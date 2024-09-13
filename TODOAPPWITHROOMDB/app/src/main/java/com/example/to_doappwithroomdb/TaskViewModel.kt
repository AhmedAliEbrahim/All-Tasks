package com.example.to_doappwithroomdb
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val taskList: LiveData<List<Task>>

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        addTask(Task( 1, "Praying and reading Quran", "Commit to praying on time and reading the daily Quranic portion"))
        taskList = repository.getAllTasks()

    }

    fun addTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun removeTask(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
}