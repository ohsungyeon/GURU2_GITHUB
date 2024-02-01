package com.example.guru_start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val tabLayout: TabLayout=findViewById(R.id.tabs)
        setUpTabs(tabLayout)
    }

    private fun setUpTabs(tabLayout: TabLayout){
        tabLayout.addTab(tabLayout.newTab().setText("내가 쓴 글"))
        tabLayout.addTab(tabLayout.newTab().setText("질의응답"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position){
                    0-> showFragment(MyPostsFragment())
                    1-> showFragment(QnAFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        showFragment(MyPostsFragment())
    }
    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_content, fragment)
            .commit()
    }
}