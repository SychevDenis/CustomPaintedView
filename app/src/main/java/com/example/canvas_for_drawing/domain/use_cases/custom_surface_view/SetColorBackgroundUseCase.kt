package com.example.canvas_for_drawing.domain.use_cases.custom_surface_view

import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import javax.inject.Inject

class SetColorBackgroundUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryCustomSurfaceView) {
    fun setColorBack(color: Int):Int{
        return canvasRepository.setColorBackground(color)
    }
}