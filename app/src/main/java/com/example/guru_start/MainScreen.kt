package com.example.guru_start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView

class MainScreen : AppCompatActivity() {
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var mypageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainscreen)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        mypageButton = findViewById(R.id.mypageButton)

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

        val spinner: Spinner = findViewById(R.id.spinner)
        val items = listOf("1학년", "2학년", "3학년", "4학년")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        val textView1 : TextView = findViewById(R.id.textView1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                // 선택된 아이템에 대한 처리
                textView1.text = selectedItem + " 커리큘럼 과목"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}

