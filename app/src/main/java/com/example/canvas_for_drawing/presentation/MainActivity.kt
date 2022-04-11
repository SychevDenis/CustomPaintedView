package com.example.canvas_for_drawing.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.canvas_for_drawing.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction()
//            .add(R.id.CustomSvContainerView,CustomSurfaceViewFragment())
//            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}

