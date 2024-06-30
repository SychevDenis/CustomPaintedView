package com.example.canvas_for_drawing.domain.use_cases.color_use_cases

import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryColor
import javax.inject.Inject

class SetColorStrokeUseCases
@Inject constructor(private val canvasRepository: CanvasRepositoryColor) {
    fun setColorStroke(color: Int):Int{
      return canvasRepository.setColorStroke(color)
    }
}