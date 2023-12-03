package com.example.uf1_proyecto
import androidx.lifecycle.ViewModel

class TasksViewModel : ViewModel() {
    val tasksList = mutableListOf<Task>()
    fun deleteTask(task: Task) {
        tasksList.remove(task)

    }


}
