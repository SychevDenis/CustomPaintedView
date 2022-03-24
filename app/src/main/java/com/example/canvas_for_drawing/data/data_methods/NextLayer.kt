package com.example.canvas_for_drawing.data.data_methods

import javax.inject.Inject

class NextLayer @Inject constructor() {
    fun nextLayer(activeLayer: Int, listSize:Int):Int {
        return if (activeLayer < listSize) {
            activeLayer.plus(1)
        } else
            listSize
    }
}
