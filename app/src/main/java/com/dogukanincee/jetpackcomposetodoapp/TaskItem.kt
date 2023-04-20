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
