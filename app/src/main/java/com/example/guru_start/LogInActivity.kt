package com.example.guru_start

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LogInActivity : AppCompatActivity() {

    lateinit var stuEditText: EditText
    lateinit var idEditText: EditText
    lateinit var pwEditText: EditText
    lateinit var LoginButton: Button
    lateinit var SignupButton: Button

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        stuEditText = findViewById(R.id.stueditText)
        idEditText = findViewById(R.id.ideditText)
        pwEditText = findViewById(R.id.pweditText)
        LoginButton = findViewById(R.id.LoginButton)
        SignupButton = findViewById(R.id.SignupButton)

        dbHelper = DBHelper(this)

        LoginButton.setOnClickListener {
            val stunum = stuEditText.text.toString()
            val id = idEditText.text.toString()
            val password = pwEditText.text.toString()

            if (stunum.isEmpty() || id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val isUserValid = dbHelper.checkUser(stunum, id, password)

                if (isUserValid) {
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    // 다음 액티비티로 이동
                    // val intent
                    val intent=Intent(this, MainScreen::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "로그인 실패. 학번, 아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        SignupButton.setOnClickListener {
            val intent=Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            }
        }
    }


