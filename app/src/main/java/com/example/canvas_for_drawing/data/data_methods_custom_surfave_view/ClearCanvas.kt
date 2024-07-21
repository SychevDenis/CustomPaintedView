package com.example.canvas_for_drawing.data.data_methods_custom_surfave_view

import android.graphics.Paint
import android.graphics.Path
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject

class ClearCanvas @Inject constructor() {

    fun clear(
        pair: Pair, onSizeChanged: OnSizeChanged,
        backColor: Int
    ): Pair? {
        //записываем максимальное значение между шириной и высотой экрана
        //такое большое число нужно на случай, если у приложения будет функция переворота экрана
        val max = (onSizeChanged.w.toFloat().coerceAtLeast
            (onSizeChanged.h.toFloat())) * 2
        val newPaint = (Paint().apply {
            style = Paint.Style.FILL
            color = backColor
        })
        val newPath = Path().apply {
            // Рисуем прямоугольник во весь экран устройства
            addRect(
                0f,
                0f,
                max,
                max,
                Path.Direction.CW
            )
        }

        if (pair.add(newPath, newPaint)) {
            return pair
        } else {
            return null
        }
    }
}