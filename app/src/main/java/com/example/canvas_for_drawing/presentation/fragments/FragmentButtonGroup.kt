package com.example.canvas_for_drawing.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.presentation.viewModels.ViewModelCSV
import com.example.canvas_for_drawing.presentation.viewModels.ViewModelMainActivity

class FragmentButtonGroup() : Fragment() {
    private val viewModelCSV: ViewModelCSV by activityViewModels()
    private val viewModelMainActivity: ViewModelMainActivity by activityViewModels()

    private lateinit var frameLayout: FrameLayout
    private lateinit var buttonBack: ImageButton
    private lateinit var buttonNext: ImageButton
    private lateinit var buttonWidthBrush: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_button_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVariables(view) //инициализация всех вью
        viewModelObserve() //подписываем вью модели на обновления
        updateBackgroundColor() //установка цвета заднего фона
        setListener() //подписываемся на слушатели
    }

    private fun viewModelObserve() {//подписываем вью модели на обновления
        //не вызывать до метода initVariables()
        viewModelMainActivity.vmStrokeWidth.observe(this) {
            paintIconButtonWidthBrush(it)
        }
    }

    private fun updateBackgroundColor() {//установка background вью элементов
        //не вызывать до метода initVariables()
        viewModelMainActivity.updateBackgroundColorApp(//обновить background элементов
            frameLayout, buttonBack, buttonNext, buttonWidthBrush
        )
    }

    private fun setListener() {//подключаем все слушатели
        //не вызывать до метода initVariables()
        buttonBack.setOnClickListener() {
            viewModelCSV.backLayers()
        }
        buttonWidthBrush.setOnClickListener() {
            viewModelMainActivity.visibilityInversionViewWidthBrush()
        }
        buttonNext.setOnClickListener() {
            viewModelCSV.nextLayers()
        }
    }

    private fun initVariables(view: View) { //инициализация переменных
        frameLayout = view.findViewById(R.id.frame_layout_button_group)
        buttonBack = view.findViewById(R.id.view_fragment_button_back)
        buttonNext = view.findViewById(R.id.view_fragment_button_next)
        buttonWidthBrush = view.findViewById(R.id.view_fragment_button_group_brush_width)
    }

    private fun paintIconButtonWidthBrush(width: Float, ) {//создание иконки
        viewModelMainActivity.paintIconButtonWidthBrush(width, buttonWidthBrush)
    }
}



