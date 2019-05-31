package com.jack_watson.database

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "influxdb")
class InfluxDbConfiguration {


    //Kotlin config @Value requires a placeholder, so the value is set at some point
    @Value("\${url:}")
    lateinit var url: String

    @Value("\${username:}")
    lateinit var username: String

    @Value("\${password:}")
    lateinit var password: String

    @Value("\${database:}")
    lateinit var database: String

    @Value("\${retentionPolicy:}")
    lateinit var retentionPolicy: String

}