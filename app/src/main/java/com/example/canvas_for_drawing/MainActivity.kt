package com.example.canvas_for_drawing

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var button:Button?=null
    private var customSurfaceView:CustomSurfaceView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button)
        customSurfaceView=findViewById(R.id.customSurfaceView)
        button?.setOnClickListener(){
            customSurfaceView?.apply {
                clear=true
            }
        }
    }
}