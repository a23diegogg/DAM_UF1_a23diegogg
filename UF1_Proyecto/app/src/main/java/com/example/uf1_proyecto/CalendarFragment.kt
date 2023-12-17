package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_calendar, container, false)


        val clearDBButton = view.findViewById<Button>(R.id.clearDB)
        clearDBButton.setOnClickListener {
            val dbHelper = miSQLiteHelper(requireContext())
            dbHelper.deleteAllTasks()
        }

        return view
    }

}