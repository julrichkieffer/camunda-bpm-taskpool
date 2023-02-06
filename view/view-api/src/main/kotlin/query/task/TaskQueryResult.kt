package io.holunda.polyflow.view.query.task

import io.holunda.polyflow.view.Task
import io.holunda.polyflow.view.query.PageableSortableQuery
import io.holunda.polyflow.view.query.QueryResult

/**
 * Result of query for multiple tasks.
 */
data class TaskQueryResult(
  override val elements: List<Task>,
  override val totalElementCount: Int = elements.size
) : QueryResult<Task, TaskQueryResult>(elements = elements, totalElementCount = totalElementCount) {
  override fun slice(query: PageableSortableQuery) = this.copy(elements = super.slice(query).elements)
}
