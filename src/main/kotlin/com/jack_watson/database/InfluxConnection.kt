package com.jack_watson.database

import com.jack_watson.bean.DrivingSession
import com.jack_watson.bean.lapTracker.Lap
import org.influxdb.InfluxDBFactory
import org.influxdb.dto.BoundParameterQuery
import org.influxdb.dto.Point
import org.influxdb.dto.Query
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

        return if (hasResults(queryResult)) {
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

    fun getLatestDrivingSession(sourceUser: String): DrivingSession? {
        val queryResult = influxDB.query(
            Query(
                "SELECT * " +
                        "FROM drivingSession " +
                        "WHERE \"sourceUser\"='$sourceUser' " +
                        "ORDER BY time DESC " +
                        "LIMIT 1 ", influxDbConfig.database
            )
        )

        val drivingSessions = getDrivingSessionResults(queryResult)
        return if (drivingSessions != null && drivingSessions.isNotEmpty()) {
            drivingSessions[0]
        } else {
            null
        }
    }

    fun getDrivingSessionsByDriver(driverName: String): List<DrivingSession> {
        val queryResult = influxDB.query(
            Query(
                "SELECT * " +
                        "FROM drivingSession " +
                        "WHERE \"driverName\"='$driverName' ",
                influxDbConfig.database
            )
        )

        return if (queryResult != null) {
            getDrivingSessionResults(queryResult)
        } else {
            return listOf(DrivingSession())
        }

    }

    private fun getDrivingSessionResults(queryResult: QueryResult?): List<DrivingSession> {
        if (hasResults(queryResult)) {
            val list = DrivingSession.mapQueryResults(queryResult!!.results[0].series[0])
            //Return something if it worked
            if (list.isNotEmpty()) {
                return list
            }
        }
        return listOf(DrivingSession())
    }


    private fun hasResults(queryResult: QueryResult?) =
        queryResult != null && queryResult.results != null && queryResult.results[0].series != null

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
            for (i in 0 until result.columns.size) {
                val value = valueArray[i]
                when (result.columns[i]) {
                    "carClass" -> lap.carClass = value as String
                    "carModel" -> lap.carModel = value as String
                    "lapNumber" -> lap.lapNumber = (value as Double).toLong()
                    "lapTime" -> lap.lapTime = (value as Double).toFloat()
                    "overallLapNumber" -> lap.overallLapNumber = (value as Double).toLong()
                    "pitLap" -> lap.pitLap = value as Boolean
                    "positionAtEnd" -> lap.positionAtEnd = (value as Double).toLong()
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

    fun getNumberOfLapsCompletedByDriver(driverName: String): Number {
        val queryResult = influxDB.query(
            BoundParameterQuery.QueryBuilder.newQuery(
                "SELECT count(\"lapNumber\") as \"lapCount\" " +
                        "FROM \"lap\" " +
                        "WHERE \"driverName\" = '$driverName' "
            ).forDatabase(influxDbConfig.database).create()
        )

        if (hasResults(queryResult)) {
            val resultSeries = queryResult.results[0].series[0]
            for (valueArray in resultSeries.values) {
                for (i in 0 until resultSeries.columns.size) {
                    when (resultSeries.columns[i]) {
                        "lapCount" -> return valueArray[i] as Number
                    }
                }
            }
        }
        return 0
    }

    fun getBestSectorTimesByDriver(driverName: String): Array<Double> {
        val queryResult = influxDB.query(
            BoundParameterQuery.QueryBuilder.newQuery(
                "SELECT min(\"sector1Time\") as \"bestSector1Time\", " +
                        "min(\"sector2Time\") as \"bestSector2Time\", " +
                        "min(\"sector3Time\") as \"bestSector3Time\" " +
                        "FROM \"lap\" " +
                        "WHERE \"driverName\" = '$driverName' " +
                        "GROUP BY \"driverName\""
            ).forDatabase(influxDbConfig.database).create()
        )

        var bestSector1Time = -1.0
        var bestSector2Time = -1.0
        var bestSector3Time = -1.0

        if (hasResults(queryResult)) {
            val resultSeries = queryResult.results[0].series[0]
            for (valueArray in resultSeries.values) {
                for (i in 0 until resultSeries.columns.size) {
                    val value = valueArray[i]
                    when (resultSeries.columns[i]) {
                        "bestSector1Time" -> bestSector1Time = value as Double
                        "bestSector2Time" -> bestSector2Time = value as Double
                        "bestSector3Time" -> bestSector3Time = value as Double
                    }
                }
            }
        }
        return arrayOf(bestSector1Time, bestSector2Time, bestSector3Time)
    }
}