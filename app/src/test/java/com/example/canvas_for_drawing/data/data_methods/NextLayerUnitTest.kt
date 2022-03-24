package com.example.canvas_for_drawing.data.data_methods
import com.google.common.truth.Truth.assertThat
import org.junit.Test
class NextLayerUnitTest {
    @Test
    fun `enter activeLayer 3, listSize 2, returns 2`() {
        val result = NextLayer().nextLayer(3,2)
        assertThat(result).isEqualTo(2)
    }
    @Test
    fun `enter activeLayer 3, listSize 3, returns 3`() {
        val result = NextLayer().nextLayer(3,3)
        assertThat(result).isEqualTo(3)
    }
    @Test
    fun `enter activeLayer 2, listSize 3, returns 3`() {
        val result = NextLayer().nextLayer(2,3)
        assertThat(result).isEqualTo(3)
    }
}
