package com.example.roomdatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao: TaskDao = AppDatabase.getDatabase(application).taskDao()

    fun insertTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskDao.insertTask(task)
    }

    fun getAllTasks() = taskDao.getAllTasks()
}
