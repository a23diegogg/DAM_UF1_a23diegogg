package com.example.uf1_proyecto

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf

class miSQLiteHelper(context: Context): SQLiteOpenHelper(
    context, "tasks.db", null,1) {
    private var database: SQLiteDatabase? = null

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
    private fun openDatabaseWritable() {
        database = writableDatabase
    }

    private fun openDatabaseReadable() {
        database = readableDatabase
    }

    private fun closeDatabase() {
        database?.close()
    }

    fun addTask(name: String, date: String, description: String) {
        openDatabaseWritable()
        database?.apply {

            val values = ContentValues().apply {
                put("taskName", name)
                put("taskDate", date)
                put("taskDescription", description)
            }
            insert("tasks", null, values)
        }
    }
    fun getAllTasks(): List<Task> {
        val tasksList = mutableListOf<Task>()
        openDatabaseReadable()
        database?.apply {
            val cursor = rawQuery("SELECT * FROM tasks", null)

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
        }
        return tasksList
    }
    fun updateTask(taskId: Int, updatedTask: Task) {
        openDatabaseWritable()
        database?.apply {
            val values = ContentValues().apply {
                put("taskName", updatedTask.taskName)
                put("taskDate", updatedTask.taskDate)
                put("taskDescription", updatedTask.taskDescription)
            }
            update("tasks", values, "id = ?", arrayOf(taskId.toString()))
        }
    }
    fun deleteTaskByColumnValue(columnName: String, columnValue: String) {
        openDatabaseWritable()
        database?.apply {
            execSQL("DELETE FROM tasks WHERE $columnName = '$columnValue'")
        }
    }

    fun deleteTaskByTaskName(taskName: String) {
        openDatabaseWritable()
        database?.apply {
            delete("tasks", "taskName = ?", arrayOf(taskName))
        }
    }

    fun deleteAllTasks() {
        openDatabaseWritable()
        database?.apply {
            execSQL("DELETE FROM tasks")
        }
    }


}

