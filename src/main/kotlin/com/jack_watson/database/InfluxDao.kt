package com.jack_watson.database

import com.jack_watson.bean.*
import com.jack_watson.bean.lapTracker.LapTracker
import com.jack_watson.enums.TirePosition
import org.influxdb.dto.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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

    private fun insertPoints(telemetryData: TelemetryData) {
        //Insert TelemetryData
        influxConnection.write(
            telemetryData.addVectorsToPoint(
                Point.measurementByPOJO(TelemetryData::class.java)
                    .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                    .tag("sourceUser", telemetryData.SourceUser)
                    .addFieldsFromPOJO(telemetryData)
            ).build()
        )

        //Insert EventInfo and WeatherInfo as "race" measurement
        influxConnection.write(
            Point.measurement("race")
                .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                .tag("sourceUser", telemetryData.SourceUser)
                .addFieldsFromPOJO(telemetryData.EventDetails)
                .addFieldsFromPOJO(telemetryData.Weather)
                .build()
        )

        //Insert Tires
        if (telemetryData.Tires != null) {
            for (i in 0 until telemetryData.Tires.size) {
                var tire = telemetryData.Tires[i]
                tire.PositionOnCar = TirePosition.getTirePositionByIndex(i)
                influxConnection.write(
                    Point.measurementByPOJO(Tire::class.java)
                        .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                        .tag("sourceUser", telemetryData.SourceUser)
                        .addFieldsFromPOJO(tire)
                        .tag("positionOnCar", tire.PositionOnCar.toString())
                        .build()
                )
            }
        }

        if (telemetryData.Participants != null && telemetryData.ParticipantsEx != null && telemetryData.NumParticipants != null) {
            for (participantIndex in 0 until telemetryData.NumParticipants) {
                writeParticipant(
                    telemetryData,
                    telemetryData.Participants[participantIndex],
                    telemetryData.ParticipantsEx[participantIndex]
                )
            }
        }

        lapTracker.writeLap(influxConnection, telemetryData)
    }

    private fun writeParticipant(
        telemetryData: TelemetryData,
        participant: ParticipantInfo,
        participantExtra: ParticipantInfoEx
    ) {
        influxConnection.write(
            participantExtra.addVectorsToPoint(
                participant.addVectorsToPoint(
                    Point.measurementByPOJO(ParticipantInfo::class.java)
                        .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                        .tag("sourceUser", telemetryData.SourceUser)
                        .addFieldsFromPOJO(participant)
                        .addFieldsFromPOJO(participantExtra)
                        .tag("invalidLap", participantExtra.InvalidLap.toString())
                )
            ).build()
        )
    }


}