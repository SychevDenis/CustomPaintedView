package com.example.canvas_for_drawing.data.data_methods_activity

import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import javax.inject.Inject

class CreateColorCircleDrawables @Inject constructor() {
    fun create(color: Int): LayerDrawable {
        // за цвет иконки палитры

        // Создаем белый круг обводку
        val whiteDrawable = ShapeDrawable(OvalShape()).apply {
            this.paint.color = Color.WHITE
            this.intrinsicWidth = 75
            this.intrinsicHeight = 75
        }

        // Создаем внутренний круг
        val colorDrawable = ShapeDrawable(OvalShape()).apply {
            this.paint.color = color
        }

        // Объединяем два круга
        val layerDrawable =
            LayerDrawable(arrayOf(whiteDrawable, colorDrawable)).apply {
                this.setLayerInset(1, 8, 8, 8, 8) // Отступ для черного круга
            }
        return layerDrawable
    }
}