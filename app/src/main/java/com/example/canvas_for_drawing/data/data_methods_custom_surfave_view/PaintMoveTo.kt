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
                style = Paint.Style.STROKE
                color = it.color ?: Color.BLACK
                strokeWidth = it.strokeWidth ?: 10f
            })
        }
        return pair
    }
}