package com.jack_watson.bean

data class WeatherInfo (
    val AmbientTemperature: Float?,
    val TrackTemperature: Float?,

    val RainDensity: Float?,
    val SnowDensity: Float?,

    val WindSpeed: Float?,
    val WindDirectionX: Float?,
    val WindDirectionY: Float?,

    val CloudBrightness: Float?
)