package com.example.signupandsignin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,"user.db",null,1) {

    private val sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table Users(Name Text, Email Text, Password Text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    fun signIN(name: String, email: String, password: String) {
        val contentValues = ContentValues()
        contentValues.put("Name", name)
        contentValues.put("Email", email)
        contentValues.put("Password", password)

        sqLiteDatabase.insert("Users", null, contentValues)
    }


    fun logIn(email: String, password: String) {
        val email = sqLiteDatabase.rawQuery("SELECT * FROM Users WERE Email = $email ", null)

        val res = sqLiteDatabase.rawQuery(
            "SELECT * FROM Users WHERE Email = '$email' AND Password = '$password'",
            null
        )
        if (res.count < 1) {
            println("The Password Enter Wrong , Sorry!")
        } else {
            println("Something Wrong")
        }


    }

    fun getDetails(email: String): List<String> {
        val c = sqLiteDatabase.rawQuery("SELECT * FROM Users WHERE Email = '$email'", null)
        if (c.moveToFirst())
            return listOf(c.getString(0).toString(), c.getString(1).toString())
        return listOf(":(", ":(")
    }


}