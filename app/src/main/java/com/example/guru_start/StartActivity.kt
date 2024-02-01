package com.example.guru_start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent

class StartActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 5000 // 5초 대기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Handler().postDelayed({
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)

            finish()
        }, SPLASH_TIME_OUT)

    }
}