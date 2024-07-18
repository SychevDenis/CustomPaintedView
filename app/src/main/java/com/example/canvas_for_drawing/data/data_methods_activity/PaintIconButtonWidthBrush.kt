package com.example.canvas_for_drawing.data.data_methods_activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.widget.ImageButton
import javax.inject.Inject

class PaintIconButtonWidthBrush @Inject constructor() {
    fun paint(
        width: Float, buttonWidthBrush: ImageButton
    ): Boolean {
        val localWidth = 150 // Ширина Bitmap
        val localHeight = 150 // Высота Bitmap
        val bitmap = Bitmap.createBitmap(localWidth, localHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        canvas.drawCircle(
            (localWidth / 2).toFloat(),
            (localHeight / 2).toFloat(),
            (width / 1.5f) + 2f,
            paint
        )
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        canvas.drawCircle((localWidth / 2).toFloat(), (localHeight / 2).toFloat(), 70F, paint)
        buttonWidthBrush.setImageBitmap(bitmap)
        return true
    }
}