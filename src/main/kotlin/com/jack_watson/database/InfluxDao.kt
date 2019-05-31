package com.jack_watson.database

import com.jack_watson.bean.ParticipantInfo
import com.jack_watson.bean.TelemetryData
import com.jack_watson.bean.Tire
import com.jack_watson.enums.TirePosition
import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory
import org.influxdb.dto.Point
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class InfluxDao @Autowired constructor(
    private val influxDbConfig: InfluxDbConfiguration
) {

    fun processTelemetryData(telemetryData: TelemetryData) {
        //Setup database connection
        var influxDb = connectToInfluxDb()
        influxDb.setDatabase(influxDbConfig.database)
        influxDb.setRetentionPolicy(influxDbConfig.retentionPolicy)

        //System.out.println("${telemetryData.Timestamp} - ${telemetryData.getTimestampEpoch()}" )

        insertPoints(influxDb, telemetryData)

        influxDb.close()
    }

    private fun insertPoints(influxDb: InfluxDB, telemetryData: TelemetryData) {
        //Insert TelemetryData
        influxDb.write(
            Point.measurementByPOJO(TelemetryData::class.java)
                .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                .addFieldsFromPOJO(telemetryData)
                .build()
        )

        //Insert EventInfo and WeatherInfo as "race" measurement
        influxDb.write(
            Point.measurement("race")
                .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                .addFieldsFromPOJO(telemetryData.EventDetails)
                .addFieldsFromPOJO(telemetryData.Weather)
                .build()
        )

        //Insert Tires
        if (telemetryData.Tires != null) {
            for (i in 0 until telemetryData.Tires.size) {
                var tire = telemetryData.Tires[i]
                tire.PositionOnCar = TirePosition.getTirePositionByIndex(i)
                influxDb.write(
                    Point.measurementByPOJO(Tire::class.java)
                        .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                        .addFieldsFromPOJO(tire)
                        .tag("positionOnCar", tire.PositionOnCar.toString())
                        .build()
                )
            }
        }

        if (telemetryData.Participants != null && telemetryData.ParticipantsEx != null && telemetryData.NumParticipants != null) {
            for (participantIndex in 0 until telemetryData.NumParticipants) {
                influxDb.write(
                    Point.measurementByPOJO(ParticipantInfo::class.java)
                        .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                        .addFieldsFromPOJO(telemetryData.Participants[participantIndex])
                        .addFieldsFromPOJO(telemetryData.ParticipantsEx[participantIndex])
                        .tag("invalidLap", telemetryData.ParticipantsEx[participantIndex].InvalidLap.toString())
                        .build()
                )
            }
        }
    }

    private fun connectToInfluxDb() =
        InfluxDBFactory.connect(influxDbConfig.url, influxDbConfig.username, influxDbConfig.password)

}