package com.example.canvas_for_drawing.domain.use_cases.custom_surface_view
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import javax.inject.Inject

class SetSizeChanged
@Inject constructor(private val canvasRepository: CanvasRepositoryCustomSurfaceView) {
    fun setSizeChanged(onSizeChanged: OnSizeChanged):OnSizeChanged{
       return canvasRepository.setSizeChanged(onSizeChanged)
    }
}