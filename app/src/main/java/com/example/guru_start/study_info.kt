package com.example.guru_start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton


class study_info : AppCompatActivity() {

    lateinit var button4: Button
    lateinit var backButton: ImageButton
    lateinit var lect_info_btn: Button
    lateinit var lect_eva_btn: Button
    lateinit var ass_info_btn: Button
    lateinit var study_info_btn: Button
    lateinit var que_btn: Button
    lateinit var test_info_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_info)
        button4 = findViewById(R.id.ass_info_btn_main)
        button4.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        lect_info_btn = findViewById(R.id.lect_info_btn)
        lect_info_btn.setOnClickListener {
            val intent = Intent(this, class_info::class.java)
            startActivity(intent)
        }
        lect_eva_btn = findViewById(R.id.lect_eva_btn)
        lect_eva_btn.setOnClickListener {
            val intent = Intent(this, lecture_eval::class.java)
            startActivity(intent)
        }
        ass_info_btn = findViewById(R.id.ass_info_btn)
        ass_info_btn.setOnClickListener {
            val intent = Intent(this, ass_info::class.java)
            startActivity(intent)
        }
        study_info_btn = findViewById(R.id.study_info_btn)
        study_info_btn.setOnClickListener {
            val intent = Intent(this, study_info::class.java)
            startActivity(intent)
        }
        que_btn = findViewById(R.id.que_btn)
        que_btn.setOnClickListener {
            val intent = Intent(this, QnA_page::class.java)
            startActivity(intent)
        }

        test_info_btn = findViewById(R.id.test_info_btn)
        test_info_btn.setOnClickListener{
            val intent = Intent(this, test_info::class.java)
            startActivity(intent)
        }
    }
}