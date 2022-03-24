package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject


class ClearCanvasUseCase
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun clearCanvas(
        pair: Pair, onSizeChanged: OnSizeChanged,
        backColor: Int
    ): Any? {
        return canvasRepository.clearCanvas(pair, onSizeChanged, backColor)
    }
}
