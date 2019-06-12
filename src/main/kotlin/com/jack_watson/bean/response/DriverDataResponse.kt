package com.jack_watson.bean.response

import com.jack_watson.bean.lapTracker.Lap

data class DriverDataResponse(
    var driverName: String,
    var lapsCompleted: Number, //Number of laps that were finished by the driver

    //Number of seconds taken for each sector.
    // Index 0 = Sector 1
    // Index 1 = Sector 2
    // Index 2 = Sector 3
    var personalBestSectorTimes: Array<Double>,
    var personalBestLap: Lap?,

    //Time driven in number of seconds
    var timeDriven: Double,
    var topSpeed: Double
)