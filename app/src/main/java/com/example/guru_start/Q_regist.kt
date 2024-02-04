package com.example.guru_start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.database.sqlite.SQLiteDatabase
import com.example.guru_start.QuestionDBManager.Companion.TABLE_NAME
import android.R
import android.content.Intent
import com.example.guru_start.databinding.QRegistBinding

class Q_regist : AppCompatActivity() {

    //    lateinit var ReviewEditText: EditText
    lateinit var dbManager: QuestionDBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var binding : QRegistBinding
    //  ReviewEditText = findViewById<EditText>(R.id.ReviewEditText)  // ReviewEditText연결
    var RegistTypeStirng : String = ""
    var ReviewEditTextString : String = ""
    var selectedItem: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // 바인딩 설정
        binding = QRegistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // spinner를 사용하여 드롭다운 설정
        val itemList = listOf("수강학기 선택", "2021-1", "2021-2", "2022-1", "2022-2", "2023-1", "2023-2")
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, itemList)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //드롭다운에서 선택된 문자 변수에 저장
                selectedItem = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@Q_regist, itemList[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        dbManager = QuestionDBManager(this, "등록", null, 1)
        val databaseName = "등록"
        val path = applicationContext.getDatabasePath(databaseName).absolutePath
        val intent = Intent(this, QnA_page::class.java)

        //저장 버튼 클릭하면 등록이라는 DB의 QandAorStudySharing 테이블에 데이터 저장
        binding.StoreButton.setOnClickListener {
            ReviewEditTextString = binding.ReviewEditText.text.toString()
            RegistTypeStirng = binding.RegistTextView.text.toString()
            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO $TABLE_NAME VALUES ('$RegistTypeStirng', '$ReviewEditTextString', '$selectedItem')")
            sqlitedb.close()
            Toast.makeText(this@Q_regist, "등록완료", Toast.LENGTH_SHORT).show()
            intent.putExtra("databasePath", path)
            startActivity(intent)

        }

        //뒤로가기 버튼 누르면 화면 전환
        binding.BackImageButton2.setOnClickListener{
//            intent.putExtra("databasePath", path)
//            startActivity(intent)
            finish()
        }
    }
}