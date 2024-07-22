package com.example.canvas_for_drawing.domain.repository_interfaces

interface CanvasRepositoryColor {
    fun addColorInPalette(color:Int, list: MutableSet<Int>, maxLength:Int):MutableSet<Int>
    fun removeColorInBar(list: MutableSet<Int>):MutableSet<Int>
    fun setColorStroke(color:Int):Int //нужно будет добавить валидацию цвета
}