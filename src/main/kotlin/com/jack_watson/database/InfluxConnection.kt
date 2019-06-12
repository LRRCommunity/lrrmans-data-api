package com.jack_watson.database

import com.jack_watson.bean.lapTracker.Lap
import org.influxdb.InfluxDBFactory
import org.influxdb.dto.BoundParameterQuery
import org.influxdb.dto.Point
import org.influxdb.dto.QueryResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PreDestroy

/*
 * Singleton wrapper class for InfluxDB object
 * This ensures we only ever have one connection to InfluxDB, and it will never be closed
 */
@Component
class InfluxConnection
@Autowired constructor(
    private val influxDbConfig: InfluxDbConfiguration
) {
    private var influxDB = connectToInfluxDb()

    fun write(point: Point) {
        //System.out.println(point)
        influxDB.write(point)
    }

    fun getBestLapByDriver(driverName: String): Lap? {
        val queryResult = influxDB.query(
            BoundParameterQuery.QueryBuilder.newQuery(
                "SELECT *, min(\"lapTime\") as \"fastestLapTime\" " +
                        "FROM \"lap\" " +
                        "WHERE lapTime >= 0 " +
                        "AND \"driverName\" = '$driverName' "
            ).forDatabase(influxDbConfig.database).create()
        )

        return if (queryResult != null && queryResult.results != null && queryResult.results[0].series != null) {
            mapResultToLaps(queryResult.results[0].series[0])[0]
        } else {
            null
        }
    }

    fun getTopSpeedByDriver(driverName: String) = getTopSpeedFromResult(
        influxDB.query(
            BoundParameterQuery.QueryBuilder.newQuery(
                "SELECT max(\"speed\") as \"topSpeed\" " +
                        "FROM \"telemetryData\" " +
                        "WHERE \"driverName\" = '$driverName' " +
                        "GROUP BY \"driverName\""
            ).forDatabase(influxDbConfig.database).create()
        ).results[0].series[0]
    )

    private fun connectToInfluxDb() =
        InfluxDBFactory.connect(influxDbConfig.url, influxDbConfig.username, influxDbConfig.password)
            .setDatabase(influxDbConfig.database)
            .setRetentionPolicy(influxDbConfig.retentionPolicy)

    //Close the connection on application shutdown
    @PreDestroy
    fun destroy() {
        influxDB.close()
    }

    private fun mapResultToLaps(result: QueryResult.Series): List<Lap> {
        val lapArray = ArrayList<Lap>()
        for (valueArray in result.values) {
            val lap = Lap()
            for (i in 0..result.columns.size) {
                val value = valueArray[i]
                when (result.columns[i]) {
                    "carClass" -> lap.carClass = value as String
                    "carModel" -> lap.carModel = value as String
                    "lapNumber" -> lap.lapNumber = value as Long
                    "lapTime" -> lap.lapTime = value as Float
                    "overallLapNumber" -> lap.overallLapNumber = value as Long
                    "pitLap" -> lap.pitLap = value as Boolean
                    "positionAtEnd" -> lap.positionAtEnd = value as Long
                    else -> System.out.println("InfluxConnection.mapResultToLap(result): Well, that's not meant to be here.")
                }
            }
            lapArray.add(lap)
        }
        return lapArray
    }

    private fun getTopSpeedFromResult(result: QueryResult.Series): Double {
        for (valueArray in result.values) {
            for (i in 0..result.columns.size) {
                if (result.columns[i] == "topSpeed") {
                    return valueArray[i] as Double
                }
            }
        }
        return -1.0
    }
}