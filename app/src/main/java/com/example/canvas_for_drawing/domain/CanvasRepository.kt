package com.example.canvas_for_drawing.domain

import android.graphics.Paint
import android.graphics.Path
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.SettingPaintObject

interface CanvasRepository {
    fun setColorBack():LiveData<Int>
    fun paintMoveTo(drawingObject: MutableLiveData<DrawingObject>):MutableLiveData<DrawingObject>
    fun paintLineTo(drawingObject: MutableLiveData<DrawingObject>):MutableLiveData<DrawingObject>
    fun showCanvasPaths(): MutableLiveData<MutableList<Path>>
    fun clickBack(infoLayerCanvas: InfoLayerCanvas)
    fun clickNext(infoLayerCanvas: InfoLayerCanvas)
    fun getInfoLayer(): MutableLiveData<Int>
    fun delListToActiveLayer(drawingObject: MutableLiveData<DrawingObject>)
    fun cleanCanvas()
    fun saveCanvas(onSizeChanged: OnSizeChanged)
    fun showCanvasPaint(): MutableLiveData<MutableList<Paint>>
    fun settingPaint(settingPaintObject: SettingPaintObject)
}