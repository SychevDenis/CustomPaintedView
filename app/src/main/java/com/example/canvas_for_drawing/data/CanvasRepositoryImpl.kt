package com.example.canvas_for_drawing.data

import android.graphics.Color
import android.graphics.Path
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas


object CanvasRepositoryImpl : CanvasRepository {
    private var colorBack = MutableLiveData<Int>()//звет фона
    private var paths = mutableListOf<Path>() //слои нарисованных объектов
    private var pathsLD = MutableLiveData<MutableList<Path>>() //слои нарисованных объектов LD
    private var activeLayerLD = MutableLiveData<Int>()//активный слой
    private var activeLayer = 0//активный слой


    override fun setColorBack(): LiveData<Int> {
        colorBack.value = Color.WHITE
        return colorBack
    }

    override fun paintMoveTo(drawingObject: MutableLiveData<DrawingObject>):MutableLiveData<DrawingObject> { // начала нового объекта
        drawingObject.value?.let {
            activeLayer = it.infoLayer.activeLayerCanvas//читаем активный слой
            clickNext(it.infoLayer)//вперед по слоям
            activeLayerLD.value = activeLayer
            val path = Path()
            paths.add(path) //добавляем его в массив
            paths.last().moveTo(it.eventX, it.eventY)
            Log.i("log", paths.size.toString() + " слоев")
            Log.i("log", "${activeLayer} слой активный")
            pathsLD.value = paths
        }
        return drawingObject
    }

    override fun paintLineTo(drawingObject: MutableLiveData<DrawingObject>):MutableLiveData<DrawingObject>{ // маршрут нового объекта
        drawingObject.value?.let {
            paths.last().lineTo(it.eventX, it.eventY)
            pathsLD.value = paths
        }
        return drawingObject
    }

    override fun showCanvas(): MutableLiveData<MutableList<Path>> {
        return pathsLD
    }


    override fun clickBack(infoLayerCanvas: InfoLayerCanvas) {// назад по слоям
        Log.i("log", paths.size.toString() + " слоев")
        Log.i("log", "${activeLayer} слой активный")
        activeLayer--
        activeLayerLD.value = activeLayer
    }

    override fun clickNext(infoLayerCanvas: InfoLayerCanvas) { //вперед по слоям
        Log.i("log", paths.size.toString() + " слоев")
        Log.i("log", "${activeLayer} слой активный")
        activeLayer++
        activeLayerLD.value = activeLayer
    }

    override fun getInfoLayer(): MutableLiveData<Int> { //отслеживание переменной автивного слоя
        return activeLayerLD
    }

    override fun delListToActiveLayer(drawingObject: MutableLiveData<DrawingObject>) {
        drawingObject.value?.let {
            while (paths.size > it.infoLayer.activeLayerCanvas) {
                paths.removeLast()
            }
        }
    }

    override fun cleanCanvas() {
        while (paths.size > 0) {
            paths.removeLast()
        }
        activeLayer=0
        activeLayerLD.value = activeLayer
    }
}


