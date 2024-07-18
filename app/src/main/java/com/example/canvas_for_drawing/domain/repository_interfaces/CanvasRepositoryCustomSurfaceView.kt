package com.example.canvas_for_drawing.domain.repository_interfaces

import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair

interface CanvasRepositoryCustomSurfaceView {
    fun backLayers(activeLayer: Int): Int
    fun clearCanvas(
        pair: Pair, onSizeChanged: OnSizeChanged,
        backColor: Int
    ): Any?
    fun creatingNewThread(pair: Pair, activeLayer: Int)
    fun nextLayers(activeLayer: Int, listSize: Int): Int
    fun paintLineTo(drawingObject: DrawingObject, pair: Pair): Pair
    fun paintMoveTo(drawingObject: DrawingObject, pair: Pair): Pair
    fun saveBitmap(
        fileName: String, onSizeChanged: OnSizeChanged,
        pair: Pair,
        activeLayer: Int
    ): Boolean
}