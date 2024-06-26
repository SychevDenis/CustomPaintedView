package com.example.canvas_for_drawing.domain

import android.graphics.Paint
import android.graphics.Path
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair


interface CanvasRepository {
    fun setColorBackground(color:Int):Int //установка цвета фона в custom view
    fun paintMoveTo(drawingObject: DrawingObject, pair: Pair):Pair
    fun paintLineTo(drawingObject: DrawingObject, pair:Pair):Pair
    fun nextLayers(activeLayer:Int, listSize:Int):Int
    fun backLayers(activeLayer:Int):Int
    fun clearCanvas(pair: Pair,onSizeChanged:OnSizeChanged,
                    backColor:Int):Any?
    fun creatingNewThread(pair: Pair, activeLayer:Int)
    fun saveCanvas( onSizeChanged: OnSizeChanged,
                    pair:Pair,
                    activeLayer: Int):Boolean
    fun setSizeChanged(onSizeChanged: OnSizeChanged):OnSizeChanged
    fun setProgressSeekBar(progress: Int):Int
}