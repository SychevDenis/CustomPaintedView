package com.example.canvas_for_drawing.presentation

import android.graphics.Path
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas

import com.example.canvas_for_drawing.domain.use_case.*


class ViewModelFragmentCustomSurfaceView() : ViewModel() {

    private val repository=CanvasRepositoryImpl

    private val setColorBack = SetColorBackUseCase(repository)
    fun setColorBack()=setColorBack.setColorBack()

    private val paint = PaintUseCase(repository)
    fun paint(drawingObject: MutableLiveData<DrawingObject>) =paint.paint(drawingObject)

    private val showCanvas = ShowCanvasUseCase(repository)
    fun showCanvas()=showCanvas.showCanvas()

    private val clickBack = ClickBackUseCase(repository)
    fun clickBack(infoLayerCanvas: InfoLayerCanvas)=clickBack.clickBack(infoLayerCanvas)

    private val clickNext = ClickNextUseCase(repository)
    fun clickNext(infoLayerCanvas: InfoLayerCanvas)=clickNext.clickNext(infoLayerCanvas)

    private val getInfoLayer = GetInfoLayerUseCase(repository)
    fun getInfoLayer()=getInfoLayer.getInfoLayer()

    private val cleanCanvas = CleanCanvasUseCase(repository)
    fun cleanCanvas()=cleanCanvas.cleanCanvas()

}