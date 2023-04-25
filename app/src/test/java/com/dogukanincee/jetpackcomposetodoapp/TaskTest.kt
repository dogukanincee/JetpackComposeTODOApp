package com.dogukanincee.jetpackcomposetodoapp

import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskTest {
    private val task = Task(1, "Test Title", "Test Description", true)

    @Test
    fun `test task id`() {
        assertEquals(1, task.id)
    }

    @Test
    fun `test task title`() {
        assertEquals("Test Title", task.title)
    }

    @Test
    fun `test task description`() {
        assertEquals("Test Description", task.description)
    }

    @Test
    fun `test task completed`() {
        assertEquals(true, task.completed)
    }
}