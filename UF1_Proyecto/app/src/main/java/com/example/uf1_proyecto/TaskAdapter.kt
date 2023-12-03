package com.example.uf1_proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskNameTextView: TextView = itemView.findViewById(R.id.taskNameTextView)
        val taskDateTextView: TextView = itemView.findViewById(R.id.taskDateTextView)
        val taskDescriptionTextView: TextView = itemView.findViewById(R.id.taskDescriptionTextView)
        val deleteTaskButton: Button = itemView.findViewById(R.id.deleteTaskButton) // Asegúrate de tener el botón en tu layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.taskNameTextView.text = currentTask.taskName
        holder.taskDateTextView.text = currentTask.taskDate
        holder.taskDescriptionTextView.text = currentTask.taskDescription
        holder.deleteTaskButton.setOnClickListener {
            // Accede a la tarea correspondiente y elimínala
            val selectedTask = tasks[position]
            // Usa 'selectedTask' para eliminar la tarea de la lista de tareas
            tasks.remove(selectedTask)
            notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
        }
        holder.deleteTaskButton.setOnClickListener {
            val selectedTask = tasks[position]
            tasks.remove(selectedTask)
            notifyDataSetChanged()

            val dbHelper = miSQLiteHelper(holder.itemView.context)
            dbHelper.deleteTaskByTaskName(selectedTask.taskName)
        }

    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}
