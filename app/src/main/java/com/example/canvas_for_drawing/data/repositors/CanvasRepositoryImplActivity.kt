package com.example.canvas_for_drawing.data.repositors

import android.app.Activity
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.canvas_for_drawing.data.data_methods_activity.CreateColorCircleDrawables
import com.example.canvas_for_drawing.data.data_methods_activity.GetDateTime
import com.example.canvas_for_drawing.data.data_methods_activity.GetPermissionWriteReadExternalStorage
import com.example.canvas_for_drawing.data.data_methods_activity.InitFragments
import com.example.canvas_for_drawing.data.data_methods_activity.PaintIconButtonWidthBrush
import com.example.canvas_for_drawing.data.data_methods_activity.UpdateBackgroundColorApp
import com.example.canvas_for_drawing.data.data_methods_activity.SettingNavigationBar
import com.example.canvas_for_drawing.data.data_methods_activity.SettingToolBar
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import com.example.canvas_for_drawing.domain.use_cases.activity.PaintIconButtonWidthBrushUseCase

class CanvasRepositoryImplActivity(
    private val getDateTime: GetDateTime,
    private val createColorCircleDrawables: CreateColorCircleDrawables,
    private val settingToolBar: SettingToolBar,
    private val settingNavigationBar: SettingNavigationBar,
    private val initFragments: InitFragments,
    private val getPermissionWriteReadExternalStorage: GetPermissionWriteReadExternalStorage,
    private val updateBackgroundColorAppUseCase: UpdateBackgroundColorApp,
    private val paintIconButtonWidthBrush: PaintIconButtonWidthBrush
) : CanvasRepositoryActivity {
    override fun getDateTime(formatFilePicture: String): String {
        return getDateTime.getDateTime(formatFilePicture)
    }

    override fun createColorCircleDrawables(color: Int): Any {
        return createColorCircleDrawables.create(color)
    }

    override fun updateBackgroundColorAppUseCase(
        color: String,
        frameLayout: Any,
        buttonBack: Any,
        buttonNext: Any,
        buttonWidthBrush: Any
    ): Boolean {
        return updateBackgroundColorAppUseCase.update(
            color, frameLayout as FrameLayout, buttonBack as ImageButton,
            buttonNext as ImageButton,buttonWidthBrush as ImageButton)
    }

    override fun paintIconButtonWidthBrush(width: Float, buttonWidthBrush: ImageButton): Boolean {
       return paintIconButtonWidthBrush.paint(width,buttonWidthBrush)
    }


    override fun settingToolBar(activity: Any, title: String, color: Int): Boolean {
        return settingToolBar.setting(activity as AppCompatActivity, title, color)
    }

    override fun settingNavigationBar(activity: Any): Boolean {
        return settingNavigationBar.setting(activity)
    }

    override fun initFragment(activity: Any, savedInstanceState: Any?): Boolean {
        return initFragments.init(activity, savedInstanceState)
    }

    override fun getPermissionWriteReadExternalStorage(context: Any): Boolean {
        return getPermissionWriteReadExternalStorage.get(context as Activity)
    }
}



