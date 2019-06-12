package com.jack_watson.bean

import com.jack_watson.database.InfluxUtils
import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement
import org.influxdb.dto.Point
import java.time.Instant
import java.time.format.DateTimeFormatter


//The contents of this class should match TelemetryData.cs from https://github.com/LRRCommunity/libpcars2
@Measurement(name = "telemetryData")
data class TelemetryData(
    val Timestamp: String,
    val SourceUser: String,
    val ReturnData: ReturnData,

    @Column(name = "gameState", tag = true) val GameState: String,
    @Column(name = "sessionState", tag = true) val SessionState: String,
    @Column(name = "raceState", tag = true) val RaceState: String,

    @Column(name = "viewedParticipantIndex") val ViewedParticipantIndex: Int,
    @Column(name = "numParticipants") val NumParticipants: Int,

    //Inserted as participant
    val Participants: Array<ParticipantInfo>,
    val ParticipantsEx: Array<ParticipantInfoEx>,

    @Column(name = "brake") val Brake: Float,
    @Column(name = "clutch") val Clutch: Float,
    @Column(name = "steering") val Steering: Float,
    @Column(name = "throttle") val Throttle: Float,

    @Column(name = "carName", tag = true) val CarName: String,
    @Column(name = "carClassName", tag = true) val CarClassName: String,

    //Inserted as race
    val EventDetails: EventInfo,
    val Weather: WeatherInfo,

    @Column(name = "invalidLap") val InvalidLap: Boolean,
    @Column(name = "bestLapTime") val BestLapTime: Float,
    @Column(name = "lastLapTime") val LastLapTime: Float,
    @Column(name = "currentTime") val CurrentTime: Float,
    @Column(name = "splitTime") val SplitTime: Float,
    @Column(name = "splitTimeAhead") val SplitTimeAhead: Float,
    @Column(name = "splitTimeBehind") val SplitTimeBehind: Float,
    @Column(name = "currentSector1Time") val CurrentSector1Time: Float,
    @Column(name = "currentSector2Time") val CurrentSector2Time: Float,
    @Column(name = "currentSector3Time") val CurrentSector3Time: Float,

    @Column(name = "fastestSector1Time") val FastestSector1Time: Float,
    @Column(name = "fastestSector2Time") val FastestSector2Time: Float,
    @Column(name = "fastestSector3Time") val FastestSector3Time: Float,
    @Column(name = "personalFastestLapTime") val PersonalFastestLapTime: Float,
    @Column(name = "personalFastestSector1Time") val PersonalFastestSector1Time: Float,
    @Column(name = "personalFastestSector2Time") val PersonalFastestSector2Time: Float,
    @Column(name = "personalFastestSector3Time") val PersonalFastestSector3Time: Float,

    @Column(name = "highestFlagColor") val HighestFlagColour: String?,
    @Column(name = "highestFlagReason") val HighestFlagReason: String?,

    @Column(name = "pitMode", tag = true) val PitMode: String,
    @Column(name = "pitSchedule", tag = true) val PitSchedule: String,

    //TODO: Split these flags into multiple fields
    val CarFlags: Byte?,

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
    val Orientation: Vector<Float>?,
    val LocalVelocity: Vector<Float>?,
    val WorldVelocity: Vector<Float>?,
    val AngularVelocity: Vector<Float>?,
    val LocalAcceleration: Vector<Float>?,
    val WorldAcceleration: Vector<Float>?,

    //TODO: Figure out whatever the hell this means
    val ExtentsCenter: Vector<Float>?,

    //Each tire is inserted as its own point into the tires measurement
    val Tires: Array<Tire>,

    @Column(name = "crashState") val CrashState: Long?,
    @Column(name = "aeroDamage") val AeroDamage: Float?,
    @Column(name = "engineDamage") val EngineDamage: Float?,

    @Column(name = "engineSpeed") val EngineSpeed: Float?,
    @Column(name = "engineTorque") val EngineTorque: Float?,

    val Wings: Array<Float>?,


    @Column(name = "handBrake") val HandBrake: Float?,

    @Column(name = "enforcedPitStopLap") val EnforcedPitStopLap: Int?,
    @Column(name = "brakeBias") val BrakeBias: Float?

) {
    private val dateFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    private val _timestamp: Long = Instant.from(dateFormat.parse(Timestamp)).toEpochMilli()
    fun getTimestampEpoch() = _timestamp

    fun addVectorsToPoint(pointBuilder: Point.Builder): Point.Builder {
        var newPointBuilder = InfluxUtils.addVectorToPoint(pointBuilder, Orientation!!, "orientation")
        newPointBuilder = InfluxUtils.addVectorToPoint(newPointBuilder, LocalVelocity!!, "localVelocity")
        newPointBuilder = InfluxUtils.addVectorToPoint(newPointBuilder, WorldVelocity!!, "worldVelocity")
        newPointBuilder = InfluxUtils.addVectorToPoint(newPointBuilder, AngularVelocity!!, "angularVelocity")
        newPointBuilder = InfluxUtils.addVectorToPoint(newPointBuilder, LocalAcceleration!!, "localAcceleration")
        newPointBuilder = InfluxUtils.addVectorToPoint(newPointBuilder, WorldAcceleration!!, "worldAcceleration")
        newPointBuilder = InfluxUtils.addVectorToPoint(newPointBuilder, ExtentsCenter!!, "extentsCenter")
        return newPointBuilder
    }
}