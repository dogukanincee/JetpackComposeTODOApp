package com.dogukanincee.jetpackcomposetodoapp

import android.app.Application
import com.dogukanincee.jetpackcomposetodoapp.data.TodoDatabase

class TodoApplication : Application() {

    val database by lazy { TodoDatabase.getDatabase(this) }

}
