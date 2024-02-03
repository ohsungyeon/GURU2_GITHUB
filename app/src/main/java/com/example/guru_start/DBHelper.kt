package com.example.guru_start

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "usersDB"
        private const val TABLE_USERS = "usersTBL"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_NICKNAME = "nickname"
        private const val KEY_EMAIL = "email"
        private const val KEY_STUDENTNUM = "studentnum"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db!!.execSQL("CREATE TABLE $TABLE_USERS ($KEY_STUDENTNUM INTEGER PRIMARY KEY, $KEY_NAME TEXT, $KEY_NICKNAME TEXT, $KEY_EMAIL TEXT, $KEY_ID TEXT, $KEY_PASSWORD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }
    fun addUser(name: String, nickname: String, email: String, studentnum: String, id: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(KEY_NAME, name)
            put(KEY_NICKNAME, nickname)
            put(KEY_EMAIL, email)
            put(KEY_STUDENTNUM, studentnum)
            put(KEY_ID, id)
            put(KEY_PASSWORD, password)
        }
        db.insert(TABLE_USERS, null, values)
        db.close()
    }
    fun checkUser(studentNum: String, userId: String, password: String): Boolean {
        val db = this.readableDatabase
        val selection = "$KEY_STUDENTNUM = ? AND $KEY_ID = ? AND $KEY_PASSWORD = ?"
        val selectionArgs = arrayOf(studentNum, userId, password)
        val cursor = db.query(TABLE_USERS, arrayOf(KEY_STUDENTNUM), selection, selectionArgs, null, null, null)

        val userExists = cursor.moveToFirst()
        cursor.close()
        db.close()

        return userExists
    }
}

