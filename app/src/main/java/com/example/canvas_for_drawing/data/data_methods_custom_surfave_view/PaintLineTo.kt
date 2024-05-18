package com.example.canvas_for_drawing.data.data_methods_custom_surfave_view

import android.graphics.Path
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject

class PaintLineTo @Inject constructor() {
    fun paint(drawingObject: DrawingObject, pair:Pair):Pair {
        val path=pair.getLastElementPath() as Path
        path.apply {
            lineTo(drawingObject.eventX, drawingObject.eventY)
        }
        return pair
    }
}