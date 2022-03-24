package com.example.canvas_for_drawing.domain.models

data class DrawingObject(
    var eventX: Float = 0f, //местоположение по х координате
    var eventY: Float = 0f, //местоположение по у координате
    var eventAction: Int = 0, //выполненое действие
    var strokeWidth: Float = 10f, //ширина линии
    var color: Int = COLOR_BLACK, //цвет кисти
)
{

    companion object {
        const val ACTION_DOWN = 0 //нажатие на тач
        const val ACTION_MOVE = 2 //движение по тачу
        const val ACTION_UP = 1 //отпустить тач
        const val COLOR_BLACK = -16777216 //константа черного цвета
    }
}
