package com.example.canvas_for_drawing.di.modules

import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.BackLayer
import com.example.canvas_for_drawing.data.repositors.CanvasRepositoryImplCustomSurfaceView
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.ClearCanvas
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.CreatingNewThread
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.NextLayer
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.PaintLineTo
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.PaintMoveTo
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.SaveBitmap
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryCustomSurfaceView
import dagger.Module
import dagger.Provides

@Module
class ModuleCanvasRepositoryImplCustomSurfaceView {
    @Provides
    fun provideRepositoryImpl(
        saveBitmap: SaveBitmap,
        backLayer: BackLayer,
        nextLayer: NextLayer,
        pathLineTo: PaintLineTo,
        pathMoveTo: PaintMoveTo,
        creatingNewThread: CreatingNewThread,
        clearCanvas: ClearCanvas,
    ): CanvasRepositoryCustomSurfaceView {
        return CanvasRepositoryImplCustomSurfaceView(
            saveBitmap,
            backLayer,
            nextLayer,
            pathLineTo,
            pathMoveTo,
            creatingNewThread,
            clearCanvas
        )
    }
}