package com.example.canvas_for_drawing.domain.use_case

import android.graphics.Path
import com.example.canvas_for_drawing.domain.CanvasRepository

class ShowCanvasUseCase(private val canvasRepository: CanvasRepository) {
    fun showCanvas(): MutableList<Path> {
        return canvasRepository.showCanvas()
    }
}