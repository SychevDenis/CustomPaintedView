package com.example.canvas_for_drawing.domain.use_case


import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject


class PaintUseCase(private val canvasRepository: CanvasRepository) {
    fun paint(drawingObject: MutableLiveData<DrawingObject>):MutableLiveData<DrawingObject> {
        drawingObject.value?.let {
            if (it.eventAction == ACTION_DOWN) {
                if (it.infoLayer.activeLayerCanvas <= it.infoLayer.layerSizeCanvas) {
                    canvasRepository.delListToActiveLayer(drawingObject)
                }
                canvasRepository.paintMoveTo(drawingObject)
            } else
                canvasRepository.paintLineTo(drawingObject)
        }
        return drawingObject
    }

    companion object {
        val ACTION_DOWN = 0
        val ACTION_MOVE = 2
        val ACTION_UP = 1
    }

}
