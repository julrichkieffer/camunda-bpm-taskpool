package io.holunda.polyflow.taskpool.collector

import io.holunda.polyflow.taskpool.collector.task.assigner.ProcessVariableTaskAssignerMapping
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.NestedConfigurationProperty

/**
 * Configuration properties of Camunda Taskpool collector.
 * Not using constructor binding here, because we need the springApplicationName.
 */
@ConfigurationProperties(prefix = "polyflow.integration.collector.camunda")
class CamundaTaskpoolCollectorProperties(

  /**
   * Denotes the (logical) name of the process application, defaults to "spring.application.name".
   */
  @Value("\${spring.application.name:unset-application-name}")
  var applicationName: String,

  /**
   * Task collector properties.
   */
  @NestedConfigurationProperty
  var task: CamundaTaskCollectorProperties = CamundaTaskCollectorProperties(),
  /**
   * Process definition collector properties.
   */
  @NestedConfigurationProperty
  var processDefinition: CamundaProcessDefinitionCollectorProperties = CamundaProcessDefinitionCollectorProperties(),

  /**
   * Process instance collector properties.
   */
  @NestedConfigurationProperty
  var processInstance: CamundaProcessInstanceCollectorProperties = CamundaProcessInstanceCollectorProperties(),

  /**
   * Process variable collector properties.
   */
  @NestedConfigurationProperty
  var processVariable: CamundaProcessVariableProperties = CamundaProcessVariableProperties()
)

/**
 * Task collector properties.
 */
@ConstructorBinding
data class CamundaTaskCollectorProperties(
  /**
   * Task enricher properties.
   */
  @NestedConfigurationProperty
  val enricher: TaskCollectorEnricherProperties = TaskCollectorEnricherProperties(),

  /**
   * Task assigner properties.
   */
  @NestedConfigurationProperty
  val assigner: TaskAssignerProperties = TaskAssignerProperties(),

  /**
   * Flag to enable or disable the collector.
   */
  val enabled: Boolean = true
)

/**
 * Process variable properties.
 */
@ConstructorBinding
data class CamundaProcessVariableProperties(
  /**
   * Enabled by default.
   */
  val enabled: Boolean = true
)

/**
 * Task command enricher properties.
 */
@ConstructorBinding
data class TaskCollectorEnricherProperties(
  /**
   * Type of enricher, see TaskCollectorEnricherType values.
   */
  val type: TaskCollectorEnricherType = TaskCollectorEnricherType.processVariables,
)

/**
 * Type of enricher.
 */
enum class TaskCollectorEnricherType {
  /**
   * No enrichment.
   */
  no,

  /**
   * Enrich with process variables.
   */
  processVariables,

  /**
   * Custom enricher.
   */
  custom
}

enum class TaskAssignerType {
  /**
   * Empty assigner, use information from Camunda task.
   */
  no,

  /**
   * Use process variables for assignment information.
   */
  processVariables,

  /**
   * Custom assigner.
   */
  custom
}

/**
 * Properties controlling the transfer of process definitions deployments.
 */
@ConstructorBinding
data class CamundaProcessDefinitionCollectorProperties(

  /**
   * Disable by default.
   */
  val enabled: Boolean = false
)


/**
 * Properties controlling the transfer of process instance.
 */
@ConstructorBinding
data class CamundaProcessInstanceCollectorProperties(

  /**
   * Enabled by default.
   */
  val enabled: Boolean = true
)

/**
 * Properties to set up the task assigner.
 */
@ConstructorBinding
data class TaskAssignerProperties(
  /**
   * Configures assigner type.
   */
  val type: TaskAssignerType = TaskAssignerType.no,
  /**
   * Process variable carrying the assignee information used by the process variable task assigner.
   */
  val assignee: String? = null,
  /**
   * Process variable carrying the candidateUsers information used by the process variable task assigner.
   */
  val candidateUsers: String? = null,
  /**
   * Process variable carrying the candidateGroups information used by the process variable task assigner.
   */
  val candidateGroups: String? = null
) {
  fun toMapping(): ProcessVariableTaskAssignerMapping = ProcessVariableTaskAssignerMapping(
    assignee = assignee,
    candidateUsers = candidateUsers,
    candidateGroups = candidateGroups,
  )
}
