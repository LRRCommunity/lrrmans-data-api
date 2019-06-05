package com.jack_watson.bean.lapTracker

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "lap-tracker")
class LapTrackerConfiguration {

    //Kotlin config @Value requires a placeholder, so the value is set at some point
    @Value("\${file-name:}")
    lateinit var fileName: String

}