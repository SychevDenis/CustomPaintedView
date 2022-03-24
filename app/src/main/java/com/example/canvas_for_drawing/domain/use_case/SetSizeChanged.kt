package com.example.canvas_for_drawing.domain.use_case
import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import javax.inject.Inject

class SetSizeChanged
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun setSizeChanged(onSizeChanged: OnSizeChanged):OnSizeChanged{
       return canvasRepository.setSizeChanged(onSizeChanged)
    }
}