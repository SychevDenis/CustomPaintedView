package com.example.canvas_for_drawing.domain.use_cases.color_use_cases
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryColor
import javax.inject.Inject

class RemoveColorInBarUseCases
@Inject constructor(private val canvasRepository: CanvasRepositoryColor) {
    fun removeColor(list: MutableList<Int>):MutableList<Int>{
       return canvasRepository.removeColorInBar(list)
    }
}