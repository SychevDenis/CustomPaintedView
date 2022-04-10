package com.example.canvas_for_drawing.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.models.DrawingObject
import kotlinx.coroutines.*

class CustomSurfaceView @JvmOverloads constructor( //jvm помогает выбрать тот конструктор, который нужен
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {


    private val modelDrawingObject = DrawingObject(0F, 0F, 0)//модель для слежения нажатий по вью
    var modelDrawingObjectLD=MutableLiveData<DrawingObject>()//lifeData для отслеживания нажатий
    lateinit var canvas: Canvas
    var colorCanvas = Color.WHITE
    var path=Path()
    val paint: Paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        holder.addCallback(this)

        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 3f
            color = Color.BLACK
            isAntiAlias = true
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        scope.launch {
            while (true) {
                canvas = holder.lockCanvas()
                canvas.drawColor(colorCanvas)//заливка цвета фона
                canvas.drawPath(path,paint)
                holder.unlockCanvasAndPost(canvas)
                delay(24)
            }
        }.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        scope.cancel()
        holder.removeCallback(this)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                modelDrawingObject.eventY = event.y
                modelDrawingObject.eventX = event.x
                modelDrawingObject.eventAction = event.action
                modelDrawingObjectLD.value=modelDrawingObject
            }
            MotionEvent.ACTION_MOVE -> {
                modelDrawingObject.eventY = event.y
                modelDrawingObject.eventX = event.x
                modelDrawingObject.eventAction = event.action
                modelDrawingObjectLD.value=modelDrawingObject
            }
            MotionEvent.ACTION_UP -> {
                modelDrawingObject.eventY = event.y
                modelDrawingObject.eventX = event.x
                modelDrawingObject.eventAction = event.action
                modelDrawingObjectLD.value=modelDrawingObject
            }
        }
        return true
    }
}
