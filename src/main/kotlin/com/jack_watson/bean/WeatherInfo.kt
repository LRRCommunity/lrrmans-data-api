package com.jack_watson.bean

import org.influxdb.annotation.Column

//Used in race measurement
data class WeatherInfo (
    @Column(name = "ambientTemperature") val AmbientTemperature: Float?,
    @Column(name = "trackTemperature") val TrackTemperature: Float?,

    @Column(name = "rainDensity") val RainDensity: Float?,
    @Column(name = "snowDensity") val SnowDensity: Float?,

    @Column(name = "windSpeed") val WindSpeed: Float?,
    @Column(name = "windDirectionX") val WindDirectionX: Float?,
    @Column(name = "windDirectionY") val WindDirectionY: Float?,

    @Column(name = "cloudBrightness") val CloudBrightness: Float?
)