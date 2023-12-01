package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ActivitiesFragment : Fragment() {

    private lateinit var myAdapter: MyAdapter
    private val taskList = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicializa el adaptador con la lista vacía
        myAdapter = MyAdapter(taskList)
        recyclerView.adapter = myAdapter

        fun addTaskToList(task: String) {
            taskList.add(task)
            myAdapter.notifyItemInserted(taskList.size - 1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_activities, container, false)

        val btn_createTask =view.findViewById<Button>(R.id.btn_createTask)
        btn_createTask.setOnClickListener {
            findNavController().navigate(R.id.action_activitiesFragment_to_creationTaskFragment)
        }
        return view
    }


}