package com.example.canvas_for_drawing.data.repositors

import com.example.canvas_for_drawing.data.data_methods_color.AddColor
import com.example.canvas_for_drawing.data.data_methods_color.RemoveColor
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryColor

class CanvasRepositoryImplColor(
    private val addColor: AddColor,
    private val removeColor: RemoveColor
) : CanvasRepositoryColor {

    override fun addColorInBar(
        color: Int,
        list: MutableList<Int>,
        maxLength: Int
    ): MutableList<Int> {
        return addColor.addColor(color, list, maxLength)
    }

    override fun removeColorInBar(list: MutableList<Int>): MutableList<Int> {
        return removeColor.removeColor(list)
    }
}



