package com.example.canvas_for_drawing.domain.use_cases.activity
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import javax.inject.Inject

class CreateColorCircleDrawablesUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryActivity) {
    fun create(color: Int): Any { //LayerDrawable
       return canvasRepository.createColorCircleDrawables(color)
    }
}