package io.holunda.polyflow.datapool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(DataEntrySenderProperties::class)
class PropertiesTestApplication
