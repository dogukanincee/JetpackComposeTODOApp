package com.dogukanincee.jetpackcomposetodoapp

import android.app.Application
import com.dogukanincee.jetpackcomposetodoapp.data.TodoDatabase

/**
 * The TodoApplication class is a custom [Application] subclass that provides a single instance
 * of the [TodoDatabase] for the entire application. It is used to initialize the database and
 * provide a reference to the database object to other parts of the application.
 */
class TodoApplication : Application() {

    /**
     * A lazy reference to the [TodoDatabase] instance. The database is created when this property
     * is accessed for the first time, and then reused for subsequent accesses.
     */
    val database by lazy { TodoDatabase.getDatabase(this) }

}