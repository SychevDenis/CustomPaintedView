package com.example.canvas_for_drawing.di.modules

import com.example.canvas_for_drawing.data.repositors.CanvasRepositoryImplColor
import com.example.canvas_for_drawing.data.data_methods_color.AddColor
import com.example.canvas_for_drawing.data.data_methods_color.RemoveColor
import com.example.canvas_for_drawing.data.data_methods_color.SetColor
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryColor
import dagger.Module
import dagger.Provides

@Module
class ModuleCanvasRepositoryImplColor {
    @Provides
    fun provideRepositoryImpl(
        addColor: AddColor,
        removeColor: RemoveColor,
        setColor: SetColor
    ): CanvasRepositoryColor {
        return CanvasRepositoryImplColor(
            addColor,
            removeColor,
            setColor
        )
    }
}