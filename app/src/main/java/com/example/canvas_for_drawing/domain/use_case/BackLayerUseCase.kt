package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import javax.inject.Inject

class BackLayerUseCase
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun backLayer(activeLayer:Int):Int {
        return canvasRepository.backLayers(activeLayer)
    }
}