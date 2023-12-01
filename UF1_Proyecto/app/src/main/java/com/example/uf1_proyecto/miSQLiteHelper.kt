package com.example.uf1_proyecto

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf

class miSQLiteHelper(context: Context): SQLiteOpenHelper(
    context, "tasks.db", null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addTask(name: String, date:String, description:String){
        val task = ContentValues()
        task.put("name",name)
        task.put("date",date)
        task.put("description",description)
    }

}