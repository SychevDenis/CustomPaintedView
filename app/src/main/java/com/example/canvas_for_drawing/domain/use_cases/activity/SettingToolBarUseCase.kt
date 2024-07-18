package com.example.canvas_for_drawing.domain.use_cases.activity
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import javax.inject.Inject

class SettingToolBarUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryActivity) {
    fun setting(activity: Any, title: String,color: Int): Boolean {
       return canvasRepository.settingToolBar(activity,title,color)
    }
}