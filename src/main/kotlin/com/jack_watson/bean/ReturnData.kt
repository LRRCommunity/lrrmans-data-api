package com.jack_watson.bean

data class ReturnData (
    val DriverName: String,

    // I had to add this to get the json mapper to deserialize the ProjectCars2DataController.postProjectCars2Data
    // request
    val anotherThing: Number?
)
