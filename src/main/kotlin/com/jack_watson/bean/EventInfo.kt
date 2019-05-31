package com.jack_watson.bean

import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement

//Used for race measurement
data class EventInfo (
    @Column(name = "LapsInEvent") val LapsInEvent: Int?,
    @Column(name = "EventTimeRemaining") val EventTimeRemaining: Float?,
    @Column(name = "TrackLength") val TrackLength: Float?,
    @Column(name = "NumSectors") val NumSectors: Int?,

    @Column(name = "TrackLocation", tag = true) val TrackLocation: String?,
    @Column(name = "TrackVariant", tag = true) val TrackVariant: String?,
    @Column(name = "TranslatedTrackLocation", tag = true) val TranslatedTrackLocation: String?,
    @Column(name = "TranslatedTrackVariant", tag = true) val TranslatedTrackVariant: String?,

    @Column(name = "WorldFastestLapTime") val WorldFastestLapTime: Float?,
    @Column(name = "WorldFastestSector1Time") val WorldFastestSector1Time: Float?,
    @Column(name = "WorldFastestSector2Time") val WorldFastestSector2Time: Float?,
    @Column(name = "WorldFastestSector3Time") val WorldFastestSector3Time: Float?
)