package com.example.canvas_for_drawing.data

import android.graphics.Color
import android.graphics.Path
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject

object CanvasRepositoryImpl:CanvasRepository {
    private var colorBack=MutableLiveData<Int>()
    var paths = mutableListOf<Path>() //слои нарисованных объектов

    override fun setColorBack(): LiveData<Int> {
        colorBack.value=Color.WHITE
        return colorBack
    }

    override fun paintMoveTo(drawingObject: DrawingObject): MutableList<Path>{
        val path = Path()
        paths.add(path) //добавляем его в массив
        paths.last().moveTo(drawingObject.eventX,drawingObject.eventY)  //рисуем начальную точку.
        Log.i("log", paths.size.toString())
        return paths
    }

    override fun paintLineTo(drawingObject: DrawingObject): MutableList<Path> {
        paths.last().lineTo(drawingObject.eventX,drawingObject.eventY)
        return paths
    }
}