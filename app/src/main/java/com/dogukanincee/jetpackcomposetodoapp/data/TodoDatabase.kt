package com.dogukanincee.jetpackcomposetodoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dogukanincee.jetpackcomposetodoapp.dao.TaskDao
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task

/**
 * This class is the main entry point for interacting with the app's database, which stores
 * information about the user's to-do list.
 */
@Database(entities = [Task::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    /**
     * Returns an instance of the TaskDao interface, which provides methods for accessing and
     * modifying task data in the database.
     */
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null

        /**
         * Returns an instance of the TodoDatabase class, creating it if it doesn't exist.
         *
         * @param context The context in which to create the database.
         */
        fun getDatabase(context: Context): TodoDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}