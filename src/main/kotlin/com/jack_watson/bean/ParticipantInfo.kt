package com.jack_watson.bean

import com.jack_watson.database.InfluxUtils
import org.influxdb.annotation.Column
import org.influxdb.annotation.Measurement
import org.influxdb.dto.Point

@Measurement(name = "participant")
data class ParticipantInfo(
    @Column(name = "active", tag = true) val IsActive: String?,
    @Column(name = "name", tag = true) val Name: String?,

    //TODO: Add this
    val WorldPosition: Vector<Float>?,

    @Column(name = "currentLapDistance") val CurrentLapDistance: Float?,
    @Column(name = "racePosition") val RacePosition: Long?,
    @Column(name = "lapsCompleted") val LapsCompleted: Long?,
    @Column(name = "currentLap") val CurrentLap: Long?,
    @Column(name = "currentSector") val CurrentSector: Int
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
            if (WorldPosition != other.WorldPosition) return false
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
        result = 31 * result + (WorldPosition?.hashCode() ?: 0)
        result = 31 * result + (CurrentLapDistance?.hashCode() ?: 0)
        result = 31 * result + (RacePosition?.hashCode() ?: 0)
        result = 31 * result + (LapsCompleted?.hashCode() ?: 0)
        result = 31 * result + (CurrentLap?.hashCode() ?: 0)
        result = 31 * result + (CurrentSector?.hashCode() ?: 0)
        return result
    }

    fun addVectorsToPoint(pointBuilder : Point.Builder) : Point.Builder =
         InfluxUtils.addVectorToPoint(pointBuilder, WorldPosition!!, "worldPosition")

}
