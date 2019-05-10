package com.jack_watson.controller

import com.jack_watson.bean.ProjectCars2Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pc2data")
class ProjectCars2DataController {

    @PostMapping("/")
    fun postProjectCars2Data(@RequestBody(required = true) pc2Data: ProjectCars2Data): ResponseEntity<ProjectCars2Data> {
        System.out.println(pc2Data)
        return ResponseEntity<ProjectCars2Data>(pc2Data, HttpStatus.OK)
    }
}