package com.example.guru_start

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.LineHeightSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import java.io.IOException

class MainScreen : AppCompatActivity() {
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var mypageButton: ImageButton
    lateinit var lectureButton: ImageButton

    var selectedGrade: String = "1학년" // 초기값은 전체로 설정

    // 강의 목록 버튼 레이아웃
    private fun createSubjectButton(lecture: Lecture): Button {
        val button = Button(this)
        val lectureName = lecture.강의명 ?: ""
        val section = " - 분반 0${lecture.분반 ?: ""}"
        val line1 = "$lectureName $section"

        val line2 = "${lecture.교수명 ?: ""} / ${lecture.구분 ?: ""} / ${lecture.학년 ?: ""}" +
                " / ${lecture.강의시간 ?: ""} / ${lecture.장소 ?: ""} / ${lecture.학점 ?: ""}학점"
        val line3 = "총 평점 : ${lecture.별점 ?: ""}"
        val layoutParams = LinearLayout.LayoutParams(1050, 350)

        // 버튼과 버튼 사이 여백 설정
        layoutParams.setMargins(0, 0, 0, 20)
        // 버튼 레이아웃 설정
        button.layoutParams = layoutParams
        button.setPadding(50, 50, 50, 50)
        button.setBackgroundResource(R.drawable.rounded_button)
        // 버튼에 들어갈 줄들
        val text = "$line1\n$line2\n$line3"

        val spannable = SpannableStringBuilder(text)

        // 첫 번째 줄 설정
        spannable.setSpan(
            RelativeSizeSpan(1.4f), // 강의명 텍스트 크기
            0, lectureName.length, // 시작 인덱스와 끝 인덱스 (강의명 부분)
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            RelativeSizeSpan(1.0f), // 분반 텍스트 크기
            lectureName.length, line1.length, // 시작 인덱스와 끝 인덱스 (분반 부분)
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(Color.GRAY), // 텍스트 색상
            lectureName.length, line1.length, // 시작 인덱스와 끝 인덱스 (분반 부분)
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            StyleSpan(Typeface.BOLD), // 강의명에는 볼드체
            0, lectureName.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 두 번째 줄 설정
        spannable.setSpan(
            ForegroundColorSpan(Color.GRAY), // 텍스트 색상
            line1.length + 1, line1.length + line2.length + 1, // 시작 인덱스와 끝 인덱스 (두 번째 줄)
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            RelativeSizeSpan(0.9f), // 텍스트 크기
            line1.length + 1, line1.length + line2.length + 1, // 시작 인덱스와 끝 인덱스 (두 번째 줄)
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 세 번째 줄 설정
        spannable.setSpan(
            LineHeightSpan.Standard(100), // 위쪽 여백 추가
            line1.length + line2.length + 2,
            line1.length + line2.length + line3.length + 2, // 시작 인덱스와 끝 인덱스 (세 번째 줄)
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            RelativeSizeSpan(1.5f), // 텍스트 크기
            line1.length + line2.length + 2,
            line1.length + line2.length + line3.length + 2, // 시작 인덱스와 끝 인덱스 (세 번째 줄)
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            StyleSpan(Typeface.BOLD), // 볼드체
            0, line1.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        button.text = spannable
        button.gravity = Gravity.START

        return button
    }

    // 강의 정보 페이지로 이동
    private fun clickClassInfoActivity(lecture: Lecture) {
        val intent = Intent(this, class_info::class.java)

        // 강의 정보를 Intent에 넣기
        intent.putExtra("LECTURE", lecture)

        startActivity(intent)
    }

    private lateinit var dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.ass_info_btn_main)
        mypageButton = findViewById(R.id.mypageButton)
        lectureButton = findViewById(R.id.lectureButton)

        button1.setOnClickListener {
            val intent1 = Intent(this, WebViewActivity::class.java)
            intent1.putExtra("url", "https://www.swu.ac.kr/index.do")
            startActivity(intent1)
        }

        button2.setOnClickListener {
            val intent2 = Intent(this, WebViewActivity::class.java)
            intent2.putExtra("url", "https://swis.swu.ac.kr/")
            startActivity(intent2)
        }

        button3.setOnClickListener {
            val intent3 = Intent(this, WebViewActivity::class.java)
            intent3.putExtra("url", "https://cyber.swu.ac.kr/ilos/main/main_form.acl")
            startActivity(intent3)
        }

        button4.setOnClickListener {
            val intent4 = Intent(this, WebViewActivity::class.java)
            intent4.putExtra("url", "https://www.swu.ac.kr/www/futurei_1.html")
            startActivity(intent4)
        }

        mypageButton.setOnClickListener {
            val intent5 = Intent(this, MyPageActivity::class.java)
            startActivity(intent5)
        }

        lectureButton.setOnClickListener {
            // LectureMain 액티비티로 이동
            val intent = Intent(this, LectureMain::class.java)
            startActivity(intent)
        }

        dbHelper = DataBaseHelper(this)
        try {
            dbHelper.createDataBase()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        dbHelper.openDataBase()

        initLoadDB()
        addSubjectButtons(selectedGrade)


        val spinner: Spinner = findViewById(R.id.spinner)
        val items = listOf("1학년", "2학년", "3학년", "4학년")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        val textView1: TextView = findViewById(R.id.textView1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // 선택된 아이템에 대한 처리
                textView1.text = selectedItem + " 커리큘럼 과목"

                // 선택된 학년에 따라 버튼 갱신
                addSubjectButtons(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    var lectureList: List<Lecture>? = null

    private fun initLoadDB() {
        val mDbHelper = DataAdapter(applicationContext)
        mDbHelper.createDatabase()
        mDbHelper.open()

        lectureList = mDbHelper.tableData

        // 데이터베이스 사용이 끝난 후에는 닫기
        mDbHelper.close()

//        // TextView에 출력
//        val textView = findViewById<TextView>(R.id.textView3)
//        for (lecture in lectureList.orEmpty()) {
//            textView.append(lecture.toString() + "\n")
        // }
    }


    // 자동 강의 목록 버튼 생성
    private fun addSubjectButtons(selectedGrade: String) {
        // lectureList가 null이 아닌 경우에만 버튼을 추가합니다.
        lectureList?.let {
            val linearLayout = findViewById<LinearLayout>(R.id.linearLayoutSubjectsMain)

            // 기존의 버튼들을 모두 제거
            linearLayout.removeAllViews()

            for (lecture in it) {
                // 해당 학기에 개설되는 강의만을 필터링하고 선택한 학년이 있다면 해당 학년 강의만 추가
                if (lecture.학년 == selectedGrade || selectedGrade == "전체") {
                    val button = createSubjectButton(lecture)
                    button.setOnClickListener {
                        clickClassInfoActivity(lecture)
                    }
                    linearLayout.addView(button)
                }
            }
        }

    }
}

