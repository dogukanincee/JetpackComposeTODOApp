package com.dogukanincee.jetpackcomposetodoapp

import com.dogukanincee.jetpackcomposetodoapp.dao.TaskDao
import com.dogukanincee.jetpackcomposetodoapp.data.TaskRepository
import com.dogukanincee.jetpackcomposetodoapp.data.TodoDatabase
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TaskRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: TodoDatabase
    private lateinit var taskDao: TaskDao
    private lateinit var taskRepository: TaskRepository

    @Mock
    private lateinit var mockTask: Task

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).allowMainThreadQueries().build()
        taskDao = database.taskDao()
        taskRepository = TaskRepository(taskDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun addTask() = runBlocking {
        taskRepository.addTask(mockTask)
        verify(mockTask).let { taskDao.insert(it) }
    }

    @Test
    fun updateTask() = runBlocking {
        taskRepository.updateTask(mockTask)
        verify(mockTask).let { taskDao.update(it) }
    }

    @Test
    fun deleteTask() = runBlocking {
        taskRepository.deleteTask(mockTask)
        verify(mockTask).let { taskDao.delete(it) }
    }

    @Test
    fun getAllTasks() {
        taskRepository.getAllTasks()
        verify(taskDao).getAllTasks()
    }
}
