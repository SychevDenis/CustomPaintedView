package com.example.canvas_for_drawing.domain.use_case.color_use_cases
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryColor
import javax.inject.Inject

class AddColorInBarUseCases
@Inject constructor(private val canvasRepository: CanvasRepositoryColor) {
    fun addColor(color:Int,list: MutableList<Int>,maxLength:Int):MutableList<Int>{
       return canvasRepository.addColorInBar(color,list,maxLength)
    }
}