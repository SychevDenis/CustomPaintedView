package com.example.canvas_for_drawing.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.domain.models.SettingPaintObject
import com.example.canvas_for_drawing.presentation.MainActivity

class FragmentButtonGroup() : Fragment() {
    private lateinit var buttonBack: Button
    private lateinit var buttonNext: Button
    private lateinit var textView: TextView
    private lateinit var seek: SeekBar
    private val settingPaintObject = SettingPaintObject(0f, Color.BLACK)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_button_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     //   Log.i("log", "OnCreateBG")
        seek = view.findViewById(R.id.seekBar)
        buttonBack = view.findViewById(R.id.button_back)
        buttonNext = view.findViewById(R.id.button_next)
        textView = view.findViewById(R.id.textViewProgress)
        buttonBack.setOnClickListener() {
            MainActivity().onClickBack()
        }
        buttonNext.setOnClickListener() {
            MainActivity().onClickNext()
        }

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                setBrushSize(progress)
                textView.text=progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    fun setBrushSize(progress: Int) {
       // Log.i("log", progress.toString())
        settingPaintObject.strokeWidth = progress.toFloat()
        settingPaintObject.color=Color.BLACK
        MainActivity().settingPaint(settingPaintObject)
    }

}
