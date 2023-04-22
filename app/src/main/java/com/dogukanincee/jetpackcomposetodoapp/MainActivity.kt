package com.dogukanincee.jetpackcomposetodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogukanincee.jetpackcomposetodoapp.data.TaskRepository

/**
 * This is the main activity for the to-do list app. It sets up the user interface and connects
 * to the app's database to retrieve and modify task data.
 */
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

    /**
     * Preview function for the default view of the app. This is used by the Android Studio preview
     * tool to show what the UI looks like.
     */
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TodoTheme {
            TaskListScreen(viewModel = viewModel())
        }
    }
}