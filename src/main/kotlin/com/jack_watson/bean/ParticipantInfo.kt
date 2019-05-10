package com.jack_watson.bean

data class ParticipantInfo(
    val mIsActive: Boolean,
    val mName: String?,
    val mWorldPosition: Array<Float>?,
    val mCurrentLapDistance: Float?,
    val mRacePosition: Long?,
    val mLapsCompleted: Long?,
    val mCurrentLap: Long?,
    val mCurrentSector: Int
) {
    //Generated equals function
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ParticipantInfo

        if (mIsActive != other.mIsActive) return false
        if (mName != other.mName) return false
        if (mWorldPosition != null) {
            if (other.mWorldPosition == null) return false
            if (!mWorldPosition.contentEquals(other.mWorldPosition)) return false
        } else if (other.mWorldPosition != null) return false
        if (mCurrentLapDistance != other.mCurrentLapDistance) return false
        if (mRacePosition != other.mRacePosition) return false
        if (mLapsCompleted != other.mLapsCompleted) return false
        if (mCurrentLap != other.mCurrentLap) return false
        if (mCurrentSector != other.mCurrentSector) return false

        return true
    }

    //Generated hashCode function
    override fun hashCode(): Int {
        var result = mIsActive.hashCode()
        result = 31 * result + (mName?.hashCode() ?: 0)
        result = 31 * result + (mWorldPosition?.contentHashCode() ?: 0)
        result = 31 * result + (mCurrentLapDistance?.hashCode() ?: 0)
        result = 31 * result + (mRacePosition?.hashCode() ?: 0)
        result = 31 * result + (mLapsCompleted?.hashCode() ?: 0)
        result = 31 * result + (mCurrentLap?.hashCode() ?: 0)
        result = 31 * result + mCurrentSector
        return result
    }
}
