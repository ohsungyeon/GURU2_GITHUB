package com.example.guru_start

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class QnA_page : AppCompatActivity() {
    lateinit var dbManager: QuestionDBManager
    lateinit var sqlitedb: SQLiteDatabase
//     Q_regist화면 식별 코드
    companion object {
        const val MY_REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qna_page)

        // 툴바 타이틀 숨기기
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // DB받아오기
        val databasePath = intent.getStringExtra("databasePath")

        // 리사이클러뷰 구현
        val rv_board = findViewById<RecyclerView>(R.id.rv_board)


        val itemList = ArrayList<BoardItem>()

        if (databasePath != null) {
            val database = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE)
            // 데이터베이스에서 데이터 읽기
            val cursor = database.rawQuery("SELECT * FROM QandAorStudySharing", null)
            // 결과 처리
            while (cursor.moveToNext()) {
                // "내용" 열의 데이터를 리스트에 추가
                val content = cursor.getString(cursor.getColumnIndex("내용"))

                // "수강시기" 열의 데이터를 리스트에 추가
                val period = cursor.getString(cursor.getColumnIndex("수강시기"))

                // BoardItem 객체 생성 및 itemList에 추가
                val boardItem = BoardItem(period, content)
                itemList.add(boardItem)
            }

            // Cursor 닫기
            cursor.close()

            val boardAdapter = BoardAdapter(itemList)

            // 어댑터에 변경 사항 알림
            boardAdapter.notifyDataSetChanged()

            rv_board.adapter = boardAdapter
            rv_board.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        } else {
            dbManager = QuestionDBManager(this, "등록", null, 1)
            val database = dbManager.readableDatabase
            val cursor = database.rawQuery("SELECT * FROM QandAorStudySharing", null)

            while (cursor.moveToNext()) {
                val content = cursor.getString(cursor.getColumnIndex("내용"))
                val period = cursor.getString(cursor.getColumnIndex("수강시기"))

                val boardItem = BoardItem(period, content)
                itemList.add(boardItem)
            }
            // Cursor 닫기
            cursor.close()

            val boardAdapter = BoardAdapter(itemList)

            // 어댑터에 변경 사항 알림
            boardAdapter.notifyDataSetChanged()

            rv_board.adapter = boardAdapter
            rv_board.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        // 뒤로가기 버튼 누르면 화면 전환(강의정보로)
        val BackImageButton1 = findViewById<ImageButton>(R.id.BackImageButton1)
        BackImageButton1.setOnClickListener {
            finish()
        }

        // 강의정보 버튼 누르면 화면 전환
        val LecInfoButton = findViewById<Button>(R.id.LecInfoButton)
        val intent_lec_info = Intent(this, class_info::class.java)
        LecInfoButton.setOnClickListener{
            startActivity(intent_lec_info)
        }


        // 질문하기 버튼 누르면 화면 전환
        val QuestionButton = findViewById<Button>(R.id.QuestionButton)
        QuestionButton.setOnClickListener {
            val intent_Q = Intent(this, Q_regist::class.java)
            startActivity(intent_Q)
        }

        //강의평가페이지로 넘어가기
        val lec_info_btn_qna = findViewById<Button>(R.id.lec_info_btn_qna)
        lec_info_btn_qna.setOnClickListener{
            val intent = Intent(this, lecture_eval::class.java)
            startActivity(intent)
        }

//        //과제정보페이지로 넘어가기
        val ass_info_btn_qna = findViewById<Button>(R.id.ass_info_btn_qna)
        ass_info_btn_qna.setOnClickListener{
            val intent = Intent(this, ass_info::class.java)
            startActivity(intent)
        }
//
        //시험정보페이지로 넘어가기
        val test_info_btn_qna = findViewById<Button>(R.id.test_info_btn_qna)
        test_info_btn_qna.setOnClickListener{
            val intent = Intent(this, test_info::class.java)
            startActivity(intent)
        }

        //공부정보페이지로 넘어가기
        val study_info_btn_qna = findViewById<Button>(R.id.study_info_btn_qna)
        study_info_btn_qna.setOnClickListener{
            val intent = Intent(this, study_info::class.java)
            startActivity(intent)
        }

    }
}