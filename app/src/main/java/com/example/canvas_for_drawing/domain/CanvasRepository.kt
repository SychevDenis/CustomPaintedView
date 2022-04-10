package com.example.canvas_for_drawing.domain

import android.graphics.Path
import androidx.lifecycle.LiveData
import com.example.canvas_for_drawing.domain.models.DrawingObject

interface CanvasRepository {
    fun setColorBack():LiveData<Int>
    fun paintMoveTo(drawingObject: DrawingObject): Path
    fun paintLineTo(drawingObject: DrawingObject): Path

}