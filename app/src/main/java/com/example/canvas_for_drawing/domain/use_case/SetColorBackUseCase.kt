package com.example.canvas_for_drawing.domain.use_case

import androidx.lifecycle.LiveData
import com.example.canvas_for_drawing.domain.CanvasRepository

class SetColorBackUseCase(private val canvasRepository: CanvasRepository) {
    fun setColorBack(): LiveData<Int> {
        return canvasRepository.setColorBack()
    }
}