package com.jack_watson.bean

import com.jack_watson.enums.*
import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement


//The contents of this class should match TelemetryData.cs from https://github.com/LRRCommunity/libpcars2
@Measurement(name = "telemetryData", database = "pc2DataTest")
data class TelemetryData(

    val TimeStamp: Long,

    @Column(name = "gameState", tag = true) val GameState: String?,
    @Column(name = "sessionState", tag = true) val SessionState: String?,
    @Column(name = "raceState", tag = true) val RaceState: String?,

    @Column(name = "viewedParticipantIndex") val ViewedParticipantIndex: Int?,
    @Column(name = "numParticipants") val NumParticipants: Int?,

    //TODO: Figure out how these get into influxdb
    val Participant: Array<ParticipantInfo>?,
    val ParticipantsEx: Array<ParticipantInfoEx>?,

    @Column(name = "brake") val Brake: Float?,
    @Column(name = "clutch") val Clutch: Float?,
    @Column(name = "steering") val Steering: Float?,
    @Column(name = "throttle") val Throttle: Float?,

    @Column(name = "carName", tag = true) val CarName: String?,
    @Column(name = "carClassName", tag = true) val CarClassName: String?,

    //TODO: Figure out how these get into influxdb
    val EventDetails: EventInfo?,
    val Weather: WeatherInfo?,

    @Column(name = "invalidLap") val InvalidLap: Boolean?,
    @Column(name = "bestLapTime") val BestLapTime: Float?,
    @Column(name = "lastLapTime") val LastLapTime: Float?,
    @Column(name = "currentTime") val CurrentTime: Float?,
    @Column(name = "splitTime") val SplitTime: Float?,
    @Column(name = "splitTimeAhead") val SplitTimeAhead: Float?,
    @Column(name = "splitTimeBehind") val SplitTimeBehind: Float?,
    @Column(name = "currentSector1Time") val CurrentSector1Time: Float?,
    @Column(name = "currentSector2Time") val CurrentSector2Time: Float?,
    @Column(name = "currentSector3Time") val CurrentSector3Time: Float?,

    val FastestSector1Time: Float?,
    val FastestSector2Time: Float?,
    val FastestSector3Time: Float?,
    val PersonalFastestLapTime: Float?,
    val PersonalFastestSector1Time: Float?,
    val PersonalFastestSector2Time: Float?,
    val PersonalFastestSector3Time: Float?,

    @Column(name = "highestFlagColor") val HighestFlagColour: String?,
    @Column(name = "highestFlagReason") val HighestFlagReason: String?,

    @Column(name = "pitMode", tag = true) val PitMode: String?,
    @Column(name = "pitSchedule", tag = true) val PitSchedule: String?,

    @Column(name = "carFlags", tag = true) val CarFlags: String?,
    @Column(name = "oilTemp") val OilTemp: Float?,
    @Column(name = "oilPressure") val OilPressure: Float?,
    @Column(name = "waterTemp") val WaterTemp: Float?,
    @Column(name = "waterPressure") val WaterPressure: Float?,
    @Column(name = "fuelPressure") val FuelPressure: Float?,
    @Column(name = "fuelLevel") val FuelLevel: Float?,
    @Column(name = "fuelCapacity") val FuelCapacity: Float?,
    @Column(name = "speed") val Speed: Float?,
    @Column(name = "rpm") val Rpm: Float?,
    @Column(name = "maxRpm") val MaxRpm: Float?,
    @Column(name = "gear") val Gear: Int?,
    @Column(name = "numGears") val NumGears: Int?,
    @Column(name = "odometer") val Odometer: Float?,

    //TODO: This doesn't work for some reason?
    val AntiLockActive: Boolean?,

    @Column(name = "lastOpponentCollisionIndex") val LastOpponentCollisionIndex: Int?,
    @Column(name = "lastOpponentCollisionMagnitude") val LastOpponentCollisionMagnitude: Float?,

    //Need to add these fields manually? The elements in each array correspond to the xyz plane
    // [0] = X, [1] = Y, [3] = Z
    val Orientation: Array<Float>?,
    val LocalVelocity: Array<Float>?,
    val WorldVelocity: Array<Float>?,
    val AngularVelocity: Array<Float>?,
    val LocalAcceleration: Array<Float>?,
    val WorldAcceleration: Array<Float>?,

    //TODO: Figure out whatever the hell this means
    val ExtentsCenter: Array<Float>?,

    //TODO: Revisit this to figure out how to get the fields into influx
    val Tires: Array<Tire>?,

    @Column(name = "crashState") val CrashState: Long?,
    @Column(name = "aeroDamage") val AeroDamage: Float?,
    @Column(name = "engineDamage") val EngineDamage: Float?,

    @Column(name = "engineSpeed") val EngineSpeed: Float?,
    @Column(name = "engineTorque") val EngineTorque: Float?,

    val Wings: Array<Float>?,


    @Column(name = "handBrake") val HandBrake: Float?,

    @Column(name = "enforcedPitStopLap") val EnforcedPitStopLap: Int?,
    @Column(name = "brakeBias") val BrakeBias: Float?

)