package com.jack_watson.bean.lapTracker

import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement
import org.influxdb.dto.Point
import java.util.concurrent.TimeUnit

@Measurement(name = "lap", timeUnit = TimeUnit.MILLISECONDS)
data class Lap(
    @Column(name = "lapNumber") var lapNumber: Long,
    @Column(name = "overallLapNumber") var overallLapNumber: Long,
    @Column(name = "lapTime") var lapTime: Float,
    @Column(name = "positionAtEnd") var positionAtEnd: Long,
    @Column(name = "pitLap") var pitLap: Boolean,
    @Column(name = "carModel", tag = true) var carModel: String,
    @Column(name = "carClass", tag = true) var carClass: String,

    var currentSector: Int = 1,
    var sectorTimes: MutableList<Float>
) {
    constructor() : this(
        -1L,
        -1L,
        -1.0f,
        -1L,
        false,
        "",
        "",
        0,
        mutableListOf(0f, 0f, 0f)
    )

    fun addSectorTimesToPointBuilder(pointBuilder: Point.Builder) =
        pointBuilder.addField("sector1Time", sectorTimes[0])
            .addField("sector2Time", sectorTimes[1])
            .addField("sector3Time", sectorTimes[2])

}

