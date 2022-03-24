package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import javax.inject.Inject


class NextLayerUseCase
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun nextLayer(activeLayer:Int, listSize:Int):Int {
       return canvasRepository.nextLayers(activeLayer,listSize)
    }
}