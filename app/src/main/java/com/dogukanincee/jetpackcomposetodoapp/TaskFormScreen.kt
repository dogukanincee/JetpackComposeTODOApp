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

@Composable
fun TaskFormScreen(
    task: Task?,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = if (task == null) stringResource(id = R.string.create_task)
            else stringResource(id = R.string.edit_task),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = stringResource(id = R.string.task_title)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = stringResource(id = R.string.task_description)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { onCancel() }, modifier = Modifier.padding(end = 8.dp)) {
                Text(text = stringResource(id = R.string.cancel))
            }
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
