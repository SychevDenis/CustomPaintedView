package com.example.canvas_for_drawing.domain.use_cases.color
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryColor
import javax.inject.Inject

class AddColorInPaletteUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryColor) {
    fun addColor(color:Int,list: MutableList<Int>,maxLength:Int):MutableList<Int>{
       return canvasRepository.addColorInPalette(color,list,maxLength)
    }
}