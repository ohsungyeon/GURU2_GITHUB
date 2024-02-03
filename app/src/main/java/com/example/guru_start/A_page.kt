package com.example.guru_start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class A_page : AppCompatActivity() {
    private var semester: String? = null
    private var question: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_page)

        //변수 선언, 연결
        val backImageButton : ImageButton = findViewById(R.id.backImageButton)
        val AnswerButton7 : Button = findViewById(R.id.AnswerButton7)
        val SemesterTextView : TextView = findViewById(R.id.SemesterTextView)
        val QuestionTextView : TextView = findViewById(R.id.QuestionTextView)

        // 이전 화면에서 전달한 데이터 받기
        val Semester = intent.getStringExtra("period")
        val Question = intent.getStringExtra("content")

        // 받은 데이터를 사용하여 필요한 작업 수행
        SemesterTextView.text = "수강학기: $Semester"
        QuestionTextView.text = "$Question"

        if (savedInstanceState != null) {
            // 저장된 데이터를 사용하여 복원
            semester = savedInstanceState.getString("semester")
            question = savedInstanceState.getString("question")

            // TextView 업데이트
            SemesterTextView.text = "수강학기: $semester"
            QuestionTextView.text = "$question"
        } else {
            // 이전 화면에서 전달한 데이터 받기
            semester = intent.getStringExtra("period")
            question = intent.getStringExtra("content")

            // 받은 데이터를 사용하여 필요한 작업 수행
            SemesterTextView.text = "수강학기: $semester"
            QuestionTextView.text = "$question"
        }

        //뒤로가기 버튼 누르면 화면 전환 -> QnA화면으로
        //QnA화면으로 돌아갈 intent생성
        val intent = Intent(this, QnA_page::class.java)
        val databasePath = intent.getStringExtra("databasePath")
        backImageButton.setOnClickListener {
//            intent.putExtra("databasePath", path)
            startActivity(intent)
        }

        // 답변하기 버튼 누르면 화면 전환-> 답변 등록 화면으로
        AnswerButton7.setOnClickListener {
            val intent2 = Intent(this, A_regist::class.java)
            startActivity(intent2)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        // 액티비티가 파괴되기 전에 데이터 저장
        outState.putString("semester", semester)
        outState.putString("question", question)

        super.onSaveInstanceState(outState)
    }
}