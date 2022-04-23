package com.example.canvas_for_drawing.domain.use_case

import android.graphics.Path
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository

class ShowCanvasPathsUseCase(private val canvasRepository: CanvasRepository) {
    fun showCanvasPaths(): MutableLiveData<MutableList<Path>> {
        return canvasRepository.showCanvasPaths()
    }
}