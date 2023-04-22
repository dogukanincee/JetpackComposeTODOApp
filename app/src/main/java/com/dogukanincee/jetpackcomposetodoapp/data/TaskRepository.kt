package com.dogukanincee.jetpackcomposetodoapp.data

import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import com.dogukanincee.jetpackcomposetodoapp.dao.TaskDao
import kotlinx.coroutines.flow.Flow

/**
 * This class is responsible for managing data operations for tasks, including retrieving,
 * adding, updating, and deleting tasks.
 *
 * @param taskDao An instance of the TaskDao interface, which provides methods for accessing
 * the task data stored in the database.
 */
class TaskRepository(private val taskDao: TaskDao) {

    /**
     * Returns a Flow that emits a list of all tasks in the database whenever the data changes.
     */
    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    /**
     * Inserts a new task into the database.
     *
     * @param task The task to insert.
     */
    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    /**
     * Updates an existing task in the database.
     *
     * @param task The task to update.
     */
    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    /**
     * Deletes a task from the database.
     *
     * @param task The task to delete.
     */
    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }
}