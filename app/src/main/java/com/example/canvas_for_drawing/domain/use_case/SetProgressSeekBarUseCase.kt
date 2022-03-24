package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import javax.inject.Inject

class SetProgressSeekBarUseCase
@Inject constructor(private val canvasRepository: CanvasRepository) {
    fun setProgressSeekBar(progress: Int):Int {
       return canvasRepository.setProgressSeekBar(progress)
    }
}