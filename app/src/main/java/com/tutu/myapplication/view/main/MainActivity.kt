package com.tutu.myapplication.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tutu.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_container, MainFragment())
                .commitAllowingStateLoss()
    }
}