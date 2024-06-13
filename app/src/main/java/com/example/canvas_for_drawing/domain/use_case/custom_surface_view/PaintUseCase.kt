package com.example.canvas_for_drawing.domain.use_case.custom_surface_view

import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject

class PaintUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryCustomSurfaceView) {
    fun paintMoveTo(drawingObject: DrawingObject, pair: Pair): Pair {
        return canvasRepository.paintMoveTo(drawingObject,pair)
    }
    fun paintLineTo(drawingObject: DrawingObject, pair:Pair): Pair {
        return canvasRepository.paintLineTo(drawingObject,pair)
    }
}

