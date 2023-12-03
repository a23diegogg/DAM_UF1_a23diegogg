package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_welcome, container, false)
        val clearDBButton = view.findViewById<Button>(R.id.clearDB)


        val btn_start = view.findViewById<Button>(R.id.btn_start)
        btn_start.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_activitiesFragment)
        }
        clearDBButton.setOnClickListener {
            val dbHelper = miSQLiteHelper(requireContext())
            dbHelper.deleteAllTasks()
        }
        return view
    }

}