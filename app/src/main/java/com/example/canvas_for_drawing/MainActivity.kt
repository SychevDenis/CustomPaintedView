package com.example.canvas_for_drawing

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var buttonClean:Button?=null
    private var buttonBack:Button?=null
    private var buttonNext:Button?=null
    private var customSurfaceView:CustomSurfaceView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonClean=findViewById(R.id.buttonClean)
        buttonBack=findViewById(R.id.buttonBack)
        buttonNext=findViewById(R.id.buttonNext)
        customSurfaceView=findViewById(R.id.customSurfaceView)
        buttonClean?.setOnClickListener(){
            customSurfaceView?.apply {
                clean=true
            }
        }
        buttonBack?.setOnClickListener(){
            customSurfaceView?.apply {
                back=true
            }
        }
        buttonNext?.setOnClickListener(){
            customSurfaceView?.apply {
                next=true
            }
        }
    }
}