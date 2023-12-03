package com.example.uf1_proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_1) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.welcomeFragment)
                    true
                }
                R.id.calendar -> {
                    navController.navigate(R.id.calendarFragment)
                    true
                }
                R.id.taskIcon -> {
                    navController.navigate(R.id.activitiesFragment)
                    true
                }
                R.id.addTaskIcon -> {
                    navController.navigate(R.id.creationTaskFragment)
                    true
                }
                else -> false
            }
        }



    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcvmenu,fragment)
            commit()
        }
    }
}


