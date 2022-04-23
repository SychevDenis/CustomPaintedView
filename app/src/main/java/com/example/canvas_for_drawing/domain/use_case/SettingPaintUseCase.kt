package com.example.canvas_for_drawing.domain.use_case

import com.example.canvas_for_drawing.domain.CanvasRepository
import com.example.canvas_for_drawing.domain.models.SettingPaintObject


class SettingPaintUseCase(private val canvasRepository: CanvasRepository) {
    fun settingPaint(settingPaintObject: SettingPaintObject) : SettingPaintObject {
       return canvasRepository.settingPaint(settingPaintObject)
    }

}
