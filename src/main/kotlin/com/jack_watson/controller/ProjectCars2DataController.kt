package com.jack_watson.controller

import com.jack_watson.bean.response.Pc2DataResponse
import com.jack_watson.bean.TelemetryData
import com.jack_watson.database.InfluxDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pc2data")
class ProjectCars2DataController @Autowired constructor(
    private val influxDao: InfluxDao
){

    @PostMapping
    fun postProjectCars2Data(@RequestBody(required = true) pc2Data: TelemetryData): ResponseEntity<Pc2DataResponse> {
        return ResponseEntity(
            influxDao.processTelemetryData(pc2Data),
            HttpStatus.OK
        )
    }
}