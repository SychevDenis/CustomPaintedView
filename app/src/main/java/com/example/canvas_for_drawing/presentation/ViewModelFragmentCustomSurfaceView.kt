package com.example.canvas_for_drawing.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.SettingPaintObject
import com.example.canvas_for_drawing.domain.use_case.*


class ViewModelFragmentCustomSurfaceView() : ViewModel() {

    private val repository=CanvasRepositoryImpl

    private val setColorBack = SetColorBackUseCase(repository)
    fun setColorBack()=setColorBack.setColorBack()

    private val paint = PaintUseCase(repository)
    fun paint(drawingObject: MutableLiveData<DrawingObject>) =paint.paint(drawingObject)

    private val showCanvasPath = ShowCanvasPathsUseCase(repository)
    fun showCanvasPaths()=showCanvasPath.showCanvasPaths()

    private val showCanvas = ShowCanvasPaintUseCase(repository)
    fun showCanvasPaint()=showCanvas.showCanvasPaint()

    private val clickBack = ClickBackUseCase(repository)
    fun clickBack(infoLayerCanvas: InfoLayerCanvas)=clickBack.clickBack(infoLayerCanvas)

    private val clickNext = ClickNextUseCase(repository)
    fun clickNext(infoLayerCanvas: InfoLayerCanvas)=clickNext.clickNext(infoLayerCanvas)

    private val getInfoLayer = GetInfoLayerUseCase(repository)
    fun getInfoLayer()=getInfoLayer.getInfoLayer()

    private val cleanCanvas = CleanCanvasUseCase(repository)
    fun cleanCanvas()=cleanCanvas.cleanCanvas()

    private val saveCanvas = SaveCanvasUseCase(repository)
    fun saveCanvas(onSizeChanged: OnSizeChanged)=saveCanvas.saveCanvas(onSizeChanged)

    private val settingPaint = SettingPaintUseCase(repository)
    fun settingPaint(settingPaintObject: SettingPaintObject)=settingPaint.settingPaint(settingPaintObject)
}