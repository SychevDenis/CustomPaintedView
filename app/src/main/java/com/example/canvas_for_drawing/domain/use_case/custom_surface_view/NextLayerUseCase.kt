package com.example.canvas_for_drawing.domain.use_case.custom_surface_view

import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import javax.inject.Inject


class NextLayerUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryCustomSurfaceView) {
    fun nextLayer(activeLayer:Int, listSize:Int):Int {
       return canvasRepository.nextLayers(activeLayer,listSize)
    }
}