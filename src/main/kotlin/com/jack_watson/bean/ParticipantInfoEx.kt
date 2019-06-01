package com.jack_watson.bean

import com.jack_watson.database.InfluxUtils
import org.influxdb.annotation.Column
import org.influxdb.dto.Point

data class ParticipantInfoEx(
    @Column(name = "currentSector1Time") val CurrentSector1Time: Float?,
    @Column(name = "currentSector2Time") val CurrentSector2Time: Float?,
    @Column(name = "currentSector3Time") val CurrentSector3Time: Float?,

    @Column(name = "FastestSector1Time") val FastestSector1Time: Float?,
    @Column(name = "FastestSector2Time") val FastestSector2Time: Float?,
    @Column(name = "FastestSector3Time") val FastestSector3Time: Float?,

    @Column(name = "fastestLapTime") val FastestLapTime: Float?,
    @Column(name = "lastLapTime") val LastLapTime: Float?,
    val InvalidLap: Boolean = false,

    @Column(name = "raceState", tag = true) val RaceState: String?,

    //TODO: Figure out how to insert this
    val Orientation: Vector<Float>?,

    @Column(name = "speed") val Speed: Float?,

    @Column(name = "pitMode") val PitMode: String?,
    @Column(name = "pitSchedule") val PitSchedule: String?,

    @Column(name = "highestFlagColor") val HighestFlagColor: String?,
    @Column(name = "highestFlagReason") val HighestFlagReason: String?,

    @Column(name = "carName", tag = true) val CarName: String?,
    @Column(name = "carClass", tag = true) val CarClassName: String?,

    //TODO: Figure out what to do with this?
    // @Column(name = "countryCode", tag = true)
    val CountryCode: Int?
) {
    fun addVectorsToPoint(pointBuilder: Point.Builder): Point.Builder =
        InfluxUtils.addVectorToPoint(pointBuilder, Orientation!!, "worldPosition")

}