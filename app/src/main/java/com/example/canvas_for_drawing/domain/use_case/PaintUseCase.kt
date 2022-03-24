package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject

class PaintUseCase
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun paintMoveTo(drawingObject: DrawingObject, pair: Pair): Pair {
        return canvasRepository.paintMoveTo(drawingObject,pair)
    }
    fun paintLineTo(drawingObject: DrawingObject, pair:Pair): Pair {
        return canvasRepository.paintLineTo(drawingObject,pair)
    }
}

