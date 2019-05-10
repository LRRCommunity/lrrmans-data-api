package com.jack_watson.bean

import com.jack_watson.enums.GameState
import com.jack_watson.enums.RaceState
import com.jack_watson.enums.SessionState

// Look I know this class is bad, let me explain.
// The goal is to do next to no processing of the Project Cars 2 data on the host machine it is sent from.
// This means I have to assume all the data is coming through like this
// Not all of the values are going to be set, but for the sake of developing without waiting for the data-collector
// to be done, I've got them all here for now. Sorry.
data class ProjectCars2Data(
    val mVersion: Long?,
    val mBuildVersionNumber: Long?,
    val mGameState: GameState?,
    val mSessionState: SessionState?,
    val mRaceState: RaceState?,
    val mViewedParticipantIndex: Int?,
    val mNumParticipants: Int?,
    val mParticipantInfo: Array<ParticipantInfo>?,
    val mUnfilteredThrottle: Float?,
    val mUnfilteredBrake: Float?,
    val mUnfilteredSteering: Float?,
    val mUnfilteredClutch: Float?,
    val mCarName: String?,
    val mCarClassName: String?,
    val mLapsInEvent: Long?,
    val mTrackLocation: String?,
    val mTrackVariation: String?,
    val mTrackLength: Float?,
    val mNumSectors: Int?,
    val mLapInvalidated: Boolean?,
    val mBestLapTime: Float?,
    val mLastLapTime: Float?,
    val mCurrentTime: Float?,
    val mSplitTimeAhead: Float?,
    val mSplitTimeBehind: Float?,
    val mSplitTime: Float?,
    val mEventTimeRemaining: Float?,
    val mPersonalFastestLapTime: Float?,
    val mWorldFastestLapTime: Float?,
    val mCurrentSector1Time: Float?,
    val mCurrentSector2Time: Float?,
    val mCurrentSector3Time: Float?,
    val mFastestSector1Time: Float?,
    val mFastestSector2Time: Float?,
    val mFastestSector3Time: Float?,
    val mPersonalFastestSector1Time: Float?,
    val mPersonalFastestSector2Time: Float?,
    val mPersonalFastestSector3Time: Float?,
    val mWorldFastestSector1Time: Float?,
    val mWorldFastestSector2Time: Float?,
    val mWorldFastestSector3Time: Float?,
    val mHighestFlagColour: Long?,
    val mHighestFlagReason: Long?,
    val mPitMode: Long?,
    val mPitSchedule: Long?,
    val mCarFlags: Long?,
    val mOilTempCelsius: Float?,
    val mOilPressureKPa: Float?,
    val mWaterTempCelsius: Float?,
    val mWaterPressureKPa: Float?,
    val mFuelPressureKPa: Float?,
    val mFuelLevel: Float?,
    val mFuelCapacity: Float?,
    val mSpeed: Float?,
    val mRpm: Float?,
    val mMaxRPM: Float?,
    val mBrake: Float?,
    val mThrottle: Float?,
    val mClutch: Float?,
    val mSteering: Float?,
    val mGear: Int?,
    val mNumGears: Int?,
    val mOdometerKM: Float?,
    val mAntiLockActive: Boolean?,
    val mLastOpponentCollisionIndex: Int?,
    val mLastOpponentCollisionMagnitude: Float?,
    val mBoostActive: Boolean?,
    val mBoostAmount: Float?,
    val mOrientation: Array<Float>?,
    val mLocalVelocity: Array<Float>?,
    val mWorldVelocity: Array<Float>?,
    val mAngularVelocity: Array<Float>?,
    val mLocalAcceleration: Array<Float>?,
    val mWorldAcceleration: Array<Float>?,
    val mExtentsCentre: Array<Float>?,
    val mTyreFlags: Array<Long>?,
    val mTerrain: Array<Long>?,
    val mTyreY: Array<Float>?,
    val mTyreRPS: Array<Float>?,
    val mTyreSlipSpeed: Array<Float>?,
    val mTyreTemp: Array<Float>?,
    val mTyreGrip: Array<Float>?,
    val mTyreHeightAboveGround: Array<Float>?,
    val mTyreLateralStiffness: Array<Float>?,
    val mTyreWear: Array<Float>?,
    val mBrakeDamage: Array<Float>?,
    val mSuspensionDamage: Array<Float>?,
    val mBrakeTempCelsius: Array<Float>?,
    val mTyreTreadTemp: Array<Float>?,
    val mTyreLayerTemp: Array<Float>?,
    val mTyreCarcassTemp: Array<Float>?,
    val mTyreRimTemp: Array<Float>?,
    val mTyreInternalAirTemp: Array<Float>?,
    val mCrashState: Long?,
    val mAeroDamage: Float?,
    val mEngineDamage: Float?,
    val mAmbientTemperature: Float?,
    val mTrackTemperature: Float?,
    val mRainDensity: Float?,
    val mWindSpeed: Float?,
    val mWindDirectionX: Float?,
    val mWindDirectionY: Float?,
    val mCloudBrightness: Float?,
    val mSequenceNumber: Long?,
    val mWheelLocalPositionY: Array<Float>?,
    val mSuspensionTravel: Array<Float>?,
    val mSuspensionVelocity: Array<Float>?,
    val mAirPressure: Array<Float>?,
    val mEngineSpeed: Float?,
    val mEngineTorque: Float?,
    val mWings: Array<Float>?,
    val mHandBrake: Float?,
    val mCurrentSector1Times: Array<Float>?,
    val mCurrentSector2Times: Array<Float>?,
    val mCurrentSector3Times: Array<Float>?,
    val mFastestSector1Times: Array<Float>?,
    val mFastestSector2Times: Array<Float>?,
    val mFastestSector3Times: Array<Float>?,
    val mFastestLapTimes: Array<Float>?,
    val mLastLapTimes: Array<Float>?,
    val mLapsInvalidated: Array<Boolean>?,
    val mRaceStates: Array<Long>?,
    val mPitModes: Array<Long>?,
    val mOrientations: Array<Array<Float>>?,
    val mSpeeds: Array<Float>?,
    val mCarNames: Array<String>?,
    val mCarClassNames: Array<String>?,
    val mEnforcedPitStopLap: Int?,
    val mTranslatedTrackLocation: String?,
    val mTranslatedTrackVariation: String?,
    val mBrakeBias: Float?,
    val mTurboBoostPressure: Float?,
    val mTyreCompound: Array<String>?,
    val mPitSchedules: Array<Long>?,
    val mHighestFlagColours: Array<Long>?,
    val mHighestFlagReasons: Array<Long>?,
    val mNationalities: Array<Long>?,
    val mSnowDensity: Float?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectCars2Data

        if (mVersion != other.mVersion) return false
        if (mBuildVersionNumber != other.mBuildVersionNumber) return false
        if (mGameState != other.mGameState) return false
        if (mSessionState != other.mSessionState) return false
        if (mRaceState != other.mRaceState) return false
        if (mViewedParticipantIndex != other.mViewedParticipantIndex) return false
        if (mNumParticipants != other.mNumParticipants) return false
        if (mParticipantInfo != null) {
            if (other.mParticipantInfo == null) return false
            if (!mParticipantInfo.contentEquals(other.mParticipantInfo)) return false
        } else if (other.mParticipantInfo != null) return false
        if (mUnfilteredThrottle != other.mUnfilteredThrottle) return false
        if (mUnfilteredBrake != other.mUnfilteredBrake) return false
        if (mUnfilteredSteering != other.mUnfilteredSteering) return false
        if (mUnfilteredClutch != other.mUnfilteredClutch) return false
        if (mCarName != other.mCarName) return false
        if (mCarClassName != other.mCarClassName) return false
        if (mLapsInEvent != other.mLapsInEvent) return false
        if (mTrackLocation != other.mTrackLocation) return false
        if (mTrackVariation != other.mTrackVariation) return false
        if (mTrackLength != other.mTrackLength) return false
        if (mNumSectors != other.mNumSectors) return false
        if (mLapInvalidated != other.mLapInvalidated) return false
        if (mBestLapTime != other.mBestLapTime) return false
        if (mLastLapTime != other.mLastLapTime) return false
        if (mCurrentTime != other.mCurrentTime) return false
        if (mSplitTimeAhead != other.mSplitTimeAhead) return false
        if (mSplitTimeBehind != other.mSplitTimeBehind) return false
        if (mSplitTime != other.mSplitTime) return false
        if (mEventTimeRemaining != other.mEventTimeRemaining) return false
        if (mPersonalFastestLapTime != other.mPersonalFastestLapTime) return false
        if (mWorldFastestLapTime != other.mWorldFastestLapTime) return false
        if (mCurrentSector1Time != other.mCurrentSector1Time) return false
        if (mCurrentSector2Time != other.mCurrentSector2Time) return false
        if (mCurrentSector3Time != other.mCurrentSector3Time) return false
        if (mFastestSector1Time != other.mFastestSector1Time) return false
        if (mFastestSector2Time != other.mFastestSector2Time) return false
        if (mFastestSector3Time != other.mFastestSector3Time) return false
        if (mPersonalFastestSector1Time != other.mPersonalFastestSector1Time) return false
        if (mPersonalFastestSector2Time != other.mPersonalFastestSector2Time) return false
        if (mPersonalFastestSector3Time != other.mPersonalFastestSector3Time) return false
        if (mWorldFastestSector1Time != other.mWorldFastestSector1Time) return false
        if (mWorldFastestSector2Time != other.mWorldFastestSector2Time) return false
        if (mWorldFastestSector3Time != other.mWorldFastestSector3Time) return false
        if (mHighestFlagColour != other.mHighestFlagColour) return false
        if (mHighestFlagReason != other.mHighestFlagReason) return false
        if (mPitMode != other.mPitMode) return false
        if (mPitSchedule != other.mPitSchedule) return false
        if (mCarFlags != other.mCarFlags) return false
        if (mOilTempCelsius != other.mOilTempCelsius) return false
        if (mOilPressureKPa != other.mOilPressureKPa) return false
        if (mWaterTempCelsius != other.mWaterTempCelsius) return false
        if (mWaterPressureKPa != other.mWaterPressureKPa) return false
        if (mFuelPressureKPa != other.mFuelPressureKPa) return false
        if (mFuelLevel != other.mFuelLevel) return false
        if (mFuelCapacity != other.mFuelCapacity) return false
        if (mSpeed != other.mSpeed) return false
        if (mRpm != other.mRpm) return false
        if (mMaxRPM != other.mMaxRPM) return false
        if (mBrake != other.mBrake) return false
        if (mThrottle != other.mThrottle) return false
        if (mClutch != other.mClutch) return false
        if (mSteering != other.mSteering) return false
        if (mGear != other.mGear) return false
        if (mNumGears != other.mNumGears) return false
        if (mOdometerKM != other.mOdometerKM) return false
        if (mAntiLockActive != other.mAntiLockActive) return false
        if (mLastOpponentCollisionIndex != other.mLastOpponentCollisionIndex) return false
        if (mLastOpponentCollisionMagnitude != other.mLastOpponentCollisionMagnitude) return false
        if (mBoostActive != other.mBoostActive) return false
        if (mBoostAmount != other.mBoostAmount) return false
        if (mOrientation != null) {
            if (other.mOrientation == null) return false
            if (!mOrientation.contentEquals(other.mOrientation)) return false
        } else if (other.mOrientation != null) return false
        if (mLocalVelocity != null) {
            if (other.mLocalVelocity == null) return false
            if (!mLocalVelocity.contentEquals(other.mLocalVelocity)) return false
        } else if (other.mLocalVelocity != null) return false
        if (mWorldVelocity != null) {
            if (other.mWorldVelocity == null) return false
            if (!mWorldVelocity.contentEquals(other.mWorldVelocity)) return false
        } else if (other.mWorldVelocity != null) return false
        if (mAngularVelocity != null) {
            if (other.mAngularVelocity == null) return false
            if (!mAngularVelocity.contentEquals(other.mAngularVelocity)) return false
        } else if (other.mAngularVelocity != null) return false
        if (mLocalAcceleration != null) {
            if (other.mLocalAcceleration == null) return false
            if (!mLocalAcceleration.contentEquals(other.mLocalAcceleration)) return false
        } else if (other.mLocalAcceleration != null) return false
        if (mWorldAcceleration != null) {
            if (other.mWorldAcceleration == null) return false
            if (!mWorldAcceleration.contentEquals(other.mWorldAcceleration)) return false
        } else if (other.mWorldAcceleration != null) return false
        if (mExtentsCentre != null) {
            if (other.mExtentsCentre == null) return false
            if (!mExtentsCentre.contentEquals(other.mExtentsCentre)) return false
        } else if (other.mExtentsCentre != null) return false
        if (mTyreFlags != null) {
            if (other.mTyreFlags == null) return false
            if (!mTyreFlags.contentEquals(other.mTyreFlags)) return false
        } else if (other.mTyreFlags != null) return false
        if (mTerrain != null) {
            if (other.mTerrain == null) return false
            if (!mTerrain.contentEquals(other.mTerrain)) return false
        } else if (other.mTerrain != null) return false
        if (mTyreY != null) {
            if (other.mTyreY == null) return false
            if (!mTyreY.contentEquals(other.mTyreY)) return false
        } else if (other.mTyreY != null) return false
        if (mTyreRPS != null) {
            if (other.mTyreRPS == null) return false
            if (!mTyreRPS.contentEquals(other.mTyreRPS)) return false
        } else if (other.mTyreRPS != null) return false
        if (mTyreSlipSpeed != null) {
            if (other.mTyreSlipSpeed == null) return false
            if (!mTyreSlipSpeed.contentEquals(other.mTyreSlipSpeed)) return false
        } else if (other.mTyreSlipSpeed != null) return false
        if (mTyreTemp != null) {
            if (other.mTyreTemp == null) return false
            if (!mTyreTemp.contentEquals(other.mTyreTemp)) return false
        } else if (other.mTyreTemp != null) return false
        if (mTyreGrip != null) {
            if (other.mTyreGrip == null) return false
            if (!mTyreGrip.contentEquals(other.mTyreGrip)) return false
        } else if (other.mTyreGrip != null) return false
        if (mTyreHeightAboveGround != null) {
            if (other.mTyreHeightAboveGround == null) return false
            if (!mTyreHeightAboveGround.contentEquals(other.mTyreHeightAboveGround)) return false
        } else if (other.mTyreHeightAboveGround != null) return false
        if (mTyreLateralStiffness != null) {
            if (other.mTyreLateralStiffness == null) return false
            if (!mTyreLateralStiffness.contentEquals(other.mTyreLateralStiffness)) return false
        } else if (other.mTyreLateralStiffness != null) return false
        if (mTyreWear != null) {
            if (other.mTyreWear == null) return false
            if (!mTyreWear.contentEquals(other.mTyreWear)) return false
        } else if (other.mTyreWear != null) return false
        if (mBrakeDamage != null) {
            if (other.mBrakeDamage == null) return false
            if (!mBrakeDamage.contentEquals(other.mBrakeDamage)) return false
        } else if (other.mBrakeDamage != null) return false
        if (mSuspensionDamage != null) {
            if (other.mSuspensionDamage == null) return false
            if (!mSuspensionDamage.contentEquals(other.mSuspensionDamage)) return false
        } else if (other.mSuspensionDamage != null) return false
        if (mBrakeTempCelsius != null) {
            if (other.mBrakeTempCelsius == null) return false
            if (!mBrakeTempCelsius.contentEquals(other.mBrakeTempCelsius)) return false
        } else if (other.mBrakeTempCelsius != null) return false
        if (mTyreTreadTemp != null) {
            if (other.mTyreTreadTemp == null) return false
            if (!mTyreTreadTemp.contentEquals(other.mTyreTreadTemp)) return false
        } else if (other.mTyreTreadTemp != null) return false
        if (mTyreLayerTemp != null) {
            if (other.mTyreLayerTemp == null) return false
            if (!mTyreLayerTemp.contentEquals(other.mTyreLayerTemp)) return false
        } else if (other.mTyreLayerTemp != null) return false
        if (mTyreCarcassTemp != null) {
            if (other.mTyreCarcassTemp == null) return false
            if (!mTyreCarcassTemp.contentEquals(other.mTyreCarcassTemp)) return false
        } else if (other.mTyreCarcassTemp != null) return false
        if (mTyreRimTemp != null) {
            if (other.mTyreRimTemp == null) return false
            if (!mTyreRimTemp.contentEquals(other.mTyreRimTemp)) return false
        } else if (other.mTyreRimTemp != null) return false
        if (mTyreInternalAirTemp != null) {
            if (other.mTyreInternalAirTemp == null) return false
            if (!mTyreInternalAirTemp.contentEquals(other.mTyreInternalAirTemp)) return false
        } else if (other.mTyreInternalAirTemp != null) return false
        if (mCrashState != other.mCrashState) return false
        if (mAeroDamage != other.mAeroDamage) return false
        if (mEngineDamage != other.mEngineDamage) return false
        if (mAmbientTemperature != other.mAmbientTemperature) return false
        if (mTrackTemperature != other.mTrackTemperature) return false
        if (mRainDensity != other.mRainDensity) return false
        if (mWindSpeed != other.mWindSpeed) return false
        if (mWindDirectionX != other.mWindDirectionX) return false
        if (mWindDirectionY != other.mWindDirectionY) return false
        if (mCloudBrightness != other.mCloudBrightness) return false
        if (mSequenceNumber != other.mSequenceNumber) return false
        if (mWheelLocalPositionY != null) {
            if (other.mWheelLocalPositionY == null) return false
            if (!mWheelLocalPositionY.contentEquals(other.mWheelLocalPositionY)) return false
        } else if (other.mWheelLocalPositionY != null) return false
        if (mSuspensionTravel != null) {
            if (other.mSuspensionTravel == null) return false
            if (!mSuspensionTravel.contentEquals(other.mSuspensionTravel)) return false
        } else if (other.mSuspensionTravel != null) return false
        if (mSuspensionVelocity != null) {
            if (other.mSuspensionVelocity == null) return false
            if (!mSuspensionVelocity.contentEquals(other.mSuspensionVelocity)) return false
        } else if (other.mSuspensionVelocity != null) return false
        if (mAirPressure != null) {
            if (other.mAirPressure == null) return false
            if (!mAirPressure.contentEquals(other.mAirPressure)) return false
        } else if (other.mAirPressure != null) return false
        if (mEngineSpeed != other.mEngineSpeed) return false
        if (mEngineTorque != other.mEngineTorque) return false
        if (mWings != null) {
            if (other.mWings == null) return false
            if (!mWings.contentEquals(other.mWings)) return false
        } else if (other.mWings != null) return false
        if (mHandBrake != other.mHandBrake) return false
        if (mCurrentSector1Times != null) {
            if (other.mCurrentSector1Times == null) return false
            if (!mCurrentSector1Times.contentEquals(other.mCurrentSector1Times)) return false
        } else if (other.mCurrentSector1Times != null) return false
        if (mCurrentSector2Times != null) {
            if (other.mCurrentSector2Times == null) return false
            if (!mCurrentSector2Times.contentEquals(other.mCurrentSector2Times)) return false
        } else if (other.mCurrentSector2Times != null) return false
        if (mCurrentSector3Times != null) {
            if (other.mCurrentSector3Times == null) return false
            if (!mCurrentSector3Times.contentEquals(other.mCurrentSector3Times)) return false
        } else if (other.mCurrentSector3Times != null) return false
        if (mFastestSector1Times != null) {
            if (other.mFastestSector1Times == null) return false
            if (!mFastestSector1Times.contentEquals(other.mFastestSector1Times)) return false
        } else if (other.mFastestSector1Times != null) return false
        if (mFastestSector2Times != null) {
            if (other.mFastestSector2Times == null) return false
            if (!mFastestSector2Times.contentEquals(other.mFastestSector2Times)) return false
        } else if (other.mFastestSector2Times != null) return false
        if (mFastestSector3Times != null) {
            if (other.mFastestSector3Times == null) return false
            if (!mFastestSector3Times.contentEquals(other.mFastestSector3Times)) return false
        } else if (other.mFastestSector3Times != null) return false
        if (mFastestLapTimes != null) {
            if (other.mFastestLapTimes == null) return false
            if (!mFastestLapTimes.contentEquals(other.mFastestLapTimes)) return false
        } else if (other.mFastestLapTimes != null) return false
        if (mLastLapTimes != null) {
            if (other.mLastLapTimes == null) return false
            if (!mLastLapTimes.contentEquals(other.mLastLapTimes)) return false
        } else if (other.mLastLapTimes != null) return false
        if (mLapsInvalidated != null) {
            if (other.mLapsInvalidated == null) return false
            if (!mLapsInvalidated.contentEquals(other.mLapsInvalidated)) return false
        } else if (other.mLapsInvalidated != null) return false
        if (mRaceStates != null) {
            if (other.mRaceStates == null) return false
            if (!mRaceStates.contentEquals(other.mRaceStates)) return false
        } else if (other.mRaceStates != null) return false
        if (mPitModes != null) {
            if (other.mPitModes == null) return false
            if (!mPitModes.contentEquals(other.mPitModes)) return false
        } else if (other.mPitModes != null) return false
        if (mOrientations != null) {
            if (other.mOrientations == null) return false
            if (!mOrientations.contentDeepEquals(other.mOrientations)) return false
        } else if (other.mOrientations != null) return false
        if (mSpeeds != null) {
            if (other.mSpeeds == null) return false
            if (!mSpeeds.contentEquals(other.mSpeeds)) return false
        } else if (other.mSpeeds != null) return false
        if (mCarNames != null) {
            if (other.mCarNames == null) return false
            if (!mCarNames.contentEquals(other.mCarNames)) return false
        } else if (other.mCarNames != null) return false
        if (mCarClassNames != null) {
            if (other.mCarClassNames == null) return false
            if (!mCarClassNames.contentEquals(other.mCarClassNames)) return false
        } else if (other.mCarClassNames != null) return false
        if (mEnforcedPitStopLap != other.mEnforcedPitStopLap) return false
        if (mTranslatedTrackLocation != other.mTranslatedTrackLocation) return false
        if (mTranslatedTrackVariation != other.mTranslatedTrackVariation) return false
        if (mBrakeBias != other.mBrakeBias) return false
        if (mTurboBoostPressure != other.mTurboBoostPressure) return false
        if (mTyreCompound != null) {
            if (other.mTyreCompound == null) return false
            if (!mTyreCompound.contentEquals(other.mTyreCompound)) return false
        } else if (other.mTyreCompound != null) return false
        if (mPitSchedules != null) {
            if (other.mPitSchedules == null) return false
            if (!mPitSchedules.contentEquals(other.mPitSchedules)) return false
        } else if (other.mPitSchedules != null) return false
        if (mHighestFlagColours != null) {
            if (other.mHighestFlagColours == null) return false
            if (!mHighestFlagColours.contentEquals(other.mHighestFlagColours)) return false
        } else if (other.mHighestFlagColours != null) return false
        if (mHighestFlagReasons != null) {
            if (other.mHighestFlagReasons == null) return false
            if (!mHighestFlagReasons.contentEquals(other.mHighestFlagReasons)) return false
        } else if (other.mHighestFlagReasons != null) return false
        if (mNationalities != null) {
            if (other.mNationalities == null) return false
            if (!mNationalities.contentEquals(other.mNationalities)) return false
        } else if (other.mNationalities != null) return false
        if (mSnowDensity != other.mSnowDensity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mVersion?.hashCode() ?: 0
        result = 31 * result + (mBuildVersionNumber?.hashCode() ?: 0)
        result = 31 * result + (mGameState?.hashCode() ?: 0)
        result = 31 * result + (mSessionState?.hashCode() ?: 0)
        result = 31 * result + (mRaceState?.hashCode() ?: 0)
        result = 31 * result + (mViewedParticipantIndex ?: 0)
        result = 31 * result + (mNumParticipants ?: 0)
        result = 31 * result + (mParticipantInfo?.contentHashCode() ?: 0)
        result = 31 * result + (mUnfilteredThrottle?.hashCode() ?: 0)
        result = 31 * result + (mUnfilteredBrake?.hashCode() ?: 0)
        result = 31 * result + (mUnfilteredSteering?.hashCode() ?: 0)
        result = 31 * result + (mUnfilteredClutch?.hashCode() ?: 0)
        result = 31 * result + (mCarName?.hashCode() ?: 0)
        result = 31 * result + (mCarClassName?.hashCode() ?: 0)
        result = 31 * result + (mLapsInEvent?.hashCode() ?: 0)
        result = 31 * result + (mTrackLocation?.hashCode() ?: 0)
        result = 31 * result + (mTrackVariation?.hashCode() ?: 0)
        result = 31 * result + (mTrackLength?.hashCode() ?: 0)
        result = 31 * result + (mNumSectors ?: 0)
        result = 31 * result + (mLapInvalidated?.hashCode() ?: 0)
        result = 31 * result + (mBestLapTime?.hashCode() ?: 0)
        result = 31 * result + (mLastLapTime?.hashCode() ?: 0)
        result = 31 * result + (mCurrentTime?.hashCode() ?: 0)
        result = 31 * result + (mSplitTimeAhead?.hashCode() ?: 0)
        result = 31 * result + (mSplitTimeBehind?.hashCode() ?: 0)
        result = 31 * result + (mSplitTime?.hashCode() ?: 0)
        result = 31 * result + (mEventTimeRemaining?.hashCode() ?: 0)
        result = 31 * result + (mPersonalFastestLapTime?.hashCode() ?: 0)
        result = 31 * result + (mWorldFastestLapTime?.hashCode() ?: 0)
        result = 31 * result + (mCurrentSector1Time?.hashCode() ?: 0)
        result = 31 * result + (mCurrentSector2Time?.hashCode() ?: 0)
        result = 31 * result + (mCurrentSector3Time?.hashCode() ?: 0)
        result = 31 * result + (mFastestSector1Time?.hashCode() ?: 0)
        result = 31 * result + (mFastestSector2Time?.hashCode() ?: 0)
        result = 31 * result + (mFastestSector3Time?.hashCode() ?: 0)
        result = 31 * result + (mPersonalFastestSector1Time?.hashCode() ?: 0)
        result = 31 * result + (mPersonalFastestSector2Time?.hashCode() ?: 0)
        result = 31 * result + (mPersonalFastestSector3Time?.hashCode() ?: 0)
        result = 31 * result + (mWorldFastestSector1Time?.hashCode() ?: 0)
        result = 31 * result + (mWorldFastestSector2Time?.hashCode() ?: 0)
        result = 31 * result + (mWorldFastestSector3Time?.hashCode() ?: 0)
        result = 31 * result + (mHighestFlagColour?.hashCode() ?: 0)
        result = 31 * result + (mHighestFlagReason?.hashCode() ?: 0)
        result = 31 * result + (mPitMode?.hashCode() ?: 0)
        result = 31 * result + (mPitSchedule?.hashCode() ?: 0)
        result = 31 * result + (mCarFlags?.hashCode() ?: 0)
        result = 31 * result + (mOilTempCelsius?.hashCode() ?: 0)
        result = 31 * result + (mOilPressureKPa?.hashCode() ?: 0)
        result = 31 * result + (mWaterTempCelsius?.hashCode() ?: 0)
        result = 31 * result + (mWaterPressureKPa?.hashCode() ?: 0)
        result = 31 * result + (mFuelPressureKPa?.hashCode() ?: 0)
        result = 31 * result + (mFuelLevel?.hashCode() ?: 0)
        result = 31 * result + (mFuelCapacity?.hashCode() ?: 0)
        result = 31 * result + (mSpeed?.hashCode() ?: 0)
        result = 31 * result + (mRpm?.hashCode() ?: 0)
        result = 31 * result + (mMaxRPM?.hashCode() ?: 0)
        result = 31 * result + (mBrake?.hashCode() ?: 0)
        result = 31 * result + (mThrottle?.hashCode() ?: 0)
        result = 31 * result + (mClutch?.hashCode() ?: 0)
        result = 31 * result + (mSteering?.hashCode() ?: 0)
        result = 31 * result + (mGear ?: 0)
        result = 31 * result + (mNumGears ?: 0)
        result = 31 * result + (mOdometerKM?.hashCode() ?: 0)
        result = 31 * result + (mAntiLockActive?.hashCode() ?: 0)
        result = 31 * result + (mLastOpponentCollisionIndex ?: 0)
        result = 31 * result + (mLastOpponentCollisionMagnitude?.hashCode() ?: 0)
        result = 31 * result + (mBoostActive?.hashCode() ?: 0)
        result = 31 * result + (mBoostAmount?.hashCode() ?: 0)
        result = 31 * result + (mOrientation?.contentHashCode() ?: 0)
        result = 31 * result + (mLocalVelocity?.contentHashCode() ?: 0)
        result = 31 * result + (mWorldVelocity?.contentHashCode() ?: 0)
        result = 31 * result + (mAngularVelocity?.contentHashCode() ?: 0)
        result = 31 * result + (mLocalAcceleration?.contentHashCode() ?: 0)
        result = 31 * result + (mWorldAcceleration?.contentHashCode() ?: 0)
        result = 31 * result + (mExtentsCentre?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreFlags?.contentHashCode() ?: 0)
        result = 31 * result + (mTerrain?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreY?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreRPS?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreSlipSpeed?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreTemp?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreGrip?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreHeightAboveGround?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreLateralStiffness?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreWear?.contentHashCode() ?: 0)
        result = 31 * result + (mBrakeDamage?.contentHashCode() ?: 0)
        result = 31 * result + (mSuspensionDamage?.contentHashCode() ?: 0)
        result = 31 * result + (mBrakeTempCelsius?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreTreadTemp?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreLayerTemp?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreCarcassTemp?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreRimTemp?.contentHashCode() ?: 0)
        result = 31 * result + (mTyreInternalAirTemp?.contentHashCode() ?: 0)
        result = 31 * result + (mCrashState?.hashCode() ?: 0)
        result = 31 * result + (mAeroDamage?.hashCode() ?: 0)
        result = 31 * result + (mEngineDamage?.hashCode() ?: 0)
        result = 31 * result + (mAmbientTemperature?.hashCode() ?: 0)
        result = 31 * result + (mTrackTemperature?.hashCode() ?: 0)
        result = 31 * result + (mRainDensity?.hashCode() ?: 0)
        result = 31 * result + (mWindSpeed?.hashCode() ?: 0)
        result = 31 * result + (mWindDirectionX?.hashCode() ?: 0)
        result = 31 * result + (mWindDirectionY?.hashCode() ?: 0)
        result = 31 * result + (mCloudBrightness?.hashCode() ?: 0)
        result = 31 * result + (mSequenceNumber?.hashCode() ?: 0)
        result = 31 * result + (mWheelLocalPositionY?.contentHashCode() ?: 0)
        result = 31 * result + (mSuspensionTravel?.contentHashCode() ?: 0)
        result = 31 * result + (mSuspensionVelocity?.contentHashCode() ?: 0)
        result = 31 * result + (mAirPressure?.contentHashCode() ?: 0)
        result = 31 * result + (mEngineSpeed?.hashCode() ?: 0)
        result = 31 * result + (mEngineTorque?.hashCode() ?: 0)
        result = 31 * result + (mWings?.contentHashCode() ?: 0)
        result = 31 * result + (mHandBrake?.hashCode() ?: 0)
        result = 31 * result + (mCurrentSector1Times?.contentHashCode() ?: 0)
        result = 31 * result + (mCurrentSector2Times?.contentHashCode() ?: 0)
        result = 31 * result + (mCurrentSector3Times?.contentHashCode() ?: 0)
        result = 31 * result + (mFastestSector1Times?.contentHashCode() ?: 0)
        result = 31 * result + (mFastestSector2Times?.contentHashCode() ?: 0)
        result = 31 * result + (mFastestSector3Times?.contentHashCode() ?: 0)
        result = 31 * result + (mFastestLapTimes?.contentHashCode() ?: 0)
        result = 31 * result + (mLastLapTimes?.contentHashCode() ?: 0)
        result = 31 * result + (mLapsInvalidated?.contentHashCode() ?: 0)
        result = 31 * result + (mRaceStates?.contentHashCode() ?: 0)
        result = 31 * result + (mPitModes?.contentHashCode() ?: 0)
        result = 31 * result + (mOrientations?.contentDeepHashCode() ?: 0)
        result = 31 * result + (mSpeeds?.contentHashCode() ?: 0)
        result = 31 * result + (mCarNames?.contentHashCode() ?: 0)
        result = 31 * result + (mCarClassNames?.contentHashCode() ?: 0)
        result = 31 * result + (mEnforcedPitStopLap ?: 0)
        result = 31 * result + (mTranslatedTrackLocation?.hashCode() ?: 0)
        result = 31 * result + (mTranslatedTrackVariation?.hashCode() ?: 0)
        result = 31 * result + (mBrakeBias?.hashCode() ?: 0)
        result = 31 * result + (mTurboBoostPressure?.hashCode() ?: 0)
        result = 31 * result + (mTyreCompound?.contentHashCode() ?: 0)
        result = 31 * result + (mPitSchedules?.contentHashCode() ?: 0)
        result = 31 * result + (mHighestFlagColours?.contentHashCode() ?: 0)
        result = 31 * result + (mHighestFlagReasons?.contentHashCode() ?: 0)
        result = 31 * result + (mNationalities?.contentHashCode() ?: 0)
        result = 31 * result + (mSnowDensity?.hashCode() ?: 0)
        return result
    }
}