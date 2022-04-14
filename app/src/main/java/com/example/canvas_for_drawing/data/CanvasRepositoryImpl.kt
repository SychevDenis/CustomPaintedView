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
    var activeLayerLD = MutableLiveData<Int>()//активный слой
    private var activeLayer = 0//активный слой


    override fun setColorBack(): LiveData<Int> {
        colorBack.value = Color.WHITE
        return colorBack
    }

    override fun paintMoveTo(
        drawingObject: DrawingObject,
    ): MutableList<Path> { // начала нового объекта
        activeLayer = drawingObject.infoLayer.activeLayerCanvas//читаем активный слой
        clickNext(drawingObject.infoLayer)//вперед по слоям
        activeLayerLD.value= activeLayer
        val path = Path()
        paths.add(path) //добавляем его в массив
        paths.last().moveTo(drawingObject.eventX, drawingObject.eventY)
        Log.i("log", paths.size.toString() + " слоев")
        Log.i("log", "${activeLayer} слой активный")
        return paths
    }

    override fun paintLineTo(drawingObject: DrawingObject): MutableList<Path> { // маршрут нового объекта
        paths.last().lineTo(drawingObject.eventX, drawingObject.eventY)
        return paths
    }

    override fun showCanvas(): MutableList<Path> { //обновить канвас
        return paths
    }

    override fun clickBack(infoLayerCanvas: InfoLayerCanvas) {// назад по слоям
        Log.i("log", paths.size.toString() + " слоев")
        Log.i("log", "${activeLayer} слой активный")
        activeLayer--
        activeLayerLD.value = activeLayer
    }

    override fun clickNext(infoLayerCanvas: InfoLayerCanvas){ //вперед по слоям
        Log.i("log", paths.size.toString() + " слоев")
        Log.i("log", "${activeLayer} слой активный")
        activeLayer++
        activeLayerLD.value = activeLayer
    }

    override fun getInfoLayer():MutableLiveData<Int>  { //отслеживание переменной автивного слоя
        return activeLayerLD
    }

    override fun delListToActiveLayer(drawingObject: DrawingObject) {
        while (paths.size > drawingObject.infoLayer.activeLayerCanvas) {
            paths.removeLast()
        }
    }


}