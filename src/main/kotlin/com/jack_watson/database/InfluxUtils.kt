package com.jack_watson.database

import com.jack_watson.bean.Vector
import org.influxdb.dto.Point

class InfluxUtils {
    companion object {
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

    }
}