package com.example.canvas_for_drawing.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.models.DrawingObject
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

class CustomSurfaceView @JvmOverloads constructor( //jvm помогает выбрать тот конструктор, который нужен
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {


    private val modelDrawingObject = DrawingObject(0F, 0F, 0)//модель для слежения нажатий по вью
    var modelDrawingObjectLD = MutableLiveData<DrawingObject>()//lifeData для отслеживания нажатий
    var paths = mutableListOf<Path>()
    private var canvas:Canvas? = Canvas()
    var colorCanvas = Color.WHITE
    val paint: Paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        holder.addCallback(this)

        paint.apply { //default
            style = Paint.Style.STROKE
            strokeWidth = 3f
            color = Color.BLACK
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        scope.launch {
            while (true) {
                canvas=null
                if (holder.surface.isValid) {
                    canvas = holder.lockCanvas()
                    if (canvas!=null) {
                        canvas?.drawColor(colorCanvas)//заливка цвета фона
                        drawLayers(canvas)//послойное рисование
                        holder.unlockCanvasAndPost(canvas)
                        delay(24)
                    }
                }
            }
        }.start()
    }

    private fun drawLayers(canvas: Canvas?) { //послойное рисование
        if (paths.isNotEmpty()) {
            var i = 1
            while (i <= paths.size) {
                canvas?.drawPath(paths[i - 1], paint) //рисуем массив
                i++
            }
        }
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
                modelDrawingObjectLD.value =
                    modelDrawingObject.apply {
                        eventX = event.x
                        eventY = event.y
                        eventAction = event.action
                    }
            }
            MotionEvent.ACTION_MOVE -> {
                modelDrawingObjectLD.value =
                    modelDrawingObject.apply {
                        eventX = event.x
                        eventY = event.y
                        eventAction = event.action
                    }
            }
            MotionEvent.ACTION_UP -> {
                modelDrawingObjectLD.value =
                    modelDrawingObject.apply {
                        eventX = event.x
                        eventY = event.y
                        eventAction = event.action
                    }
            }
        }
        return true
    }

    fun getScreenOrientation(): String {//отслеживание ориентации
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> "portrait"
            Configuration.ORIENTATION_LANDSCAPE -> "landscape"
            else -> ""
        }
    }
}
