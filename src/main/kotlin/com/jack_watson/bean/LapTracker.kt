package com.jack_watson.bean

import com.jack_watson.database.InfluxConnection
import org.influxdb.dto.Point
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.TimeUnit

@Component
class LapTracker {

    private val lapsByUser: ConcurrentMap<String, Lap> = ConcurrentHashMap()

    fun writeLap(influxConnection: InfluxConnection, telemetryData: TelemetryData) {
        val participantIndex = telemetryData.ViewedParticipantIndex
        val sourceUser = telemetryData.SourceUser
        //System.out.println("${telemetryData.Participants[participantIndex]}\n${telemetryData.ParticipantsEx[participantIndex]}")

        val participant = telemetryData.Participants[participantIndex]
        val participantEx = telemetryData.ParticipantsEx[participantIndex]

        val lap = Lap(
            participant.LapsCompleted as Long,
            participant.LapsCompleted,
            participantEx.LastLapTime as Float,
            participant.RacePosition as Long,
            telemetryData.PitMode != "0",
            telemetryData.CarName,
            telemetryData.CarClassName
        )

        var writePoint = false
        synchronized(this) {
            if (isNewUser(sourceUser)) {
                lapsByUser[sourceUser] = lap
                writePoint = true
            } else if (isNewLap(sourceUser, telemetryData.Participants[participantIndex].LapsCompleted as Number)) {
                lap.overallLapNumber = lapsByUser[sourceUser]!!.overallLapNumber + 1
                lapsByUser[sourceUser] = lap
                writePoint = true
            }
        }

        if (writePoint) {
            influxConnection.write(
                Point.measurementByPOJO(Lap::class.java)
                    .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                    .addFieldsFromPOJO(lap)
                    .tag("sourceUser", telemetryData.SourceUser)
                    .build()
            )
        }
    }

    private fun isNewLap(sourceUser: String, lastLapCompleted: Number) =
        lapsByUser[sourceUser]?.lapNumber != lastLapCompleted

    private fun isNewUser(sourceUser: String) =
        !lapsByUser.contains(sourceUser)

}

