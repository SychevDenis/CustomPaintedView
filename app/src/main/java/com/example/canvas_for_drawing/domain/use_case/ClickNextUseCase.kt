package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas


class ClickNextUseCase(private val canvasRepository: CanvasRepository) {
    fun clickNext(infoLayerCanvas: InfoLayerCanvas) {
        if (infoLayerCanvas.activeLayerCanvas < infoLayerCanvas.layerSizeCanvas)
            canvasRepository.clickNext(infoLayerCanvas)
    }
}