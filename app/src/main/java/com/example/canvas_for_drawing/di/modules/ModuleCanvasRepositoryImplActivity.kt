package com.example.canvas_for_drawing.di.modules

import com.example.canvas_for_drawing.data.data_methods_activity.CreateColorCircleDrawables
import com.example.canvas_for_drawing.data.data_methods_activity.GetDateTime
import com.example.canvas_for_drawing.data.data_methods_activity.GetPermissionWriteReadExternalStorage
import com.example.canvas_for_drawing.data.data_methods_activity.InitFragments
import com.example.canvas_for_drawing.data.data_methods_activity.PaintIconButtonWidthBrush
import com.example.canvas_for_drawing.data.data_methods_activity.UpdateBackgroundColorApp
import com.example.canvas_for_drawing.data.data_methods_activity.SettingNavigationBar
import com.example.canvas_for_drawing.data.data_methods_activity.SettingToolBar
import com.example.canvas_for_drawing.data.repositors.CanvasRepositoryImplActivity
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import com.example.canvas_for_drawing.domain.use_cases.activity.PaintIconButtonWidthBrushUseCase
import dagger.Module
import dagger.Provides

@Module
class ModuleCanvasRepositoryImplActivity {
    @Provides
    fun provideRepositoryImpl(
        getDateTime: GetDateTime,
        createColorCircleDrawables: CreateColorCircleDrawables,
        settingToolBar: SettingToolBar,
        settingNavigationBar:SettingNavigationBar,
        initFragments: InitFragments,
        getPermissionWriteReadExternalStorage: GetPermissionWriteReadExternalStorage,
        updateBackgroundColorApp: UpdateBackgroundColorApp,
        paintIconButtonWidthBrush: PaintIconButtonWidthBrush
    ): CanvasRepositoryActivity {
        return CanvasRepositoryImplActivity(
            getDateTime,
            createColorCircleDrawables,
            settingToolBar,
            settingNavigationBar,
            initFragments,
            getPermissionWriteReadExternalStorage,
            updateBackgroundColorApp,
            paintIconButtonWidthBrush
        )
    }
}