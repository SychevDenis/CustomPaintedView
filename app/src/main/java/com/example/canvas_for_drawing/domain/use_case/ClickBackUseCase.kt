package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas

class ClickBackUseCase(private val canvasRepository: CanvasRepository) {
    fun clickBack(infoLayerCanvas: InfoLayerCanvas) {
        if (infoLayerCanvas.activeLayerCanvas>0)
       canvasRepository.clickBack(infoLayerCanvas)
    }
}