package com.example.uf1_proyecto

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf

class miSQLiteHelper(context: Context): SQLiteOpenHelper(
    context, "tasks.db", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "taskName TEXT," +
                    "taskDate TEXT," +
                    "taskDescription TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL("ALTER TABLE tasks ADD COLUMN priority INTEGER DEFAULT 0")
        }
    }
    fun addTask(name: String, date: String, description: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("taskName", name)
            put("taskDate", date)
            put("taskDescription", description)
        }
        db.insert("tasks", null, values)
        db.close()
    }
    fun getAllTasks(): List<Task> {
        val tasksList = mutableListOf<Task>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tasks", null)

        if (cursor.moveToFirst()) {
            val idColumnIndex = cursor.getColumnIndex("id")
            val taskNameColumnIndex = cursor.getColumnIndex("taskName")
            val taskDateColumnIndex = cursor.getColumnIndex("taskDate")
            val taskDescriptionColumnIndex = cursor.getColumnIndex("taskDescription")

            do {
                val taskId = cursor.getInt(idColumnIndex)
                val taskName = cursor.getString(taskNameColumnIndex)
                val taskDate = cursor.getString(taskDateColumnIndex)
                val taskDescription = cursor.getString(taskDescriptionColumnIndex)

                val task = Task(taskName, taskDate, taskDescription)
                tasksList.add(task)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return tasksList
    }
    fun updateTask(taskId: Int, updatedTask: Task) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("taskName", updatedTask.taskName)
            put("taskDate", updatedTask.taskDate)
            put("taskDescription", updatedTask.taskDescription)
        }
        db.update("tasks", values, "id = ?", arrayOf(taskId.toString()))
        db.close()
    }
    fun deleteTaskByColumnValue(columnName: String, columnValue: String) {
        val db = writableDatabase
        db.execSQL("DELETE FROM tasks WHERE $columnName = '$columnValue'")
        db.close()
    }

    fun deleteTaskByTaskName(taskName: String) {
        val db = writableDatabase
        db.delete("tasks", "taskName = ?", arrayOf(taskName))
        db.close()
    }
    fun deleteAllTasks() {
        val db = writableDatabase
        db.execSQL("DELETE FROM tasks")
        db.close()
    }


}

