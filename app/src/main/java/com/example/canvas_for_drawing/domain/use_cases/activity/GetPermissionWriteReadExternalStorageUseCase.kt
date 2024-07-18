package com.example.canvas_for_drawing.domain.use_cases.activity

import android.app.Activity
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import javax.inject.Inject

class GetPermissionWriteReadExternalStorageUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryActivity) {
    fun get(context: Activity): Boolean {
        return canvasRepository.getPermissionWriteReadExternalStorage(context)
    }
}