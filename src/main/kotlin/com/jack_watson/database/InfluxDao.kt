package com.jack_watson.database

import com.jack_watson.bean.TelemetryData
import org.influxdb.BatchOptions
import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory
import org.influxdb.dto.Point
import org.influxdb.dto.Query
import org.influxdb.dto.QueryResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class InfluxDao @Autowired constructor(
    private val influxDbConfig: InfluxDbConfiguration
){

    fun processTelemetryData(telemetryData: TelemetryData): Boolean {
        System.out.println(telemetryData)

        //Setup database connection
        var influxDb = connectToInfluxDb()
        influxDb.setDatabase(influxDbConfig.database)
        influxDb.enableBatch(BatchOptions.DEFAULTS)

        //testDb(influxDb)

        //Write telemetry stuff
        val point = buildPoint(telemetryData)
        System.out.println(point.lineProtocol())
        influxDb.write(point)
        influxDb.close()
        return true
    }

    private fun connectToInfluxDb() =
        InfluxDBFactory.connect(influxDbConfig.url, influxDbConfig.username, influxDbConfig.password)

    private fun buildPoint(telemetryData: TelemetryData) =
        Point.measurementByPOJO(TelemetryData::class.java)
            .time(telemetryData.TimeStamp, TimeUnit.MILLISECONDS)
            .addFieldsFromPOJO(telemetryData)
            .build()

    private fun testDb(influxDb: InfluxDB) {
        val result = influxDb.query(Query("SELECT * FROM cpu"))
        printResults(result)
    }

    private fun printResults(results: QueryResult) : Boolean {
        if(results.hasError()) {
            return false
        }

        for(result in results.results) {
            for(series in result.series) {
                System.out.println(series.name)
                for(fieldKey in series.columns) {
                    System.out.print("|\t$fieldKey\t")
                }
                System.out.println("|")

                for(point in series.values) {
                    for(fieldValue in point) {
                        System.out.print("|\t$fieldValue\t")
                    }
                    System.out.println("|")
                }
            }
        }

        return true
    }

}