package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class CreationTaskFragment : Fragment() {
    val sharedViewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_creation_task, container, false)

        val btn_addTask = view.findViewById<Button>(R.id.btn_addTask)
        btn_addTask.setOnClickListener {
            findNavController().navigate(R.id.action_creationTaskFragment_to_activitiesFragment)
        }
        btn_addTask.setOnClickListener {
            val taskName = view.findViewById<EditText>(R.id.taskName).text.toString()
            val taskDate = view.findViewById<EditText>(R.id.taskDate).text.toString()
            val taskDescription = view.findViewById<EditText>(R.id.taskDescription).text.toString()

            val newTask = Task(taskName, taskDate, taskDescription)
            sharedViewModel.tasksList.add(newTask)

            val navController = findNavController()
            navController.navigate(R.id.action_creationTaskFragment_to_activitiesFragment)
        }

        return view
    }

}