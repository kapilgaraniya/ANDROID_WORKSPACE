package com.example.calculatorapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "CalculatorDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE History (id INTEGER PRIMARY KEY AUTOINCREMENT, calculation TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS History")
        onCreate(db)
    }

    fun insertHistory(calculation: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("calculation", calculation)
        db.insert("History", null, values)
        db.close()
    }

    fun getHistory(): ArrayList<String> {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM History", null)
        val historyList = ArrayList<String>()

        if (cursor.moveToFirst()) {
            do {
                val calculation = cursor.getString(1)
                historyList.add(calculation)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return historyList
    }

    fun deleteHistory() {
        val db = writableDatabase
        db.execSQL("DELETE FROM History")
        db.close()
    }

}
