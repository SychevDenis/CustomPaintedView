package com.example.canvas_for_drawing.data.data_methods_custom_surfave_view

import android.graphics.Color
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.Pair
import com.google.common.truth.Truth.assertThat
import org.junit.Test
class ClearCanvasTest{
    @Test
    fun `enter_pairDefault_And_onSizeChangedDefault_And_backColor_return_True`(){
        val pair = Pair()
        val onSizeChanged=OnSizeChanged()
        val backColor = Color.BLACK
        val clearCanvas=ClearCanvas().clear(pair,onSizeChanged,backColor)
        assertThat(clearCanvas).isNotNull()
    }
}
