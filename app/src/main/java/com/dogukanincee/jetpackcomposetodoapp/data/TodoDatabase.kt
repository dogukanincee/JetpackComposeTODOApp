package com.dogukanincee.jetpackcomposetodoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dogukanincee.jetpackcomposetodoapp.dao.TaskDao
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null

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
