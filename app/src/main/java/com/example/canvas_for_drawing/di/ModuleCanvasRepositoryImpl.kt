package com.example.canvas_for_drawing.di

import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.BackLayer
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.ClearCanvas
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.CreatingNewThread
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.NextLayer
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.PaintLineTo
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.PaintMoveTo
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.SaveBitmap
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.SetSizeChanged
import com.example.canvas_for_drawing.domain.CanvasRepository
import dagger.Module
import dagger.Provides

@Module
class ModuleCanvasRepositoryImpl {
    @Provides
    fun provideRepositoryImpl(
        saveBitmap: SaveBitmap,
        setSizeChanged: SetSizeChanged,
        backLayer: BackLayer,
        nextLayer: NextLayer,
        pathLineTo: PaintLineTo,
        pathMoveTo: PaintMoveTo,
        creatingNewThread: CreatingNewThread,
        clearCanvas: ClearCanvas
    ): CanvasRepository {
        return CanvasRepositoryImpl(
            saveBitmap,
            setSizeChanged,
            backLayer,
            nextLayer,
            pathLineTo,
            pathMoveTo,
            creatingNewThread,
            clearCanvas
        )
    }
}