package com.example.canvas_for_drawing.domain.use_case

import android.graphics.Path
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject


class PaintUseCase(private val canvasRepository: CanvasRepository) {
    fun paint(drawingObject: DrawingObject): MutableList<Path> {
        if (drawingObject.eventAction == ACTION_DOWN) {
            if (drawingObject.infoLayer.activeLayerCanvas<=drawingObject.infoLayer.layerSizeCanvas){
                canvasRepository.delListToActiveLayer(drawingObject)
            }
            return canvasRepository.paintMoveTo(drawingObject)
        } else
            return canvasRepository.paintLineTo(drawingObject)
    }

    companion object {
        val ACTION_DOWN = 0
        val ACTION_MOVE = 2
        val ACTION_UP = 1
    }

}
