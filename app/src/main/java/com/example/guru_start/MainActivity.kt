package com.example.guru_start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 5000 // 5초 대기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            // 다음 화면으로 이동하는 Intent
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)

            // 현재 액티비티 종료
            finish()
        }, SPLASH_TIME_OUT)

    }
}