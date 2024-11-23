package com.cmc.regret_aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val customFragmentManager = supportFragmentManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showInit()
        initBottomNav()
    }

    private fun initBottomNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.itemIconTintList = null

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.action_retrospect -> {
                    RetrospectFragment().changeFragment()
                }

                R.id.action_my_page -> {
                    MyPageFragment().changeFragment()
                }
            }
            return@setOnItemSelectedListener true
        }

//        bottomNavigationView.setOnItemReselectedListener {  } // 바텀네비 재클릭시 화면 재생성 방지
    }

    private fun Fragment.changeFragment() {
        customFragmentManager.beginTransaction().replace(R.id.main_container, this).commit()
    }

    private fun showInit() {
        val transaction = customFragmentManager.beginTransaction()
            .add(R.id.main_container, RetrospectFragment())
        transaction.commit()
    }
}