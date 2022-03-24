package com.example.canvas_for_drawing.data

import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.BackLayer
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.ClearCanvas
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.CreatingNewThread
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.NextLayer
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.PaintLineTo
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.PaintMoveTo
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.SaveBitmap
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.SetSizeChanged
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair

class CanvasRepositoryImpl(
    private val saveBitmap: SaveBitmap,
    private val setSizeChanged: SetSizeChanged,
    private val backLayer: BackLayer,
    private val nextLayer: NextLayer,
    private val pathLineTo: PaintLineTo,
    private val pathMoveTo: PaintMoveTo,
    private val creatingNewThread:CreatingNewThread,
    private val clearCanvas: ClearCanvas
) : CanvasRepository {
    override fun setColorBackground(color: Int): Int {//установка цвета фона
        return color
    }

    override fun paintMoveTo(drawingObject: DrawingObject, pair: Pair): Pair {//начала нового объекта рисования
        return pathMoveTo.paint(drawingObject,pair)
    }

    override fun paintLineTo(drawingObject: DrawingObject, pair:Pair): Pair {//продолжение объекта рисования
        return pathLineTo.paint(drawingObject,pair)
    }


    override fun setSizeChanged(onSizeChanged: OnSizeChanged): OnSizeChanged {
        //возравщаем размер экрана
        return setSizeChanged.setSizeChanged(onSizeChanged)
    }

    override fun setProgressSeekBar(progress: Int): Int {//установить размер кисти
        return progress
        //   settingPaintObjectLD.value?.strokeWidth = progress.toFloat() ?: 10f
    }

    override fun backLayers(activeLayer:Int):Int {// назад по слоям
       return backLayer.backLayer(activeLayer)
    }

    override fun clearCanvas(pair: Pair, onSizeChanged: OnSizeChanged,
                             backColor: Int):Any? {
     return clearCanvas.clear(pair,onSizeChanged,backColor)
    }

    override fun nextLayers(activeLayer:Int, listSize:Int):Int { //вперед по слоям
        return nextLayer.nextLayer(activeLayer,listSize)
    }

    override fun creatingNewThread(pair: Pair, activeLayer:Int) { //создание новой ветки истории рисования
        creatingNewThread.create(pair, activeLayer)
    }

    override fun saveCanvas( //сохранение изображения
        onSizeChanged: OnSizeChanged,
        pair:Pair,
        activeLayer: Int
    ): Boolean { //запись в хранилище
        saveBitmap.toString()
        return saveBitmap.save(
            onSizeChanged,
            pair,
            activeLayer
        )
    }
}



