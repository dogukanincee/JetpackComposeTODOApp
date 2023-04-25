package com.dogukanincee.jetpackcomposetodoapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dogukanincee.jetpackcomposetodoapp.dao.TaskDao
import com.dogukanincee.jetpackcomposetodoapp.data.TodoDatabase
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDatabaseTest {
    private lateinit var database: TodoDatabase
    private lateinit var taskDao: TaskDao

    @Before
    fun setUp() {
        // Use an in-memory database for testing
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).build()

        taskDao = database.taskDao()
    }

    @After
    fun tearDown() {
        // Close the database after each test
        database.close()
    }

    @Test
    fun testInsertAndRetrieveTask() = runBlocking {
        // Create a task and insert it into the database
        val task = Task(title = "Test Task", description = "This is a test task.")
        taskDao.addTask(task)

        // Retrieve the task from the database and ensure it has the same values
        val retrievedTask = taskDao.getTask(task.id)
        assertEquals(task.title, retrievedTask?.title)
        assertEquals(task.description, retrievedTask?.description)
    }
}
