package com.example.canvas_for_drawing.domain.use_case.custom_surface_view

import android.graphics.Paint
import android.graphics.Path
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair
import javax.inject.Inject

class SaveCanvasUseCase @Inject constructor (private val canvasRepository: CanvasRepositoryCustomSurfaceView) {
    fun saveCanvas(onSizeChanged: OnSizeChanged,
                   pair: Pair,
                   activeLayer: Int):Boolean {
       return canvasRepository.saveCanvas(onSizeChanged,pair,activeLayer)
    }

}
