package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject


class CreatingNewThreadUseCases
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun create(pair: Pair,activeLayer:Int){
        canvasRepository.creatingNewThread(pair,activeLayer)
    }
}