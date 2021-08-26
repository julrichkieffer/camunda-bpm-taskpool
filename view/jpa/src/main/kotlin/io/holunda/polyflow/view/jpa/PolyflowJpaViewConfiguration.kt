package io.holunda.polyflow.view.jpa

import io.holunda.polyflow.view.jpa.data.DataEntryEntity
import io.holunda.polyflow.view.jpa.data.DataEntryRepository
import io.holunda.polyflow.view.jpa.process.ProcessDefinitionEntity
import io.holunda.polyflow.view.jpa.process.ProcessDefinitionRepository
import io.holunda.polyflow.view.jpa.task.TaskEntity
import io.holunda.polyflow.view.jpa.task.TaskRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableConfigurationProperties(PolyflowJpaViewProperties::class)
@ComponentScan
@EntityScan(
  basePackageClasses = [
    DataEntryEntity::class,
    ProcessDefinitionEntity::class,
    TaskEntity::class
  ]
)
@EnableJpaRepositories(
  basePackageClasses = [
    DataEntryRepository::class,
    ProcessDefinitionRepository::class,
    TaskRepository::class
  ]
)
@Configuration
class PolyflowJpaViewConfiguration
