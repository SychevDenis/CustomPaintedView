package com.example.canvas_for_drawing.di

import com.example.canvas_for_drawing.di.modules.ModuleCanvasRepositoryImplColor
import com.example.canvas_for_drawing.di.modules.ModuleCanvasRepositoryImplCustomSurfaceView
import com.example.canvas_for_drawing.di.modules.ModuleViewModel
import com.example.canvas_for_drawing.presentation.MainActivity
import dagger.Component

@Component(modules = [
    ModuleCanvasRepositoryImplCustomSurfaceView::class,
    ModuleCanvasRepositoryImplColor::class,
   ModuleViewModel::class])
interface ComponentActivity {
    fun inject(activity: MainActivity)
}
