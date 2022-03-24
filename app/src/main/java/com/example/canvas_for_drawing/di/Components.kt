package com.example.canvas_for_drawing.di

import com.example.canvas_for_drawing.presentation.MainActivity
import dagger.Component

@Component(modules = [
    ModuleCanvasRepositoryImpl::class,
   ModuleViewModel::class])
interface ComponentActivity {
    fun inject(activity: MainActivity)
}
