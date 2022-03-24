package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import javax.inject.Inject

class SetColorBackgroundUseCase
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun setColorBack(color: Int):Int{
        return canvasRepository.setColorBackground(color)
    }
}