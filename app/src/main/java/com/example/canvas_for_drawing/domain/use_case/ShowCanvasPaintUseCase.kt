package com.example.canvas_for_drawing.domain.use_case

import android.graphics.Paint
import android.graphics.Path
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.CanvasRepository

class ShowCanvasPaintUseCase(private val canvasRepository: CanvasRepository) {
    fun showCanvasPaint(): MutableLiveData<MutableList<Paint>> {
        return canvasRepository.showCanvasPaint()
    }
}