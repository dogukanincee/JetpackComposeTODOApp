package com.dogukanincee.jetpackcomposetodoapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogukanincee.jetpackcomposetodoapp.data.TaskRepository
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import kotlinx.coroutines.launch

/**
 * The ViewModel class for managing task data and state in the task list screen. It provides
 * functions for adding, updating, and deleting tasks, as well as showing and hiding the task form
 * dialog.
 *
 * @property taskRepository An instance of the TaskRepository, which provides access to the task
 * database and DAO.
 */
class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    /**
     * A StateFlow that emits a list of all tasks in the database. The list is automatically updated
     * whenever the database changes.
     */
    val tasks = taskRepository.getAllTasks()

    private val _showTaskForm = mutableStateOf(false)
    val showTaskForm get() = _showTaskForm.value

    /**
     * A function for setting the value of the _showTaskForm StateFlow. When called with `true`, it
     * displays the task form dialog, and when called with `false`, it hides the dialog.
     */
    fun setShowTaskForm(value: Boolean) {
        _showTaskForm.value = value
    }

    private val _selectedTask = mutableStateOf<Task?>(null)
    val selectedTask get() = _selectedTask.value

    /**
     * A private function for setting the value of the _selectedTask StateFlow. It is called by the
     * `showTaskForm` function to set the task that is being edited or created.
     */
    private fun setSelectedTask(task: Task?) {
        _selectedTask.value = task
    }

    /**
     * A function for adding a new task to the database. It runs on a background thread using
     * viewModelScope.launch and suspends until the task is added.
     *
     * @param task The task to be added.
     */
    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }

    /**
     * A function for updating an existing task in the database. It runs on a background thread
     * using viewModelScope.launch and suspends until the task is updated.
     *
     * @param task The task to be updated.
     */
    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }

    /**
     * A function for deleting a task from the database. It runs on a background thread using
     * viewModelScope.launch and suspends until the task is deleted.
     *
     * @param task The task to be deleted.
     */
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    /**
     * A function for showing the task form dialog. It sets the _selectedTask StateFlow to the task
     * being edited or created, and sets the _showTaskForm StateFlow to `true`.
     *
     * @param task The task being edited or created, or `null` if a new task is being created.
     */
    fun showTaskForm(task: Task?) {
        setSelectedTask(task)
        setShowTaskForm(true)
    }

    /**
     * A function for hiding the task form dialog. It sets the _showTaskForm StateFlow to `false`.
     */
    fun hideTaskForm() {
        setShowTaskForm(false)
    }
}