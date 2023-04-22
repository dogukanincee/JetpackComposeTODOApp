package com.dogukanincee.jetpackcomposetodoapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task

/**
 * This composable represents a single item in the task list. It displays the task's title and
 * description in a Card, which is clickable to navigate to the task's detail screen.
 *
 * @param task The task to display.
 * @param onClick A callback function to be called when the user clicks on the task.
 */
@Composable
fun TaskItem(
    task: Task,
    onClick: (Task) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { onClick(task) },
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.h5)
            Text(text = task.description, style = MaterialTheme.typography.body1)
        }
    }
}