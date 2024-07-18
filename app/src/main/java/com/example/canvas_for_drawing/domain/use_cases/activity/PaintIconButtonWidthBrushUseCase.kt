package com.example.canvas_for_drawing.domain.use_cases.activity
import android.widget.ImageButton
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import javax.inject.Inject

class PaintIconButtonWidthBrushUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryActivity) {
    fun paint(width: Float,buttonWidthBrush:ImageButton): Boolean {
        return canvasRepository.paintIconButtonWidthBrush(width,buttonWidthBrush)
    }
}