package com.example.canvas_for_drawing.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl
import com.example.canvas_for_drawing.domain.PaintUseCase
import com.example.canvas_for_drawing.domain.SetColorBackUseCase
import com.example.canvas_for_drawing.domain.ShowCanvasUseCase
import com.example.canvas_for_drawing.domain.models.DrawingObject


class FragmentSvViewModel() : ViewModel() {

    private val repository=CanvasRepositoryImpl
    private val setColorBack=SetColorBackUseCase(repository)

    fun setColorBack()=setColorBack.setColorBack()

    private val paint=PaintUseCase(repository)
    fun paint(drawingObject: DrawingObject)=paint.paint(drawingObject)

    private val showCanvas=ShowCanvasUseCase(repository)
    fun showCanvas()=showCanvas.showCanvas()


}