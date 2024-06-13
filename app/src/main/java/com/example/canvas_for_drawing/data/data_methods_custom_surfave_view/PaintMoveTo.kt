package com.example.canvas_for_drawing.data.data_methods_custom_surfave_view

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject

class PaintMoveTo @Inject constructor() {
    fun paint(drawingObject: DrawingObject, pair:Pair):Pair {
        drawingObject.let {
            pair.add(path = Path().apply {
                moveTo(it.eventX, it.eventY)
            }, paint = Paint().apply {
                style = Paint.Style.STROKE //стиль линии
                color = it.color ?: Color.BLACK //цвет кисти
                strokeWidth = it.strokeWidth ?: 10f //ширина строки
                strokeCap  = Paint.Cap.ROUND //сглаживание краев
                strokeJoin = Paint.Join.ROUND //сглаживание соединений
            })

        }
        return pair
    }
}