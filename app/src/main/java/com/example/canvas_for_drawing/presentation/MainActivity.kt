package com.example.canvas_for_drawing.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.canvas_for_drawing.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var customSurfaceView: CustomSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customSurfaceView = findViewById(R.id.custom_surfaceView)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setColorBack().observe(this) {
            customSurfaceView.colorCanvas=it
        }
        customSurfaceView.modelDrawingObjectLD.observe(this){
           customSurfaceView.paths = viewModel.paint(it)
           //Log.i("Log", it.eventY.toString() +" "+ it.eventAction.toString()+ " "+ it.eventX.toString())
        }
    }
}

