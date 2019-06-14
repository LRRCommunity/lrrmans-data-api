package com.jack_watson.database

import com.jack_watson.bean.*
import com.jack_watson.bean.lapTracker.LapTracker
import com.jack_watson.bean.response.DriverDataResponse
import com.jack_watson.bean.response.Pc2DataResponse
import com.jack_watson.database.InfluxUtils.Companion.addRequiredFieldsToPointBuilder
import com.jack_watson.enums.TirePosition
import org.influxdb.dto.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.concurrent.TimeUnit

@Service
class InfluxDao @Autowired constructor(
    private val influxConnection: InfluxConnection,
    private val lapTracker: LapTracker
) {

    fun processTelemetryData(telemetryData: TelemetryData): Pc2DataResponse {
        //Setup database connection

        insertPoints(telemetryData)

        return Pc2DataResponse(telemetryData.Timestamp, telemetryData.SourceUser, true)
    }

    fun getDataByDriver(driverName: String): ResponseEntity<DriverDataResponse?> {
        return ResponseEntity(
            DriverDataResponse(
                driverName = driverName,
                lapsCompleted = influxConnection.getNumberOfLapsCompletedByDriver(driverName),
                personalBestLap = influxConnection.getBestLapByDriver(driverName),
                personalBestSectorTimes = influxConnection.getBestSectorTimesByDriver(driverName),
                timeDriven = DrivingSession.getTotalTimeDriven(driverName, influxConnection),
                topSpeed = influxConnection.getTopSpeedByDriver(driverName)
            ), HttpStatus.OK
        )
    }

    private fun insertPoints(telemetryData: TelemetryData) {
        //Insert TelemetryData
        influxConnection.write(
            addRequiredFieldsToPointBuilder(
                telemetryData.addVectorsToPoint(
                    Point.measurementByPOJO(TelemetryData::class.java)
                        .addFieldsFromPOJO(telemetryData)
                ),
                telemetryData
            ).build()
        )

        //Insert EventInfo and WeatherInfo as "race" measurement
        influxConnection.write(
            addRequiredFieldsToPointBuilder(
                Point.measurement("race")
                    .addFieldsFromPOJO(telemetryData.EventDetails)
                    .addFieldsFromPOJO(telemetryData.Weather), telemetryData
            ).build()
        )

        //Insert Tires
        for (i in 0 until telemetryData.Tires.size) {
            var tire = telemetryData.Tires[i]
            tire.PositionOnCar = TirePosition.getTirePositionByIndex(i)
            influxConnection.write(
                addRequiredFieldsToPointBuilder(
                    Point.measurementByPOJO(Tire::class.java)
                        .addFieldsFromPOJO(tire)
                        .tag("positionOnCar", tire.PositionOnCar.toString()), telemetryData
                ).build()
            )
        }

        for (participantIndex in 0 until telemetryData.NumParticipants) {
            writeParticipant(
                telemetryData,
                telemetryData.Participants[participantIndex],
                telemetryData.ParticipantsEx[participantIndex]
            )
        }

        lapTracker.writeLap(influxConnection, telemetryData)

        updateDrivingSession(telemetryData)

    }

    private fun updateDrivingSession(telemetryData: TelemetryData) {
        val lastDrivingSession = influxConnection.getLatestDrivingSession(telemetryData.SourceUser)
        if (lastDrivingSession != null && telemetryData.ReturnData.DriverName == lastDrivingSession.driverName) {
            val currentTime = telemetryData.getTimestampAsInstant()
            val startOfDrivingSession = lastDrivingSession.startTimestamp
            val elapsedTime = Duration.between(startOfDrivingSession, currentTime)

            lastDrivingSession.elapsedTime = elapsedTime.toMillis() / 1000.0
            influxConnection.write(
                Point.measurementByPOJO(lastDrivingSession::class.java)
                    .addFieldsFromPOJO(lastDrivingSession)
                    .time(lastDrivingSession.startTimestamp.toEpochMilli(), TimeUnit.MILLISECONDS)
                    .build()
            )
        } else {
            writeNewDrivingSession(telemetryData)
        }
    }

    private fun writeNewDrivingSession(telemetryData: TelemetryData) {
        val drivingSession = DrivingSession(
            telemetryData.getTimestampAsInstant(),
            telemetryData.SourceUser,
            telemetryData.ReturnData.DriverName
        )
        influxConnection.write(
            Point.measurementByPOJO(drivingSession::class.java)
                .addFieldsFromPOJO(drivingSession)
                .time(drivingSession.startTimestamp.toEpochMilli(), TimeUnit.MILLISECONDS)
                .build()
        )
    }

    private fun writeParticipant(
        telemetryData: TelemetryData,
        participant: ParticipantInfo,
        participantExtra: ParticipantInfoEx
    ) {
        influxConnection.write(
            addRequiredFieldsToPointBuilder(
                participantExtra.addVectorsToPoint(
                    participant.addVectorsToPoint(
                        Point.measurementByPOJO(ParticipantInfo::class.java)
                            .addFieldsFromPOJO(participant)
                            .addFieldsFromPOJO(participantExtra)
                            .tag("invalidLap", participantExtra.InvalidLap.toString())
                    )
                ),
                telemetryData
            ).build()
        )
    }


}