package com.example.canvas_for_drawing.domain.repository_interfaces

interface CanvasRepositoryColor {
    fun addColorInPalette(color:Int, list: MutableList<Int>, maxLength:Int):MutableList<Int>
    fun removeColorInBar(list: MutableList<Int>):MutableList<Int>
    fun setColorStroke(color:Int):Int //нужно будет добавить валидацию цвета
}