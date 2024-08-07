package com.example.canvas_for_drawing.domain.use_cases.custom_surface_view

import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject


class CreatingNewThreadUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryCustomSurfaceView) {
    fun create(pair: Pair,activeLayer:Int){
        canvasRepository.creatingNewThread(pair,activeLayer)
    }
}