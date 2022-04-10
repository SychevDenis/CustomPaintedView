package com.example.canvas_for_drawing.presentation

import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl
import com.example.canvas_for_drawing.domain.PaintUseCase
import com.example.canvas_for_drawing.domain.SetColorBackUseCase
import com.example.canvas_for_drawing.domain.models.DrawingObject


class MainViewModel : ViewModel() {


    private val repository=CanvasRepositoryImpl

    private val setColorBack=SetColorBackUseCase(repository)
    fun setColorBack()=setColorBack.setColorBack()

    private val paint=PaintUseCase(repository)
    fun paint(drawingObject: DrawingObject)=paint.paint(drawingObject)

}