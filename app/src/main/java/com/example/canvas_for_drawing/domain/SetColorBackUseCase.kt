package com.example.canvas_for_drawing.domain

import androidx.lifecycle.LiveData

class SetColorBackUseCase(private val canvasRepository: CanvasRepository) {
    fun setColorBack(): LiveData<Int> {
        return canvasRepository.setColorBack()
    }
}