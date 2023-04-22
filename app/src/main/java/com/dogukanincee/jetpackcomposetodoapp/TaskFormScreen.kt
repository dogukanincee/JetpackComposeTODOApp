package com.dogukanincee.jetpackcomposetodoapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import com.example.todo.R

/**
 * This screen allows the user to create or edit a task. It contains input fields for the task's
 * title and description, as well as buttons to save or cancel the operation.
 *
 * @param task The task to edit, or null if creating a new task.
 * @param onSave A callback function to be called when the user saves the task.
 * @param onCancel A callback function to be called when the user cancels the operation.
 * @param viewModel An instance of the TaskViewModel, which manages task data and state.
 */
@Composable
fun TaskFormScreen(
    task: Task?,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    // Initialize state variables for the task's title and description
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Display the appropriate screen title depending on whether we're creating a new task
        // or editing an existing one
        Text(
            text = if (task == null) stringResource(id = R.string.create_task)
            else stringResource(id = R.string.edit_task),
            style = MaterialTheme.typography.h5
        )

        // Add vertical space between the title and the input fields
        Spacer(modifier = Modifier.height(16.dp))

        // Input field for the task's title
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = stringResource(id = R.string.task_title)) },
            modifier = Modifier.fillMaxWidth()
        )

        // Add vertical space between the title and description input fields
        Spacer(modifier = Modifier.height(16.dp))

        // Input field for the task's description
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = stringResource(id = R.string.task_description)) },
            modifier = Modifier.fillMaxWidth()
        )

        // Add vertical space between the input fields and the buttons
        Spacer(modifier = Modifier.height(16.dp))

        // Row of buttons to save or cancel the operation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            // Button to cancel the operation
            TextButton(onClick = { onCancel() }, modifier = Modifier.padding(end = 8.dp)) {
                Text(text = stringResource(id = R.string.cancel))
            }

            // Button to save the task
            Button(onClick = {
                if (task == null) {
                    viewModel.addTask(Task(title = title, description = description))
                } else {
                    viewModel.updateTask(task.copy(title = title, description = description))
                }
                onSave()
            }) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}