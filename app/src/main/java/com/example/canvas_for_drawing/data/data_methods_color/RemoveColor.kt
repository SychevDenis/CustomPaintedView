package com.example.canvas_for_drawing.data.data_methods_color
import javax.inject.Inject

class RemoveColor @Inject constructor() {
    fun removeColor(list: MutableSet<Int>): MutableSet<Int> {
        if (list.size > 0 ) {
            list.remove(list.last())
        }
        return list
    }
}