package com.example.uf1_proyecto

import android.app.DatePickerDialog
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
import java.util.Calendar

class CreationTaskFragment : Fragment() {
    val sharedViewModel: TasksViewModel by activityViewModels()
    private lateinit var dbHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = miSQLiteHelper(requireContext()) // Inicialización de dbHelper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_creation_task, container, false)

        dbHelper = miSQLiteHelper(requireContext()) // Pasar el contexto aquí

        val btn_addTask = view.findViewById<Button>(R.id.btn_addTask)
        btn_addTask.setOnClickListener {
            findNavController().navigate(R.id.action_creationTaskFragment_to_activitiesFragment)
        }
        val taskDateButton = view.findViewById<Button>(R.id.taskDateButton)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        taskDateButton.setOnClickListener {
            val datePicker = DatePickerDialog(
                requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    val taskDateEditText = view.findViewById<EditText>(R.id.taskDate)
                    taskDateEditText.setText(selectedDate)
                }, year, month, day
            )

            datePicker.show()
        }

        btn_addTask.setOnClickListener {
            val taskName = view.findViewById<EditText>(R.id.taskName).text.toString()
            val taskDate = view.findViewById<EditText>(R.id.taskDate).text.toString()
            val taskDescription = view.findViewById<EditText>(R.id.taskDescription).text.toString()

            val newTask = Task(taskName, taskDate, taskDescription)
            sharedViewModel.tasksList.add(newTask)
            dbHelper.addTask(taskName, taskDate, taskDescription)

            val navController = findNavController()
            navController.navigate(R.id.action_creationTaskFragment_to_activitiesFragment)
        }


        return view
    }

}