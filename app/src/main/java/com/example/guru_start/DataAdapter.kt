package com.example.guru_start

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.IOException
import java.sql.SQLException


class DataAdapter(private val mContext: Context) {
    private var mDb: SQLiteDatabase? = null
    private val mDbHelper: DataBaseHelper

    init {
        mDbHelper = DataBaseHelper(mContext)
    }

    @Throws(SQLException::class)
    fun createDatabase(): DataAdapter {
        try {
            mDbHelper.createDataBase()
        } catch (mIOException: IOException) {
            Log.e(TAG, "$mIOException  UnableToCreateDatabase")
            throw Error("UnableToCreateDatabase")
        }
        return this
    }

    @Throws(SQLException::class)
    fun open(): DataAdapter {
        mDb = try {
            mDbHelper.openDataBase()
            mDbHelper.close()
            mDbHelper.readableDatabase
        } catch (mSQLException: SQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString())
            throw mSQLException
        }
        return this
    }

    fun close() {
        mDbHelper.close()
    }

    val tableData: List<Lecture>
        get() = try {
            val sql = "SELECT * FROM $TABLE_NAME"
            val lectureList = mutableListOf<Lecture>()
            var lecture: Lecture? = null
            val mCur = mDb!!.rawQuery(sql, null)
            if (mCur != null) {
                while (mCur.moveToNext()) {
                    lecture = Lecture(
                        학기 = mCur.getString(0),
                        교과목번호 = mCur.getString(1),
                        강의명= mCur.getString(2),
                        분반= mCur.getString(3),
                        교수명= mCur.getString(4),
                        구분= mCur.getString(5),
                        학년= mCur.getString(6),
                        강의시간= mCur.getString(7),
                        장소= mCur.getString(8),
                        학점= mCur.getString(9),
                        수강대상= mCur.getString(10),
                        교재= mCur.getString(11),
                        사용언어= mCur.getString(12),
                        별점= mCur.getString(13)
                    )
                    lectureList.add(lecture)
                }
            }
            lectureList
        } catch (mSQLException: SQLException) {
            Log.e(TAG, "tableData >> ${mSQLException.toString()}")
            throw mSQLException
        }


    companion object {
        protected const val TAG = "DataAdapter"

        // TODO : TABLE 이름을 명시해야함
        protected const val TABLE_NAME = "GURU2_lecture"
    }
}