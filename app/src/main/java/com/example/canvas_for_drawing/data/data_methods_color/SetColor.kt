package com.example.canvas_for_drawing.data.data_methods_color

import javax.inject.Inject

class SetColor @Inject constructor() {
    fun setColor(color: Int): Int {
        return if (validColor())
            color
        else 0
    }

    private fun validColor(): Boolean { //в будущем нужно реализовать валидацию цветов
       //..........//
        return true
    }
}