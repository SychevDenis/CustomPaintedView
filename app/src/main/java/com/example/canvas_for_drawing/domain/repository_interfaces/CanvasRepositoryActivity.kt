package com.example.canvas_for_drawing.domain.repository_interfaces

import android.widget.ImageButton

interface CanvasRepositoryActivity {
    fun getDateTime(formatFilePicture: String): String
    fun createColorCircleDrawables(color: Int): Any //LayerDrawable
    fun updateBackgroundColorAppUseCase(
        color: String,
        frameLayout: Any,//FrameLayout
        buttonBack: Any,//ImageButton
        buttonNext: Any,//ImageButton
        buttonWidthBrush: Any//ImageButton
    ): Boolean
    fun paintIconButtonWidthBrush(width: Float,buttonWidthBrush: ImageButton):Boolean
    fun settingToolBar(activity: Any, title: String, color: Int): Boolean //AppCompatActivity
    fun settingNavigationBar(activity: Any): Boolean //Activity
    fun initFragment(activity: Any, savedInstanceState: Any?): Boolean
    fun getPermissionWriteReadExternalStorage(context: Any): Boolean
}