package com.jack_watson.bean

data class ParticipantInfo(
    val IsActive: Boolean?,
    val Name: String?,
    val WorldPosition: Array<Float>?,
    val CurrentLapDistance: Float?,
    val RacePosition: Long?,
    val LapsCompleted: Long?,
    val CurrentLap: Long?,
    val CurrentSector: Int?
) {
    //Generated equals function
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ParticipantInfo

        if (IsActive != other.IsActive) return false
        if (Name != other.Name) return false
        if (WorldPosition != null) {
            if (other.WorldPosition == null) return false
            if (!WorldPosition.contentEquals(other.WorldPosition)) return false
        } else if (other.WorldPosition != null) return false
        if (CurrentLapDistance != other.CurrentLapDistance) return false
        if (RacePosition != other.RacePosition) return false
        if (LapsCompleted != other.LapsCompleted) return false
        if (CurrentLap != other.CurrentLap) return false
        if (CurrentSector != other.CurrentSector) return false

        return true
    }

    //Generated hashCode function
    override fun hashCode(): Int {
        var result = IsActive.hashCode()
        result = 31 * result + (Name?.hashCode() ?: 0)
        result = 31 * result + (WorldPosition?.contentHashCode() ?: 0)
        result = 31 * result + (CurrentLapDistance?.hashCode() ?: 0)
        result = 31 * result + (RacePosition?.hashCode() ?: 0)
        result = 31 * result + (LapsCompleted?.hashCode() ?: 0)
        result = 31 * result + (CurrentLap?.hashCode() ?: 0)
        result = 31 * result + (CurrentSector?.hashCode() ?: 0)
        return result
    }
}
