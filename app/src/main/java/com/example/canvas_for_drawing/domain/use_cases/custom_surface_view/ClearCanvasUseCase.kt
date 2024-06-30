package com.example.canvas_for_drawing.domain.use_cases.custom_surface_view

import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject


class ClearCanvasUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryCustomSurfaceView) {
    fun clearCanvas(
        pair: Pair, onSizeChanged: OnSizeChanged,
        backColor: Int
    ): Any? {
        return canvasRepository.clearCanvas(pair, onSizeChanged, backColor)
    }
}
