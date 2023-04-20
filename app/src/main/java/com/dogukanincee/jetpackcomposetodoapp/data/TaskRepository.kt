package com.dogukanincee.jetpackcomposetodoapp.data

import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import com.dogukanincee.jetpackcomposetodoapp.dao.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }
}
