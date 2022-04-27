package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository


class CleanCanvasUseCase(private val canvasRepository: CanvasRepository) {
    fun cleanCanvas() {
        canvasRepository.cleanCanvas()
    }

}
