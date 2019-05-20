package com.jack_watson.bean

import com.jack_watson.enums.*

data class ParticipantInfoEx(
    val CurrentSector1Time: Float?,
    val CurrentSector2Time: Float?,
    val CurrentSector3Time: Float?,

    val FastestSector1Time: Float?,
    val FastestSector2Time: Float?,
    val FastestSector3Time: Float?,

    val FastestLapTime: Float?,
    val LastLapTime: Float?,
    val InvalidLap: Boolean?,

    val RaceState: RaceState?,
    val Orientation: Array<Float>?,
    val Speed: Float?,

    val PitMode: PitMode?,
    val PitSchedule: PitSchedule?,

    val HighestFlagColor: FlagColor?,
    val HighestFlagReason: FlagReason?,

    val CarName: String?,
    val CarClassName: String?,
    val CountryCode: Int?
)