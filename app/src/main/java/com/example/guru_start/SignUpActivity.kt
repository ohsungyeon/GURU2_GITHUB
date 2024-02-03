package com.example.guru_start

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    lateinit var dbHelper:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val nicknameEditText: EditText = findViewById(R.id.nicknameEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val signup_stuEditText: EditText = findViewById(R.id.signup_stuEditText)
        val signup_idEditText: EditText = findViewById(R.id.signup_idEditText)
        val signup_pwEditText: EditText = findViewById(R.id.signup_pwEditText)
        val signup_pwconfEditText: EditText = findViewById(R.id.signup_pwconfEditText)

        val signupButton2: Button = findViewById(R.id.signupButton2)

        dbHelper=DBHelper(this)

        signupButton2.setOnClickListener {
            val name = nameEditText.text.toString()
            val nickname = nicknameEditText.text.toString()
            val email = emailEditText.text.toString()
            val stunum2 = signup_stuEditText.text.toString()
            val id2 = signup_idEditText.text.toString()
            val password2 = signup_pwEditText.text.toString()
            val pwconf = signup_pwconfEditText.text.toString()

            if (name.isBlank() || nickname.isBlank() || email.isBlank() || stunum2.isBlank() || id2.isBlank() || password2.isBlank() || pwconf.isBlank()) {
                Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (password2 != pwconf) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                dbHelper.addUser(name, nickname, email, stunum2, id2, password2 )
                Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
                finish()

            }

        }
        val loginTextView: TextView = findViewById(R.id.gotoLoginText)
        val spannableString = SpannableString("로그인하기")
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        loginTextView.text = spannableString

        loginTextView.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LogInActivity::class.java)
            startActivity(intent)
        }
    }

}



