package com.example.canvas_for_drawing.data.data_methods_activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.FrameLayout
import android.widget.ImageButton
import javax.inject.Inject

class UpdateBackgroundColorApp @Inject constructor() {
    fun update(
        color: String,
        frameLayout: FrameLayout,
        buttonBack: ImageButton,
        buttonNext: ImageButton,
        buttonWidthBrush: ImageButton
    ): Boolean {
        frameLayout.setBackgroundColor(Color.parseColor(color))
        buttonBack.backgroundTintList = createBackgroundTintList(color, color)
        buttonNext.backgroundTintList = createBackgroundTintList(color, color)
        buttonWidthBrush.backgroundTintList = createBackgroundTintList(color,color)
        return true
    }

    private fun createBackgroundTintList( //создание BackgroundTintList
        colorStatePressed: String,
        colorNormalState: String
    ): ColorStateList {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_pressed), // Нажатое состояние
            intArrayOf() //  Обычное состояние
        )
        val colors = intArrayOf(
            Color.parseColor(colorStatePressed),  // Красный при нажатии
            Color.parseColor(colorNormalState)  // Серый в обычном состоянии
        )
        return ColorStateList(states, colors)
    }
}