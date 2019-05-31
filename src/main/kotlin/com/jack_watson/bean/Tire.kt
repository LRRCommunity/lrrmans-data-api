package com.jack_watson.bean

import com.jack_watson.enums.TirePosition
import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement
import kotlin.experimental.and

@Measurement(name = "tires")
data class Tire(
    //TODO: break into multiple fields, its actually tracking all three values in the enum
    private var _TireFlags: Byte,

    @Column(name = "isAttached") var isAttached: Boolean = false,
    @Column(name = "isInflated") var isInflated: Boolean = false,
    @Column(name = "isOnGround") var isOnGround: Boolean = false,

    var PositionOnCar: TirePosition?,
    @Column(name = "terrain") val Terrain: String?,

    //TODO: Figure out what this is? Not actually sure
    @Column(name = "compound", tag = true) val TireCompound: String?,

    @Column(name = "yPosition") val YPosition: Float?,
    @Column(name = "rotationsPerSecond") val RotationsPerSecond: Float?,
    @Column(name = "temperature") val Temperature: Float?,
    @Column(name = "heightAboveGround") val HeightAboveGround: Float?,

    @Column(name = "tireWear") val TireWear: Float?,
    @Column(name = "brakeDamage") val BrakeDamage: Float?,
    @Column(name = "suspensionDamage") val SuspensionDamage: Float?,

    @Column(name = "brakeTemperature") val BrakeTemp: Float?,
    @Column(name = "treadTemperature") val TreadTemp: Float?,
    @Column(name = "layerTemperature") val LayerTemp: Float?,
    @Column(name = "carcassTemperature") val CarcassTemp: Float?,
    @Column(name = "rimTemperature") val RimTemp: Float?,
    @Column(name = "internalAirTemperature") val InternalAirTemp: Float?,

    @Column(name = "wheelLocalPosition") val LocalPosition: Float?,
    @Column(name = "suspensionTravel") val SuspensionTravel: Float?,
    @Column(name = "suspensionVelocity") val SuspensionVelocity: Float?,
    @Column(name = "airPressure") val AirPressure: Float?,

    //Not included because they are marked deprecated
    @Deprecated(message = "OBSOLETE, kept for backward compatibility only") val SlipSpeed: Float?,
    @Deprecated(message = "OBSOLETE, kept for backward compatibility only") val Grip: Float?,
    @Deprecated(message = "OBSOLETE, kept for backward compatibility only") val LateralStiffness: Float?

) {
    var TireFlags: Byte = 0
        set(flags) {
            field = flags
            isAttached = (TireFlags and 1) == 1.toByte()
            isInflated = (TireFlags and 2) == 1.toByte()
            isOnGround = (TireFlags and 4) == 1.toByte()
        }
    @Column(name = "terrainType", tag = true)
    var terrainType: String = Terrain.toString()
}