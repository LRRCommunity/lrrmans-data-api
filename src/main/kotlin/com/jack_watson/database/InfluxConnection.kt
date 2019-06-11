package com.jack_watson.database

import org.influxdb.InfluxDBFactory
import org.influxdb.dto.Point
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PreDestroy

/*
 * Singleton wrapper class for InfluxDB object
 * This ensures we only ever have one connection to InfluxDB, and it will never be closed
 */
@Component
class InfluxConnection
@Autowired constructor(
    private val influxDbConfig: InfluxDbConfiguration
) {
    private var influxDB = connectToInfluxDb()

    fun write(point: Point) {
        System.out.println(point)
        influxDB.write(point)
    }

    private fun connectToInfluxDb() =
        InfluxDBFactory.connect(influxDbConfig.url, influxDbConfig.username, influxDbConfig.password)
            .setDatabase(influxDbConfig.database)
            .setRetentionPolicy(influxDbConfig.retentionPolicy)

    //Close the connection on application shutdown
    @PreDestroy
    fun destroy() {
        influxDB.close()
    }
}