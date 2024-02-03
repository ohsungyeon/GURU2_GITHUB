package com.example.lecture_ldg

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.Toast
import com.example.lecture_ldg.DBManager.Companion.TABLE_NAME
import com.example.lecture_ldg.databinding.MainActivity2Binding

class MainActivity2 : AppCompatActivity() {

    //객체 선언
//  val scoreText: TextView = findViewById(R.id.score_text)
//  val ratingBar: RatingBar = findViewById(R.id.ratingBar)
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var binding : MainActivity2
    //    var storeButton: Button = binding.storeButton
    var currentRating: Float = 0.0f
    var EvaluateEditTextString : String = ""
    var selectedItem1: String = ""
    var selectedItem2: String = ""
    var selectedItem3: String = ""
    var selectedItem4: String = ""
    var selectedItem5: String = ""
    var selectedItem6: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // 툴바 타이틀 숨기기
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // 바인딩 설정
        binding = MainActivity2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //별점 이벤트(별점이 변경되면 실행됨)
        binding.ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener{
                    ratingBar, rating, fromUser ->
                binding.scoreText.text = "$rating 점"
                currentRating = rating
            }

        // spinner를 사용하여 드롭다운 설정
        // 드롭다운 리스트 객체 선언
        val itemList1 = listOf("선택", "2021-1", "2021-2", "2022-1", "2022-2", "2023-1", "2023-2")
        val itemList2 = listOf("선택", "1학년", "2학년", "3학년", "4학년")
        val itemList3 = listOf("선택","많음", "보통", "적음", "없음")
        val itemList4 = listOf("선택","많음", "보통", "적음", "없음")
        val itemList5 = listOf("선택","너그러움", "보통", "깐깐")
        val itemList6 = listOf("선택","직접호명", "지정좌석", "전자출결", "복합적")

        // 스피너에 연결할 어댑터 선언
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList1)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList2)
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList3)
        val adapter4 = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList4)
        val adapter5 = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList5)
        val adapter6 = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList6)


        // 스피너의 어뎁터 연결
        binding.spinner1.adapter = adapter1
        binding.spinner2.adapter = adapter2
        binding.spinner3.adapter = adapter3
        binding.spinner4.adapter = adapter4
        binding.spinner5.adapter = adapter5
        binding.spinner6.adapter = adapter6

        // 스피너 값 선택하면 문자열로 변한하고 변수에 저장
        binding.spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem1 = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@MainActivity2, itemList1[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem2 = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@MainActivity2, itemList2[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinner3.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem3 = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@MainActivity2, itemList3[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinner4.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem4 = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@MainActivity2, itemList4[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinner5.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem5 = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@MainActivity2, itemList5[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinner6.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem6 = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@MainActivity2, itemList6[position], Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

//        EvaluateEditTextString = findViewById<EditText>(R.id.EvaluateEditText)  // ReviewEditText연결
//        EvaluateEditTextString = binding.EvaluateEditText.text.toString()// binding으로 EvaluateEditText 텍스트 받아오기

        //DBmanager객체 가져오기
        dbManager = DBManager(this, "강의평가3", null, 2)

        binding.storeButton.setOnClickListener {
            EvaluateEditTextString = binding.EvaluateEditText.text.toString()
            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO $TABLE_NAME VALUES ($currentRating, '$EvaluateEditTextString', '$selectedItem1', '$selectedItem2', '$selectedItem3', '$selectedItem4', '$selectedItem5', '$selectedItem6')")
            sqlitedb.close()


            val intent = Intent(this, "MainActivity"::class.java)
            intent.putExtra("intent_currentRating", currentRating)
            intent.putExtra("intent_selectedItem6", selectedItem6)
            startActivity(intent)
        }
    }
}