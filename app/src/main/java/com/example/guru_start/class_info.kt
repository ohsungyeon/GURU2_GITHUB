package com.example.guru_start

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class class_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_info)

        // 가로스크롤바에 있는 질의응답 버튼에 대한 클릭 리스너 설정
        //val qnaButton = findViewById<Button>(R.id.qnaButton)
        //qnaButton.setOnClickListener {
        //    navigateToQnAPage()
        //}

        // 이전 화면으로 돌아가기
        val backButton = findViewById<ImageButton>(R.id.backButton1)
        backButton.setOnClickListener {
            finish()
        }
        // 강의 정보를 가져와서 TextView에 설정
        displayLectureInfo()

        //QnA페이지로 넘어가기
        val qnaButton = findViewById<Button>(R.id.qnaButton)
        qnaButton.setOnClickListener{
            navigateToQnAPage()
        }

    }

//     qna 페이지로 넘어가기
    private fun navigateToQnAPage() {
        val intent = Intent(this, QnA_page::class.java)
        startActivity(intent)
    }


    // 강의 정보를 표시하는 함수
    private fun displayLectureInfo() {
        // Intent에서 데이터 추출
        val lecture: Lecture? = intent.getParcelableExtra("LECTURE")

        // 강의 정보를 텍스트뷰에 설정
        val lectureNameTextView = findViewById<TextView>(R.id.lecture_name_input)
        val professorTextView = findViewById<TextView>(R.id.lecture_professor_input)
        val lectureMajorTextView = findViewById<TextView>(R.id.lecture_major_input)
        val lectureGradeTextView = findViewById<TextView>(R.id.lecture_grade_input)
        val lectureTimeTextView = findViewById<TextView>(R.id.lecture_time_input)
        val lecturePlaceTextView = findViewById<TextView>(R.id.lecture_place_input)
        val lectureScoreTextView = findViewById<TextView>(R.id.lecture_score_input)
        val lectureSubjectTextView = findViewById<TextView>(R.id.lecture_subject_input)
        val lectureBookTextView = findViewById<TextView>(R.id.lecture_book_input)
        val lectureLanguageTextView = findViewById<TextView>(R.id.lecture_language_input)
        // 추가적인 정보들도 필요한 만큼 TextView 추가

        lecture?.let {
            lectureNameTextView.text = "${it.강의명}"
            professorTextView.text = "${it.교수명}"
            lectureMajorTextView.text = "${it.구분}"
            lectureGradeTextView.text = "${it.학년}"
            lectureTimeTextView.text = "${it.강의시간}"
            lecturePlaceTextView.text = "${it.장소}"
            lectureScoreTextView.text = "${it.학점}학점"
            lectureSubjectTextView.text = "${it.수강대상}"
            lectureBookTextView.text = "${it.교재}"
            lectureLanguageTextView.text = "${it.사용언어}"
            // 추가적인 정보들도 설정
        }
    }
}