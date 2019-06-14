package com.jack_watson.bean

import com.jack_watson.bean.lapTracker.Lap
import com.jack_watson.database.InfluxConnection
import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement
import org.influxdb.dto.QueryResult
import java.text.SimpleDateFormat
import java.time.Instant

@Measurement(name = "drivingSession")
data class DrivingSession(
    var startTimestamp: Instant,
    @Column(name = "sourceUser", tag = true) var sourceUser: String,
    @Column(name = "driverName", tag = true) var driverName: String,
    @Column(name = "elapsedTime") var elapsedTime: Double
) {
    constructor(startTimestamp: Instant, sourceUser: String, driverName: String) : this(
        startTimestamp,
        sourceUser,
        driverName,
        0.0
    )

    constructor() : this(
        Instant.now(),
        "",
        ""
    )

    companion object {
        fun mapQueryResults(result: QueryResult.Series): List<DrivingSession> {
            val lapArray = ArrayList<DrivingSession>()
            for (valueArray in result.values) {
                val drivingSession = DrivingSession()
                for (i in 0 until result.columns.size) {
                    val value = valueArray[i]
                    when (result.columns[i]) {
                        "time" -> drivingSession.startTimestamp = Instant.parse(value as String)
                        "sourceUser" -> drivingSession.sourceUser = value as String
                        "driverName" -> drivingSession.driverName = value as String
                        "elapsedTime" -> drivingSession.elapsedTime = value as Double
                        else -> System.out.println("DrivingSession.mapQueryResults(result): Well, that's not meant to be here.")
                    }
                }
                lapArray.add(drivingSession)
            }
            return lapArray
        }

        fun getTotalTimeDriven(driverName: String, influxConnection: InfluxConnection) : Double {
            val sessions = influxConnection.getDrivingSessionsByDriver(driverName)
            var total = 0.0
            for(session in sessions) {
                total += session.elapsedTime
            }
            return total
        }
    }
}