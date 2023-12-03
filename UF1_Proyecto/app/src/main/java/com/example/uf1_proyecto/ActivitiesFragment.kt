package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class ActivitiesFragment : Fragment() {
    private lateinit var adapter: TasksAdapter
    private val sharedViewModel: TasksViewModel by activityViewModels()
;
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener los datos de la nueva tarea
        val newTaskName = arguments?.getString("taskName")
        val newTaskDate = arguments?.getString("taskDate")
        val newTaskDescription = arguments?.getString("taskDescription")
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val btn_createTask = view.findViewById<Button>(R.id.btn_createTask)



        if (newTaskName != null && newTaskDate != null && newTaskDescription != null) {
            val newTask = Task(newTaskName, newTaskDate, newTaskDescription)
            sharedViewModel.tasksList.add(newTask)
            adapter.notifyDataSetChanged()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_activities, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = TasksAdapter(sharedViewModel.tasksList)
        recyclerView.adapter = adapter

        val btn_createTask = view.findViewById<Button>(R.id.btn_createTask)
        btn_createTask.setOnClickListener {
            findNavController().navigate(R.id.action_activitiesFragment_to_creationTaskFragment)
        }

        return view
    }
}