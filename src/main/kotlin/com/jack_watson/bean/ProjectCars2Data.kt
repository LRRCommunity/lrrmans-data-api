package com.jack_watson.bean

import com.jack_watson.enums.*


//The contents of this class should match TelemetryData.cs from https://github.com/LRRCommunity/libpcars2
data class ProjectCars2Data(
    val GameState: GameState?,
    val SessionState: SessionState?,
    val RaceState: RaceState?,

    val SequenceNumber: UInt?,

    val ViewedParticipantIndex: Int?,
    val NumParticipants: Int?,
    val Participant: Array<ParticipantInfo>?,
    val ParticipantsEx: Array<ParticipantInfoEx>?,

    val Brake: Float?,
    val Clutch: Float?,
    val Steering: Float?,
    val Throttle: Float?,
    val UnfilteredBrake: Float?,
    val UnfilteredClutch: Float?,
    val UnfilteredSteering: Float?,
    val UnfilteredThrottle: Float?,

    val CarName: String?,
    val CarClassName: String?,

    val EventDetails: EventInfo?,
    val Weather: WeatherInfo?,

    val InvalidLap: Boolean?,
    val BestLapTime: Float?,
    val LastLapTime: Float?,
    val CurrentTime: Float?,
    val SplitTime: Float?,
    val SplitTimeAhead: Float?,
    val SplitTimeBehind: Float?,
    val CurrentSector1Time: Float?,
    val CurrentSector2Time: Float?,
    val CurrentSector3Time: Float?,
    val FastestSector1Time: Float?,
    val FastestSector2Time: Float?,
    val FastestSector3Time: Float?,
    val PersonalFastestLapTime: Float?,
    val PersonalFastestSector1Time: Float?,
    val PersonalFastestSector2Time: Float?,
    val PersonalFastestSector3Time: Float?,

    val HighestFlagColour: FlagColor?,
    val HighestFlagReason: FlagReason?,

    val PitMode: PitMode?,
    val PitSchedule: PitSchedule?,

    val CarFlags: CarFlags?,
    val OilTemp: Float?,
    val OilPressure: Float?,
    val WaterTemp: Float?,
    val WaterPressure: Float?,
    val FuelPressure: Float?,
    val FuelLevel: Float?,
    val FuelCapacity: Float?,
    val Speed: Float?,
    val Rpm: Float?,
    val MaxRPM: Float?,
    val Gear: Int?,
    val NumGears: Int?,
    val Odometer: Float?,
    val AntiLockActive: Boolean?,
    val LastOpponentCollisionIndex: Int?,
    val LastOpponentCollisionMagnitude: Float?,
    val BoostActive: Boolean?,
    val BoostAmount: Float?,

    val Orientation: Array<Float>?,
    val LocalVelocity: Array<Float>?,
    val WorldVelocity: Array<Float>?,
    val AngularVelocity: Array<Float>?,
    val LocalAcceleration: Array<Float>?,
    val WorldAcceleration: Array<Float>?,
    val ExtentsCenter: Array<Float>?,

    val Tires: Array<Tire>?,

    val CrashState: Long?,
    val AeroDamage: Float?,
    val EngineDamage: Float?,

    val EngineSpeed: Float?,
    val EngineTorque: Float?,
    val Wings: Array<Float>?,
    val HandBrake: Float?,

    val EnforcedPitStopLap: Int?,
    val BrakeBias: Float?,
    val TurboBoostPressure: Float?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectCars2Data

        if (GameState != other.GameState) return false
        if (SessionState != other.SessionState) return false
        if (RaceState != other.RaceState) return false
        if (SequenceNumber != other.SequenceNumber) return false
        if (ViewedParticipantIndex != other.ViewedParticipantIndex) return false
        if (NumParticipants != other.NumParticipants) return false
        if (Participant != null) {
            if (other.Participant == null) return false
            if (!Participant.contentEquals(other.Participant)) return false
        } else if (other.Participant != null) return false
        if (ParticipantsEx != null) {
            if (other.ParticipantsEx == null) return false
            if (!ParticipantsEx.contentEquals(other.ParticipantsEx)) return false
        } else if (other.ParticipantsEx != null) return false
        if (Brake != other.Brake) return false
        if (Clutch != other.Clutch) return false
        if (Steering != other.Steering) return false
        if (Throttle != other.Throttle) return false
        if (UnfilteredBrake != other.UnfilteredBrake) return false
        if (UnfilteredClutch != other.UnfilteredClutch) return false
        if (UnfilteredSteering != other.UnfilteredSteering) return false
        if (UnfilteredThrottle != other.UnfilteredThrottle) return false
        if (CarName != other.CarName) return false
        if (CarClassName != other.CarClassName) return false
        if (EventDetails != other.EventDetails) return false
        if (Weather != other.Weather) return false
        if (InvalidLap != other.InvalidLap) return false
        if (BestLapTime != other.BestLapTime) return false
        if (LastLapTime != other.LastLapTime) return false
        if (CurrentTime != other.CurrentTime) return false
        if (SplitTime != other.SplitTime) return false
        if (SplitTimeAhead != other.SplitTimeAhead) return false
        if (SplitTimeBehind != other.SplitTimeBehind) return false
        if (CurrentSector1Time != other.CurrentSector1Time) return false
        if (CurrentSector2Time != other.CurrentSector2Time) return false
        if (CurrentSector3Time != other.CurrentSector3Time) return false
        if (FastestSector1Time != other.FastestSector1Time) return false
        if (FastestSector2Time != other.FastestSector2Time) return false
        if (FastestSector3Time != other.FastestSector3Time) return false
        if (PersonalFastestLapTime != other.PersonalFastestLapTime) return false
        if (PersonalFastestSector1Time != other.PersonalFastestSector1Time) return false
        if (PersonalFastestSector2Time != other.PersonalFastestSector2Time) return false
        if (PersonalFastestSector3Time != other.PersonalFastestSector3Time) return false
        if (HighestFlagColour != other.HighestFlagColour) return false
        if (HighestFlagReason != other.HighestFlagReason) return false
        if (PitMode != other.PitMode) return false
        if (PitSchedule != other.PitSchedule) return false
        if (CarFlags != other.CarFlags) return false
        if (OilTemp != other.OilTemp) return false
        if (OilPressure != other.OilPressure) return false
        if (WaterTemp != other.WaterTemp) return false
        if (WaterPressure != other.WaterPressure) return false
        if (FuelPressure != other.FuelPressure) return false
        if (FuelLevel != other.FuelLevel) return false
        if (FuelCapacity != other.FuelCapacity) return false
        if (Speed != other.Speed) return false
        if (Rpm != other.Rpm) return false
        if (MaxRPM != other.MaxRPM) return false
        if (Gear != other.Gear) return false
        if (NumGears != other.NumGears) return false
        if (Odometer != other.Odometer) return false
        if (AntiLockActive != other.AntiLockActive) return false
        if (LastOpponentCollisionIndex != other.LastOpponentCollisionIndex) return false
        if (LastOpponentCollisionMagnitude != other.LastOpponentCollisionMagnitude) return false
        if (BoostActive != other.BoostActive) return false
        if (BoostAmount != other.BoostAmount) return false
        if (Orientation != null) {
            if (other.Orientation == null) return false
            if (!Orientation.contentEquals(other.Orientation)) return false
        } else if (other.Orientation != null) return false
        if (LocalVelocity != null) {
            if (other.LocalVelocity == null) return false
            if (!LocalVelocity.contentEquals(other.LocalVelocity)) return false
        } else if (other.LocalVelocity != null) return false
        if (WorldVelocity != null) {
            if (other.WorldVelocity == null) return false
            if (!WorldVelocity.contentEquals(other.WorldVelocity)) return false
        } else if (other.WorldVelocity != null) return false
        if (AngularVelocity != null) {
            if (other.AngularVelocity == null) return false
            if (!AngularVelocity.contentEquals(other.AngularVelocity)) return false
        } else if (other.AngularVelocity != null) return false
        if (LocalAcceleration != null) {
            if (other.LocalAcceleration == null) return false
            if (!LocalAcceleration.contentEquals(other.LocalAcceleration)) return false
        } else if (other.LocalAcceleration != null) return false
        if (WorldAcceleration != null) {
            if (other.WorldAcceleration == null) return false
            if (!WorldAcceleration.contentEquals(other.WorldAcceleration)) return false
        } else if (other.WorldAcceleration != null) return false
        if (ExtentsCenter != null) {
            if (other.ExtentsCenter == null) return false
            if (!ExtentsCenter.contentEquals(other.ExtentsCenter)) return false
        } else if (other.ExtentsCenter != null) return false
        if (Tires != null) {
            if (other.Tires == null) return false
            if (!Tires.contentEquals(other.Tires)) return false
        } else if (other.Tires != null) return false
        if (CrashState != other.CrashState) return false
        if (AeroDamage != other.AeroDamage) return false
        if (EngineDamage != other.EngineDamage) return false
        if (EngineSpeed != other.EngineSpeed) return false
        if (EngineTorque != other.EngineTorque) return false
        if (Wings != null) {
            if (other.Wings == null) return false
            if (!Wings.contentEquals(other.Wings)) return false
        } else if (other.Wings != null) return false
        if (HandBrake != other.HandBrake) return false
        if (EnforcedPitStopLap != other.EnforcedPitStopLap) return false
        if (BrakeBias != other.BrakeBias) return false
        if (TurboBoostPressure != other.TurboBoostPressure) return false

        return true
    }

    override fun hashCode(): Int {
        var result = GameState?.hashCode() ?: 0
        result = 31 * result + (SessionState?.hashCode() ?: 0)
        result = 31 * result + (RaceState?.hashCode() ?: 0)
        result = 31 * result + (SequenceNumber?.hashCode() ?: 0)
        result = 31 * result + (ViewedParticipantIndex ?: 0)
        result = 31 * result + (NumParticipants ?: 0)
        result = 31 * result + (Participant?.contentHashCode() ?: 0)
        result = 31 * result + (ParticipantsEx?.contentHashCode() ?: 0)
        result = 31 * result + (Brake?.hashCode() ?: 0)
        result = 31 * result + (Clutch?.hashCode() ?: 0)
        result = 31 * result + (Steering?.hashCode() ?: 0)
        result = 31 * result + (Throttle?.hashCode() ?: 0)
        result = 31 * result + (UnfilteredBrake?.hashCode() ?: 0)
        result = 31 * result + (UnfilteredClutch?.hashCode() ?: 0)
        result = 31 * result + (UnfilteredSteering?.hashCode() ?: 0)
        result = 31 * result + (UnfilteredThrottle?.hashCode() ?: 0)
        result = 31 * result + (CarName?.hashCode() ?: 0)
        result = 31 * result + (CarClassName?.hashCode() ?: 0)
        result = 31 * result + (EventDetails?.hashCode() ?: 0)
        result = 31 * result + (Weather?.hashCode() ?: 0)
        result = 31 * result + (InvalidLap?.hashCode() ?: 0)
        result = 31 * result + (BestLapTime?.hashCode() ?: 0)
        result = 31 * result + (LastLapTime?.hashCode() ?: 0)
        result = 31 * result + (CurrentTime?.hashCode() ?: 0)
        result = 31 * result + (SplitTime?.hashCode() ?: 0)
        result = 31 * result + (SplitTimeAhead?.hashCode() ?: 0)
        result = 31 * result + (SplitTimeBehind?.hashCode() ?: 0)
        result = 31 * result + (CurrentSector1Time?.hashCode() ?: 0)
        result = 31 * result + (CurrentSector2Time?.hashCode() ?: 0)
        result = 31 * result + (CurrentSector3Time?.hashCode() ?: 0)
        result = 31 * result + (FastestSector1Time?.hashCode() ?: 0)
        result = 31 * result + (FastestSector2Time?.hashCode() ?: 0)
        result = 31 * result + (FastestSector3Time?.hashCode() ?: 0)
        result = 31 * result + (PersonalFastestLapTime?.hashCode() ?: 0)
        result = 31 * result + (PersonalFastestSector1Time?.hashCode() ?: 0)
        result = 31 * result + (PersonalFastestSector2Time?.hashCode() ?: 0)
        result = 31 * result + (PersonalFastestSector3Time?.hashCode() ?: 0)
        result = 31 * result + (HighestFlagColour?.hashCode() ?: 0)
        result = 31 * result + (HighestFlagReason?.hashCode() ?: 0)
        result = 31 * result + (PitMode?.hashCode() ?: 0)
        result = 31 * result + (PitSchedule?.hashCode() ?: 0)
        result = 31 * result + (CarFlags?.hashCode() ?: 0)
        result = 31 * result + (OilTemp?.hashCode() ?: 0)
        result = 31 * result + (OilPressure?.hashCode() ?: 0)
        result = 31 * result + (WaterTemp?.hashCode() ?: 0)
        result = 31 * result + (WaterPressure?.hashCode() ?: 0)
        result = 31 * result + (FuelPressure?.hashCode() ?: 0)
        result = 31 * result + (FuelLevel?.hashCode() ?: 0)
        result = 31 * result + (FuelCapacity?.hashCode() ?: 0)
        result = 31 * result + (Speed?.hashCode() ?: 0)
        result = 31 * result + (Rpm?.hashCode() ?: 0)
        result = 31 * result + (MaxRPM?.hashCode() ?: 0)
        result = 31 * result + (Gear ?: 0)
        result = 31 * result + (NumGears ?: 0)
        result = 31 * result + (Odometer?.hashCode() ?: 0)
        result = 31 * result + (AntiLockActive?.hashCode() ?: 0)
        result = 31 * result + (LastOpponentCollisionIndex ?: 0)
        result = 31 * result + (LastOpponentCollisionMagnitude?.hashCode() ?: 0)
        result = 31 * result + (BoostActive?.hashCode() ?: 0)
        result = 31 * result + (BoostAmount?.hashCode() ?: 0)
        result = 31 * result + (Orientation?.contentHashCode() ?: 0)
        result = 31 * result + (LocalVelocity?.contentHashCode() ?: 0)
        result = 31 * result + (WorldVelocity?.contentHashCode() ?: 0)
        result = 31 * result + (AngularVelocity?.contentHashCode() ?: 0)
        result = 31 * result + (LocalAcceleration?.contentHashCode() ?: 0)
        result = 31 * result + (WorldAcceleration?.contentHashCode() ?: 0)
        result = 31 * result + (ExtentsCenter?.contentHashCode() ?: 0)
        result = 31 * result + (Tires?.contentHashCode() ?: 0)
        result = 31 * result + (CrashState?.hashCode() ?: 0)
        result = 31 * result + (AeroDamage?.hashCode() ?: 0)
        result = 31 * result + (EngineDamage?.hashCode() ?: 0)
        result = 31 * result + (EngineSpeed?.hashCode() ?: 0)
        result = 31 * result + (EngineTorque?.hashCode() ?: 0)
        result = 31 * result + (Wings?.contentHashCode() ?: 0)
        result = 31 * result + (HandBrake?.hashCode() ?: 0)
        result = 31 * result + (EnforcedPitStopLap ?: 0)
        result = 31 * result + (BrakeBias?.hashCode() ?: 0)
        result = 31 * result + (TurboBoostPressure?.hashCode() ?: 0)
        return result
    }
}