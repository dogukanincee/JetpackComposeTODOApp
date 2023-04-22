package com.dogukanincee.jetpackcomposetodoapp

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import com.example.todo.R

/**
 * This composable displays a list of tasks in a vertical scrollable list. It uses a LazyColumn to
 * efficiently render only the visible items in the list, and provides an "Add Task" button in a
 * FloatingActionButton at the bottom right corner of the screen.
 *
 * @param viewModel An instance of the TaskViewModel, which manages task data and state.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskListScreen(viewModel: TaskViewModel = viewModel()) {
    // Collect the list of tasks from the view model using a StateFlow
    val tasks by viewModel.tasks.collectAsState(emptyList())

    // Scaffold composable provides a Material Design layout for the screen, including a top app bar
    // and a floating action button for adding tasks
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.showTaskForm(null) }) {
                Icon(
                    Icons.Filled.Add, null
                )
            }
        }
    ) {
        if (tasks.isEmpty()) {
            // Display a message if there are no tasks to show
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Text(
                    text = stringResource(id = R.string.no_tasks),
                    style = MaterialTheme.typography.h6
                )
            }
        } else {
            // Use a LazyColumn to efficiently render only the visible items in the list
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(tasks) { task ->
                    TaskItem(task = task, onClick = { viewModel.showTaskForm(task) })
                }
            }
        }
    }
}

/**
 * This composable represents a single item in the task list. It displays the task's title and
 * description in a Card, which is clickable to navigate to the task's detail screen.
 *
 * @param task The task to display.
 * @param onClick A callback function to be called when the user clicks on the task.
 */
@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = task.description, style = MaterialTheme.typography.body1)
        }
    }
}