package com.example.canvas_for_drawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class MyCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    View(context) {
    private val path: Path by lazy { Path() }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.apply {
            style = Paint.Style.FILL
            strokeWidth = 10f
            color = Color.RED
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(event.x, event.y)
            MotionEvent.ACTION_MOVE -> path.lineTo(event.x, event.y)
        }
        invalidate()
        return true
    }
}
