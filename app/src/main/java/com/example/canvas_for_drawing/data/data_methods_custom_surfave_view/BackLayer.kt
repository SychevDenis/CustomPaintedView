package com.example.canvas_for_drawing.data.data_methods_custom_surfave_view

import javax.inject.Inject

class BackLayer @Inject constructor() {
    fun backLayer(activeLayer: Int):Int {
        return if (activeLayer > 0) {
            activeLayer.minus(1)
        } else
            0
    }
}