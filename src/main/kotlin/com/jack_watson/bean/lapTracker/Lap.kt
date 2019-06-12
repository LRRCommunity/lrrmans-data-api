package com.jack_watson.bean.lapTracker

import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement
import java.util.concurrent.TimeUnit

@Measurement(name = "lap", timeUnit = TimeUnit.MILLISECONDS)
data class Lap(
    @Column(name = "lapNumber") var lapNumber: Long,
    @Column(name = "overallLapNumber") var overallLapNumber: Long,
    @Column(name = "lapTime") var lapTime: Float,
    @Column(name = "positionAtEnd") var positionAtEnd: Long,
    @Column(name = "pitLap") var pitLap: Boolean,
    @Column(name = "carModel", tag = true) var carModel: String,
    @Column(name = "carClass", tag = true) var carClass: String
) {
    constructor() : this(-1L, -1L, -1.0f, -1L, false, "", "")
}

