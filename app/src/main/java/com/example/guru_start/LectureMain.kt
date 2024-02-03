package com.example.guru_start

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.LineHeightSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


class LectureMain : AppCompatActivity() {

    private lateinit var dbHelper: DataBaseHelper
    private lateinit var db: SQLiteDatabase
    var selectedSemester: String = "2024-1" // 기본 학기 설정
    var selectedGrade: String? = null // 선택한 학년을 저장
    var selectedType: String? = null // 선택한 전공 필수/선택 타입을 저장


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture_main)

        dbHelper = DataBaseHelper(this)
        try {
            dbHelper.createDataBase()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        dbHelper.openDataBase()

        initLoadDB()

        // 학기 선택 버튼
        val semesterButton = findViewById<ImageButton>(R.id.imageButton2)
        semesterButton.setOnClickListener {
            showSemesterDialog()
        }

        // 학년 선택 버튼
        val gradeButton = findViewById<Button>(R.id.button7)
        gradeButton.setOnClickListener {
            showGradeDialog()
        }

        // 전필/전선 선택 버튼
        val typeButton = findViewById<Button>(R.id.button6)
        typeButton.setOnClickListener {
            showTypeDialog()
        }
        Log.d("aaa", selectedSemester)
        Log.d("aaa", selectedGrade.toString())
        Log.d("aaa", selectedType.toString())

        // 강의 목록 버튼 생성
        addSubjectButtons(selectedSemester, selectedGrade, selectedType)

        // 이전 화면으로 돌아가기
        val backButton = findViewById<ImageButton>(R.id.backButton0)
        backButton.setOnClickListener {
            finish()
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
    private fun addSubjectButtons(
        selectedSemester: String,
        selectedGrade: String?,
        selectedType: String?
    ) {
        // lectureList가 null이 아닌 경우에만 버튼을 추가합니다.
        lectureList?.let {
            val linearLayout = findViewById<LinearLayout>(R.id.linearLayoutSubjects)

            // 기존의 버튼들을 모두 제거
            linearLayout.removeAllViews()

            for (lecture in it) {
                // 해당 학기에 개설되는 강의만을 필터링하고 선택한 학년이 있다면 해당 학년 강의만 추가
                if (lecture.학기 == selectedSemester &&
                    (selectedGrade.isNullOrEmpty() || lecture.학년 == selectedGrade) &&
                    (selectedType.isNullOrBlank() || lecture.구분 == selectedType)
                ) {
                    val button = createSubjectButton(lecture)
                    button.setOnClickListener {
                        clickClassInfoActivity(lecture)
                    }
                    linearLayout.addView(button)
                }
            }
        }
    }


    // 강의 목록 버튼 레이아웃
    private fun createSubjectButton(lecture: Lecture): Button {
        val button = Button(this)
        val lectureName = lecture.강의명 ?: ""
        val section = " - 분반 0${lecture.분반 ?: ""}"
        val line1 = "$lectureName $section"

        val line2 = "${lecture.교수명 ?: ""} / ${lecture.구분 ?: ""} / ${lecture.학년 ?: ""}" +
                " / ${lecture.강의시간 ?: ""} / ${lecture.장소 ?: ""} / ${lecture.학점 ?: ""}학점"
        val line3 = "총 평점 : ${lecture.별점 ?:""}"
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

    // 학기 선택 버튼 클릭 이벤트에서 해당 학기에 개설된 강의만 버튼을 생성
    private fun showSemesterDialog() {
        val semesterList = arrayOf("2023-2", "2024-1", "2024-2") // 학기 목록
        val builder = AlertDialog.Builder(this)

        // 다이얼로그 표시
        builder.setTitle("학기를 선택하세요")
            .setItems(semesterList) { _, which ->
                // 사용자가 선택한 학기에 대한 동작 수행
                selectedSemester = semesterList[which]
                Toast.makeText(this, "선택한 학기: $selectedSemester", Toast.LENGTH_SHORT).show()

                // 선택한 학기에 따라 텍스트 변경
                val semesterTextView = findViewById<TextView>(R.id.semesterTextView)
                semesterTextView.text = selectedSemester

                // 해당 학기에 개설된 강의 버튼 생성 전에 이전에 추가된 버튼들을 제거
                val linearLayout = findViewById<LinearLayout>(R.id.linearLayoutSubjects)
                linearLayout.removeAllViews()

                // 해당 학기, 학년, 구분에 개설된 강의 버튼 생성
                addSubjectButtons(selectedSemester, selectedGrade, selectedType)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }

        builder.create().show()
    }

    // 학년 선택 다이얼로그 표시
    private fun showGradeDialog() {
        val gradeList = arrayOf("1학년", "2학년", "3학년", "4학년", "전체 보기") // 학년 목록 배열
        val builder = AlertDialog.Builder(this)

        // 다이얼로그 표시
        builder.setTitle("학년을 선택하세요")
            .setItems(gradeList) { _, which ->
                // 사용자가 선택한 학년에 대한 동작 수행
                if (which == gradeList.size - 1) {
                    // "기타"를 선택한 경우
                    selectedGrade = null
                } else {
                    // 다른 학년을 선택한 경우
                    selectedGrade = gradeList[which]
                }
                Toast.makeText(this, "선택한 학년: ${selectedGrade ?: "전체"}", Toast.LENGTH_SHORT).show()

                // 선택한 학기와 학년에 따라 텍스트 변경
                val gradeButton = findViewById<Button>(R.id.button7)
                gradeButton.text = "학년: ${selectedGrade ?: "전체"}"

                // 해당 학기, 학년, 구분에 개설된 강의 버튼 생성
                addSubjectButtons(selectedSemester, selectedGrade, selectedType)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }

        builder.create().show()
    }


    // 전필/전선 선택 다이얼로그 표시
    private fun showTypeDialog() {
        val typeList = arrayOf("전공필수", "전공선택", "전체 보기") // 전공 필수/선택 목록 배열
        val builder = AlertDialog.Builder(this)

        // 다이얼로그 표시
        builder.setTitle("구분을 선택하세요")
            .setItems(typeList) { _, which ->
                // 사용자가 선택한 구분에 대한 동작 수행
                if (which == typeList.size - 1) {
                    // "기타"를 선택한 경우
                    selectedType = null
                } else {
                    // 다른 구분을 선택한 경우
                    selectedType = typeList[which]
                }
                Toast.makeText(this, "선택한 구분: ${selectedType ?: "전체"}", Toast.LENGTH_SHORT).show()

                // 선택한 학기, 학년, 구분에 따라 텍스트 변경
                val typeButton = findViewById<Button>(R.id.button6)
                typeButton.text = "구분: ${selectedType ?: "전체"}"

                // 해당 학기, 학년, 구분에 개설된 강의 버튼 생성
                addSubjectButtons(selectedSemester, selectedGrade, selectedType)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }

        builder.create().show()
    }


}

