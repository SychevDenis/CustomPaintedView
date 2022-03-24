package com.example.canvas_for_drawing.di

import com.example.canvas_for_drawing.data.data_methods.BackLayer
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl
import com.example.canvas_for_drawing.data.data_methods.ClearCanvas
import com.example.canvas_for_drawing.data.data_methods.CreatingNewThread
import com.example.canvas_for_drawing.data.data_methods.NextLayer
import com.example.canvas_for_drawing.data.data_methods.PaintLineTo
import com.example.canvas_for_drawing.data.data_methods.PaintMoveTo
import com.example.canvas_for_drawing.data.data_methods.SaveBitmap
import com.example.canvas_for_drawing.data.data_methods.SetSizeChanged
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