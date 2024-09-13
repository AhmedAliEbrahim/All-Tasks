package com.example.to_doappwithroomdb
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}