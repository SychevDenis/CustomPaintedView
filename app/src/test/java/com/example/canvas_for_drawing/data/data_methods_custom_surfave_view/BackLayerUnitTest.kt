package com.example.canvas_for_drawing.data.data_methods_custom_surfave_view
import com.google.common.truth.Truth.assertThat
import org.junit.Test
class BackLayerUnitTest {
    @Test
    fun `enter 3, returns 2`() {
        val result = BackLayer().backLayer(3)
        assertThat(result).isEqualTo(2)
    }
    @Test
    fun `enter 0, returns 0`() {
        val result = BackLayer().backLayer(0)
        assertThat(result).isEqualTo(0)
    }
    @Test
    fun `enter -5, returns 0`() {
        val result = BackLayer().backLayer(-5)
        assertThat(result).isEqualTo(0)
    }
}