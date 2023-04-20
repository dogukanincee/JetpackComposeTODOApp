package com.dogukanincee.jetpackcomposetodoapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogukanincee.jetpackcomposetodoapp.data.TaskRepository
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    val tasks = taskRepository.getAllTasks()

    private val _showTaskForm = mutableStateOf(false)
    val showTaskForm get() = _showTaskForm.value
    fun setShowTaskForm(value: Boolean) {
        _showTaskForm.value = value
    }

    private val _selectedTask = mutableStateOf<Task?>(null)
    val selectedTask get() = _selectedTask.value
    private fun setSelectedTask(task: Task?) {
        _selectedTask.value = task
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun showTaskForm(task: Task?) {
        setSelectedTask(task)
        setShowTaskForm(true)
    }

    fun hideTaskForm() {
        setShowTaskForm(false)
    }
}