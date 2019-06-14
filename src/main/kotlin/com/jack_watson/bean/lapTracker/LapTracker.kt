package com.jack_watson.bean.lapTracker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jack_watson.bean.TelemetryData
import com.jack_watson.database.InfluxConnection
import com.jack_watson.database.InfluxUtils.Companion.addRequiredFieldsToPointBuilder
import org.influxdb.dto.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

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

        val participant = telemetryData.Participants[participantIndex]
        val participantEx = telemetryData.ParticipantsEx[participantIndex]

        val lap = Lap(
            participant.LapsCompleted as Long,
            participant.LapsCompleted,
            participantEx.LastLapTime as Float,
            participant.RacePosition as Long,
            telemetryData.PitMode != "0",
            telemetryData.CarName,
            telemetryData.CarClassName,
            participant.CurrentSector,
            mutableListOf(
                telemetryData.CurrentSector1Time,
                telemetryData.CurrentSector2Time,
                telemetryData.CurrentSector3Time
            )
        )
        writeLap(influxConnection, lap, telemetryData)
    }

    //Writes the lap out to Influx & the map if needed
    private fun writeLap(influxConnection: InfluxConnection, lap: Lap, telemetryData: TelemetryData) {
        var writePointToDatabase = false
        var writePointToFile = false
        synchronized(this) {
            if (isNewUser(telemetryData.SourceUser)) {
                lapsByUser[telemetryData.SourceUser] = lap
                writePointToDatabase = true
                writePointToFile = true
            } else if (isNewLap(telemetryData.SourceUser, lap.lapNumber as Number)) {
                lap.overallLapNumber = lapsByUser[telemetryData.SourceUser]!!.overallLapNumber + 1
                lap.sectorTimes[0] = lapsByUser[telemetryData.SourceUser]!!.sectorTimes[0]
                lapsByUser[telemetryData.SourceUser] = lap
                writePointToDatabase = true
                writePointToFile = true
            } else if (isNewSector(telemetryData.SourceUser, lap.currentSector)
                && savedCurrentSectorIsOne(telemetryData.SourceUser)
            ) {
                //If currentSector changes from 1 to 2, we need to save the sector 1 data
                //If we don't save it, we will lose the sector data on a new lap
                lapsByUser[telemetryData.SourceUser]!!.sectorTimes[0] = lap.sectorTimes[0]
                writePointToFile = true
            }
        }

        //If the current session's Lap Number is 0 we don't care about it, just wait until a lap completes
        if (lap.lapNumber > 0 && writePointToDatabase) {
            influxConnection.write(
                addRequiredFieldsToPointBuilder(
                    lap.addSectorTimesToPointBuilder(
                        Point.measurementByPOJO(Lap::class.java)
                            .addFieldsFromPOJO(lap)
                    ),
                    telemetryData
                ).build()
            )
        }

        if (lap.lapNumber > 0 && writePointToFile) {
            synchronized(this) {
                mapper.writeValue(File(lapTrackerConfig.fileName), lapsByUser)
            }
        }
    }

    private fun isNewLap(sourceUser: String, lastLapCompleted: Number) =
        lapsByUser[sourceUser]?.lapNumber != lastLapCompleted

    private fun isNewUser(sourceUser: String) =
        !lapsByUser.contains(sourceUser)

    private fun isNewSector(sourceUser: String, currentSector: Int) =
        lapsByUser[sourceUser]?.currentSector != currentSector

    private fun savedCurrentSectorIsOne(sourceUser: String) =
        lapsByUser[sourceUser]?.currentSector == 0
}

