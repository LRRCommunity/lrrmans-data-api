package com.jack_watson

import com.jack_watson.database.InfluxDbConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableConfigurationProperties(InfluxDbConfiguration::class)
@ComponentScan
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}