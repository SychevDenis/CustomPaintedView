package com.example.canvas_for_drawing.presentation

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.example.canvas_for_drawing.domain.models.DrawingObject

class AdapterDrawingObject(
) {
    fun convertToPath(
        pathListLD: MutableList<Path>,
        drawingObject: DrawingObject
    ) {
        if (drawingObject.eventAction == DrawingObject.ACTION_DOWN) {
            pathListLD.add(Path().apply { moveTo(drawingObject.eventX, drawingObject.eventY) })
        } else {
            pathListLD.last().lineTo(drawingObject.eventX, drawingObject.eventY)
        }
    }

    fun convertToPaint(
        paintListLD: MutableList<Paint>,
        drawingObject: DrawingObject
    ) {
        if (drawingObject.eventAction == DrawingObject.ACTION_DOWN) {
            paintListLD.add(Paint().apply {
                style = Paint.Style.STROKE
                color = drawingObject.color ?: Color.BLACK
                strokeWidth = drawingObject.strokeWidth ?: 10f
            })
        } else {
            paintListLD.last().apply {
                color = drawingObject.color ?: Color.BLACK
                strokeWidth = drawingObject.strokeWidth ?: 20f
            }
        }
    }
}

