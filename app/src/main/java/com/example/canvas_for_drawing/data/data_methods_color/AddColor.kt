package com.example.canvas_for_drawing.data.data_methods_color
import javax.inject.Inject

class AddColor @Inject constructor() {
    fun addColor(color:Int,list: MutableList<Int>,maxLength:Int): MutableList<Int> {
        if (list.size < maxLength) {
            list.add(color)
        }
        return list
    }
}