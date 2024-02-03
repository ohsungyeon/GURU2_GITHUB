package com.example.guru_start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.guru_start.databinding.QRegistBinding

class A_regist : AppCompatActivity() {

    lateinit var binding : QRegistBinding
    var selectedItem: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_regist)

        // 바인딩 설정
        binding = QRegistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // spinner를 사용하여 드롭다운 설정
        val itemList = listOf("수강학기 선택", "2021-1", "2021-2", "2022-1", "2022-2", "2023-1", "2023-2")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //드롭다운에서 선택된 문자 변수에 저장
                selectedItem = parent?.getItemAtPosition(position).toString()
                if(position != 0) Toast.makeText(this@A_regist, itemList[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //뒤로가기 버튼
        val BackImageButton2 : ImageButton = findViewById(R.id.BackImageButton2)
        BackImageButton2.setOnClickListener{
//            val intent = Intent(this, A_page::class.java)
//            startActivity(intent)
            finish()
        }
    }
}