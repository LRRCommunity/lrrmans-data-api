package com.jack_watson.database

import com.jack_watson.bean.*
import com.jack_watson.bean.lapTracker.LapTracker
import com.jack_watson.database.InfluxUtils.Companion.addRequiredFieldsToPointBuilder
import com.jack_watson.enums.TirePosition
import org.influxdb.dto.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
                    .addFieldsFromPOJO(telemetryData)
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
                )
                    .build()
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