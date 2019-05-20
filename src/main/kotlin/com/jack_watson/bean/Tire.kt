package com.jack_watson.bean

import com.jack_watson.enums.TerrainType
import com.jack_watson.enums.TireFlags

data class Tire(
    val TireFlags: TireFlags?,
    val Terrain: TerrainType?,
    val TireCompound: String?,

    val YPosition: Float?,
    val RotationsPerSecond: Float?,
    val Temperature: Float?,
    val HeightAboveGround: Float?,

    val TireWear: Float?,
    val BrakeDamage: Float?,
    val SuspensionDamage: Float?,

    val BrakeTemp: Float?,
    val TreadTemp: Float?,
    val LayerTemp: Float?,
    val CarcassTemp: Float?,
    val RimTemp: Float?,
    val InternalAirTemp: Float?,

    val LocalPosition: Float?,
    val SuspensionTravel: Float?,
    val SuspensionVelocity: Float?,
    val AirPressure: Float?,


    @Deprecated(message = "OBSOLETE, kept for backward compatibility only") val SlipSpeed: Float?,
    @Deprecated(message = "OBSOLETE, kept for backward compatibility only") val Grip: Float?,
    @Deprecated(message = "OBSOLETE, kept for backward compatibility only") val LateralStiffness: Float?

)