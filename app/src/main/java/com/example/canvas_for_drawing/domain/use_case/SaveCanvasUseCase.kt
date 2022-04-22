package com.example.canvas_for_drawing.domain.use_case

import android.graphics.Canvas
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.OnSizeChanged


class SaveCanvasUseCase(private val canvasRepository: CanvasRepository) {
    fun saveCanvas(onSizeChanged: OnSizeChanged) {
        canvasRepository.saveCanvas(onSizeChanged)
    }

}
