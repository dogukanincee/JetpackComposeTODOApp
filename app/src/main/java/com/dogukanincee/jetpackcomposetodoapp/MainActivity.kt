package com.dogukanincee.jetpackcomposetodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogukanincee.jetpackcomposetodoapp.data.TaskRepository

class MainActivity : ComponentActivity() {

    private val repository by lazy { TaskRepository((application as TodoApplication).database.taskDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TaskListScreen(viewModel = viewModel())
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TodoTheme {
            TaskListScreen(viewModel = viewModel())
        }
    }
}