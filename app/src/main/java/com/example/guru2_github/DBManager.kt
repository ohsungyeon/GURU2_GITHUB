package com.example.questionpage2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
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
        const val DATABASE_NAME = "강의평가3.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "강의평가"
        const val COLUMN_STAR_RATING = "별점"
        const val COLUMN_CONTENT = "내용"
        const val COLUMN_SEMESTER = "수강시기"
        const val COLUMN_RECOMMEND_TARGET = "추천대상"
        const val COLUMN_ASSIGNMENTS = "과제량"
        const val COLUMN_STUDY_GROUP = "조모임"
        const val COLUMN_GRADE = "성적"
        const val COLUMN_ATTENDANCE = "출석"
        const val COLUMN_IMAGE = "이미지"
        const val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_STAR_RATING REAL, "
                + "$COLUMN_CONTENT TEXT, "
                + "$COLUMN_SEMESTER TEXT, "
                + "$COLUMN_RECOMMEND_TARGET TEXT, "
                + "$COLUMN_ASSIGNMENTS TEXT, "
                + "$COLUMN_STUDY_GROUP TEXT, "
                + "$COLUMN_GRADE TEXT, "
                + "$COLUMN_ATTENDANCE TEXT); ")
//                + "$COLUMN_IMAGE BLOB);")
    }
}