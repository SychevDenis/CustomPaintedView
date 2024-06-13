package com.example.canvas_for_drawing.data.data_methods

import com.example.canvas_for_drawing.data.data_methods_custom_surfave_view.CreatingNewThread
import com.example.canvas_for_drawing.domain.models.Pair
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import javax.inject.Inject

class CreatingNewThreadUnitTest @Inject constructor() {
    @Test
    fun `active layer 10, return true`() {
        val pair=Pair()
        val result = CreatingNewThread().create(pair,10)
        assertThat(result).isTrue()
    }
    @Test
    fun `active layer 0, return true`() {
        val pair=Pair()
        val result = CreatingNewThread().create(pair,0)
        assertThat(result).isTrue()
    }
    @Test
    fun `active layer -10, return true`() {
        val pair=Pair()
        val result = CreatingNewThread().create(pair,-10)
        assertThat(result).isTrue()
    }
}