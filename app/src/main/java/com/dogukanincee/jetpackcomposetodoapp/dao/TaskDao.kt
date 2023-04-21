package com.dogukanincee.jetpackcomposetodoapp.dao

import androidx.room.*
import com.dogukanincee.jetpackcomposetodoapp.data.entity.Task
import kotlinx.coroutines.flow.Flow

/**
 * Data access object (DAO) for the [Task] entity.
 */
@Dao
interface TaskDao {

    /**
     * Retrieves all tasks from the database in descending order by their ID.
     *
     * @return A [Flow] emitting a list of tasks.
     */
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<Task>>

    /**
     * Inserts a new task into the database, or replaces an existing one with the same ID.
     *
     * @param task The task to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    /**
     * Updates an existing task in the database.
     *
     * @param task The task to update.
     */
    @Update
    suspend fun update(task: Task)

    /**
     * Deletes a task from the database.
     *
     * @param task The task to delete.
     */
    @Delete
    suspend fun delete(task: Task)

}
