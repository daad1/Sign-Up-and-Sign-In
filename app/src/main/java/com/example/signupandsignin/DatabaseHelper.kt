package com.example.signupandsignin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,"user.db",null,2) {
    private val sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null){
            db?.execSQL("create table users( Email STRING PRIMARY KEY ,Name text,Mobile text,Password text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun saveData(user: Users) {
        val contentValues = ContentValues()
        contentValues.put("Email",user.email)
        contentValues.put("Name",user.name)
        contentValues.put("Mobile",user.mobile)
        contentValues.put("Password",user.password)
        sqLiteDatabase.insert("users",null,contentValues)
    }


    fun readData(email: String): Users? {
        var user: Users? = null
        val cursor: Cursor =
            sqLiteDatabase.rawQuery("SELECT * FROM users WHERE Email LIKE '$email' ", null)
        if (cursor.count < 1) {
            println("No Data Found")
        } else {
            cursor.moveToNext()
            user = Users(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                (cursor.getString(3))
            )
        }
        return user
    }
}