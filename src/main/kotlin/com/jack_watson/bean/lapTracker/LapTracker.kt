package com.jack_watson.bean.lapTracker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jack_watson.bean.TelemetryData
import com.jack_watson.database.InfluxConnection
import org.influxdb.dto.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.TimeUnit
import javax.annotation.PreDestroy

@Component
class LapTracker
@Autowired constructor(
    private val lapTrackerConfig: LapTrackerConfiguration
) {

    private lateinit var lapsByUser: ConcurrentMap<String, Lap>

    private val mapper = jacksonObjectMapper()

    init {
        val lapTrackerSave = File(lapTrackerConfig.fileName)
        lapsByUser = if (lapTrackerSave.exists() && lapTrackerSave.canRead()) {
            mapper.readValue<ConcurrentHashMap<String, Lap>>(lapTrackerSave)
        } else {
            ConcurrentHashMap()
        }
    }

    fun writeLap(influxConnection: InfluxConnection, telemetryData: TelemetryData) {
        val participantIndex = telemetryData.ViewedParticipantIndex
        val sourceUser = telemetryData.SourceUser

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

        if (writePoint && lap.overallLapNumber != 0L) {
            influxConnection.write(
                Point.measurementByPOJO(Lap::class.java)
                    .time(telemetryData.getTimestampEpoch(), TimeUnit.MILLISECONDS)
                    .addFieldsFromPOJO(lap)
                    .tag("sourceUser", telemetryData.SourceUser)
                    .build()
            )

            synchronized(this) {
                mapper.writeValue(File(lapTrackerConfig.fileName), lapsByUser)
            }
        }
    }

    private fun isNewLap(sourceUser: String, lastLapCompleted: Number) =
        lapsByUser[sourceUser]?.lapNumber != lastLapCompleted

    private fun isNewUser(sourceUser: String) =
        !lapsByUser.contains(sourceUser)

}

