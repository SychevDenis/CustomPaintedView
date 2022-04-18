package com.example.canvas_for_drawing.domain

import android.graphics.Path
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas

interface CanvasRepository {
    fun setColorBack():LiveData<Int>
    fun paintMoveTo(drawingObject: MutableLiveData<DrawingObject>):MutableLiveData<DrawingObject>
    fun paintLineTo(drawingObject: MutableLiveData<DrawingObject>):MutableLiveData<DrawingObject>
    fun showCanvas(): MutableLiveData<MutableList<Path>>
    fun clickBack(infoLayerCanvas: InfoLayerCanvas)
    fun clickNext(infoLayerCanvas: InfoLayerCanvas)
    fun getInfoLayer(): MutableLiveData<Int>
    fun delListToActiveLayer(drawingObject: MutableLiveData<DrawingObject>)
    fun cleanCanvas()
}