package com.example.guru_start

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class QuestionDBManager(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        // 테이블 생성 SQL 문 실행
        db?.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        const val DATABASE_NAME = "등록.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "QandAorStudySharing"
        const val COLUMN_TYPE = "등록유형"
        const val COLUMN_CONTENT = "내용"
        const val COLUMN_SEMESTER = "수강시기"
//        const val COLUMN_IMAGE = "이미지"
        const val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_TYPE TEXT, "
                + "$COLUMN_CONTENT TEXT, "
                + "$COLUMN_SEMESTER TEXT);")
//                + "$COLUMN_IMAGE BLOB);")
    }
}