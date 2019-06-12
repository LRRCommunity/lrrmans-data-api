package com.jack_watson.controller

import com.jack_watson.database.InfluxDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/driver")
class DriverDataController @Autowired constructor(
    private val influxDao: InfluxDao
) {

    @GetMapping
    fun getDataByDriver(@RequestParam(required = true) name: String) = influxDao.getDataByDriver(name)
}