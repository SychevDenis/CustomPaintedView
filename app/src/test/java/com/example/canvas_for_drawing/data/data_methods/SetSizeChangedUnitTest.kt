package com.example.canvas_for_drawing.data.data_methods
import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.SetSizeChanged
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.google.common.truth.Truth
import org.junit.Test

class SetSizeChangedUnitTest {
    @Test
    fun `enter onSizeChanged, return onSizeChanged `() {
        val onSizeChanged = OnSizeChanged()
        val result = SetSizeChanged().setSizeChanged(onSizeChanged)
        Truth.assertThat(result).isEqualTo(onSizeChanged)
    }
}