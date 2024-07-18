package com.example.canvas_for_drawing.domain.use_cases.activity
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import javax.inject.Inject

class UpdateBackgroundColorAppUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryActivity) {
    fun update(
        color: String,
        frameLayout: Any,
        buttonBack: Any,
        buttonNext: Any,
        buttonWidthBrush: Any
    ): Boolean {
        return canvasRepository.updateBackgroundColorAppUseCase(
            color,frameLayout,buttonBack,buttonNext,buttonWidthBrush)
    }
}