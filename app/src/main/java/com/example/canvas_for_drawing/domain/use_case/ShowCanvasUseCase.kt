package com.example.canvas_for_drawing.domain.use_case

import android.graphics.Path
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository

class ShowCanvasUseCase(private val canvasRepository: CanvasRepository) {
    fun showCanvas(): MutableLiveData<MutableList<Path>> {
        return canvasRepository.showCanvas()
    }
}