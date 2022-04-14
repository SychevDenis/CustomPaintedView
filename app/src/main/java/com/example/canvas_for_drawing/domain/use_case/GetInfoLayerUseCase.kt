package com.example.canvas_for_drawing.domain.use_case

import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository


class GetInfoLayerUseCase(private val canvasRepository: CanvasRepository) {
    fun getInfoLayer(): MutableLiveData<Int> {
       return canvasRepository.getInfoLayer()
    }
}