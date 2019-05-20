package com.jack_watson.bean

data class EventInfo (
    val LapsInEvent: UInt,
    val EventTimeRemaining: Float,
    val TrackLength: Float,
    val NumSectors: Int,

    val TrackLocation: String,
    val TrackVariant: String,
    val TranslatedTrackLocation: String,
    val TranslatedTrackVariant: String,

    val WorldFastestLapTime: Float,
    val WorldFastestSector1Time: Float,
    val WorldFastestSector2Time: Float,
    val WorldFastestSector3Time: Float
)