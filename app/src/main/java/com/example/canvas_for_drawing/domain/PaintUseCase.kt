package com.example.canvas_for_drawing.domain

import android.graphics.Path
import com.example.canvas_for_drawing.domain.models.DrawingObject

class PaintUseCase(private val canvasRepository: CanvasRepository) {

    fun paint(drawingObject: DrawingObject): MutableList<Path> {
        return if (drawingObject.eventAction == ACTION_DOWN) {
            canvasRepository.paintMoveTo(drawingObject)
        } else
            canvasRepository.paintLineTo(drawingObject)
    }

    companion object {
        val ACTION_DOWN = 0
        val ACTION_MOVE = 2
        val ACTION_UP = 1
    }

}
