package com.example.canvas_for_drawing.data

import android.graphics.Color
import android.graphics.Path
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject

object CanvasRepositoryImpl:CanvasRepository {
    private var colorBack=MutableLiveData<Int>()
    private var drawingObject=MutableLiveData<DrawingObject>()
    private val path = android.graphics.Path()

    override fun setColorBack(): LiveData<Int> {
        colorBack.value=Color.WHITE
        return colorBack
    }

    override fun paintMoveTo(drawingObject: DrawingObject): Path {
        path.moveTo(drawingObject.eventX,drawingObject.eventY)
        return path
    }

    override fun paintLineTo(drawingObject: DrawingObject): Path {
        path.lineTo(drawingObject.eventX,drawingObject.eventY)
        return path
    }
}