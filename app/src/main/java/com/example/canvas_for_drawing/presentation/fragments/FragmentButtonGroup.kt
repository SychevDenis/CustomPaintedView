package com.example.canvas_for_drawing.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.view.animation.Animation
import android.view.animation.AnimationUtils

import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.presentation.ViewModelCSV

class FragmentButtonGroup() : Fragment(), Animation.AnimationListener {


    private val viewModelCSV: ViewModelCSV by activityViewModels()//нужно искать способ вставить
    //это через инъекцию, но я пока не знаю как

    private lateinit var buttonBack: Button
    private lateinit var buttonNext: Button
    private lateinit var textView: TextView
    private lateinit var seek: SeekBar


    private lateinit var inAnimator: Animation
    private lateinit var outAnimator: Animation


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_button_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVariables(view)//инициализация всех вью
        setListener()
    }

    private fun setListener() {//подключаем все слушатели
        outAnimator.setAnimationListener(this)
        inAnimator.setAnimationListener(this)

        buttonBack.setOnClickListener() {
            //  it.startAnimation(outAnimator)
            viewModelCSV.backLayers()
        }

        buttonNext.setOnClickListener() {
            //    it.startAnimation(inAnimator)
            viewModelCSV.nextLayers()
        }

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                setBrushSize(progress)
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    fun setBrushSize(progress: Int) {
        viewModelCSV.setProgressSeekBar(progress)
    }

    private fun initVariables(view: View) { //инициализация переменных
        seek = view.findViewById(R.id.seekBar)
        buttonBack = view.findViewById(R.id.button_back)
        buttonNext = view.findViewById(R.id.button_next)
        textView = view.findViewById(R.id.textViewProgress)
        inAnimator = AnimationUtils.loadAnimation(view.context, R.anim.alpha_in)
        outAnimator = AnimationUtils.loadAnimation(view.context, R.anim.alpha_out)
    }

    override fun onAnimationStart(animation: Animation?) {
        println("FragmentBG start animation ${animation.toString()}")
    }

    override fun onAnimationEnd(animation: Animation?) {
        println("FragmentBG end animation ${animation.toString()}")
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }
}
