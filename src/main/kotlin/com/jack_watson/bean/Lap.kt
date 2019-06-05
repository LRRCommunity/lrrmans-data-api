package com.jack_watson.bean

import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement

@Measurement(name = "lap")
data class Lap(
    @Column(name = "lapNumber") val lapNumber: Long,
    @Column(name = "overallLapNumber") var overallLapNumber: Long,
    @Column(name = "lapTime") val lapTime: Float,
    @Column(name = "positionAtEnd") val positionAtEnd: Long,
    @Column(name = "pitLap") val pitLap: Boolean,
    @Column(name = "carModel", tag = true) val carModel: String,
    @Column(name = "carClass", tag = true) val carClass: String
)

