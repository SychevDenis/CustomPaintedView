package com.example.canvas_for_drawing.domain

import android.graphics.Path


class ShowCanvasUseCase(private val canvasRepository: CanvasRepository) {
    fun showCanvas(): MutableList<Path> {
        return canvasRepository.showCanvas()
    }
}