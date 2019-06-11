package com.jack_watson.database

import com.jack_watson.bean.TelemetryData
import com.jack_watson.bean.Vector
import org.influxdb.dto.Point
import java.util.concurrent.TimeUnit

class InfluxUtils {
    companion object {
        private const val UNSET_FLOAT_VALUE: Float = -123.0F
        private const val SECONDS_TO_MILLISECONDS_MULTIPLIER = 1000

        fun <T> addVectorToPoint(pointBuilder: Point.Builder, vector: Vector<T>, name: String): Point.Builder =
            when (vector.X) {
                is Float -> {
                    pointBuilder.addField("$name.X", vector.X as Float)
                        .addField("$name.Y", vector.Y as Float)
                        .addField("$name.Z", vector.Z as Float)
                }
                is String -> {
                    pointBuilder.addField("$name.X", vector.X as String)
                        .addField("$name.Y", vector.Y as String)
                        .addField("$name.Z", vector.Z as String)
                }
                is Boolean -> {
                    pointBuilder.addField("$name.X", vector.X as Boolean)
                        .addField("$name.Y", vector.Y as Boolean)
                        .addField("$name.Z", vector.Z as Boolean)
                }
                is Long -> {
                    pointBuilder.addField("$name.X", vector.X as Long)
                        .addField("$name.Y", vector.Y as Long)
                        .addField("$name.Z", vector.Z as Long)
                }
                is Number -> {
                    pointBuilder.addField("$name.X", vector.X as Number)
                        .addField("$name.Y", vector.Y as Number)
                        .addField("$name.Z", vector.Z as Number)
                }
                else -> {
                    pointBuilder
                }
            }

        //Return 0 if number of seconds is set to the "Unset" value, otherwise converts to milliseconds
        fun convertSecondsToMilliseconds(numberOfSeconds: Float) =
            if (numberOfSeconds.equals(UNSET_FLOAT_VALUE)) {
                -1f
            } else {
                (numberOfSeconds * SECONDS_TO_MILLISECONDS_MULTIPLIER)
            }

        fun addRequiredFieldsToPointBuilder(pointBuilder: Point.Builder, telemetryData: TelemetryData) =
                pointBuilder.time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                    .tag("sourceUser", telemetryData.SourceUser)
                    .tag("driverName", telemetryData.ReturnData.DriverName)
    }
}