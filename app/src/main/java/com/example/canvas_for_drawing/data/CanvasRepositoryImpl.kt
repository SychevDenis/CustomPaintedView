package com.example.canvas_for_drawing.data

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Environment
import androidx.core.graphics.applyCanvas
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.SettingPaintObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object CanvasRepositoryImpl : CanvasRepository {
    private var colorBack = MutableLiveData<Int>()//звет фона
    private var paths = mutableListOf<Path>() //слои нарисованных объектов
    private var pathsLD = MutableLiveData<MutableList<Path>>() //слои нарисованных объектов LD
    private var paints = mutableListOf<Paint>() //слои паинтов
    private var paintsLD = MutableLiveData<MutableList<Paint>>()//слои паинтов LD
    private var activeLayerLD = MutableLiveData<Int>()//активный слой
    private var activeLayer = 0//активный слой

    override fun setColorBack(): LiveData<Int> {
        colorBack.value = Color.WHITE
        return colorBack
    }

    override fun paintMoveTo(drawingObject: MutableLiveData<DrawingObject>): MutableLiveData<DrawingObject> { // начала нового объекта
        drawingObject.value?.let {
            activeLayer = it.infoLayer.activeLayerCanvas//читаем активный слой
            clickNext(it.infoLayer)//вперед по слоям
            activeLayerLD.value = activeLayer
            val path = Path()
            paths.add(path) //добавляем его в массив
            paths.last().moveTo(it.eventX, it.eventY)
            pathsLD.value = paths
            val paint=Paint(Paint.SUBPIXEL_TEXT_FLAG)
            paints.add(paint)
            paints.last().style = Paint.Style.STROKE
            paints.last().color=it.settingPaint.color
            paints.last().strokeWidth=it.settingPaint.strokeWidth
            paintsLD.value= paints
        }
        return drawingObject
    }

    override fun paintLineTo(drawingObject: MutableLiveData<DrawingObject>): MutableLiveData<DrawingObject> { // маршрут нового объекта
        drawingObject.value?.let {
            paths.last().lineTo(it.eventX, it.eventY)
            pathsLD.value = paths
            paints.last().color=it.settingPaint.color
            paints.last().strokeWidth=it.settingPaint.strokeWidth
            paintsLD.value= paints
        }
        return drawingObject
    }

    override fun showCanvasPaths(): MutableLiveData<MutableList<Path>> {
        return pathsLD
    }
    override fun showCanvasPaint(): MutableLiveData<MutableList<Paint>> {
        return paintsLD
    }

    override fun settingPaint(settingPaintObject: SettingPaintObject):SettingPaintObject {
       return settingPaint(settingPaintObject)
    }


    override fun clickBack(infoLayerCanvas: InfoLayerCanvas) {// назад по слоям
        activeLayer--
        activeLayerLD.value = activeLayer
    }

    override fun clickNext(infoLayerCanvas: InfoLayerCanvas) { //вперед по слоям
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
                paints.removeLast()
            }
        }
    }

    override fun cleanCanvas() {
        while (paths.size > 0) {
            paths.removeLast()
            paints.removeLast()
        }
        activeLayer = 0
        activeLayerLD.value = activeLayer

    }

    override fun saveCanvas(onSizeChanged: OnSizeChanged) { //запись в хранилище
        val myDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), ""
        )
        myDir.mkdirs()
        try {
            val filename = "myFile.jpg" //имя файла
            val file = File(myDir, filename)
            val bitmap = Bitmap.createBitmap(onSizeChanged.w, onSizeChanged.h, Bitmap.Config.ARGB_8888)
            val paint: Paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)
            paint.apply { //default
                style = Paint.Style.STROKE
                strokeWidth = 10f
                color = Color.BLACK
            }
            bitmap.applyCanvas {
                if (paths.isNotEmpty()) {
                    var i = 1
                    this.drawColor(Color.WHITE)
                    while (i <= activeLayer) {
                        this.drawPath(paths[i - 1], paint) //рисуем массив
                        i++
                    }
                }
            }
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush()
            out.close()//закрываем поток
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}


