package com.example.canvas_for_drawing.domain.use_cases.activity
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import javax.inject.Inject

class GetDateTimeUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryActivity) {
    fun getDateTime(formatFilePicture:String): String {
       return canvasRepository.getDateTime(formatFilePicture)
    }
}