package com.example.canvas_for_drawing.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import kotlinx.coroutines.*


class CustomSurfaceView @JvmOverloads constructor(//jvm помогает выбрать тот конструктор, который нужен
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    var CSVInterface: CustomSurfaceViewInterface? = null
    private var colorBackgroundCanvas = Color.WHITE //default color
    private var activeLayer = 0 //активный слой
    private var drawingPermission = true //разрешение на рисование
    private var pathList = mutableListOf<Path>()
    private var paintList = mutableListOf<Paint>()
    private lateinit var scope:CoroutineScope

    @SuppressLint("SuspiciousIndentation")
    override fun surfaceCreated(p0: SurfaceHolder) {
        scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                try {
                    while (drawingPermission) { //если разрешено рисовать
                        if (p0.surface.isValid) {
                            withContext(Dispatchers.IO) {
                                p0.lockCanvas()?.let {   //метод блокирует объект Canvas
                                    //формирование изображения
                                    it.drawColor(colorBackgroundCanvas)//заливка цвета фона
                                    drawLayers(
                                        pathList,
                                        paintList,
                                        activeLayer,
                                        it
                                    )//послойное рисование
                                    p0.unlockCanvasAndPost(it) //метод разблокирует объект Canvas
                                    delay(24)//такая задержка увеличивает производительность
                                    //примерно на 20%, но не сказывается на работе программы
                                }
                            }
                        }
                    }
                } catch (e: CancellationException) {
                    println("CSV ${e.message}")
                } finally {
                }
            }
        }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        if (visibility==0){
            holder.addCallback(this)
        } else {
            holder.removeCallback(this)
        }
        super.onVisibilityChanged(changedView, visibility)

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        scope.cancel()
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                eventHandler(event)
            }

            MotionEvent.ACTION_MOVE -> {
                eventHandler(event)
            }

            MotionEvent.ACTION_UP -> {
                eventHandler(event)
            }
        }
        return true
    }

    private fun eventHandler(event: MotionEvent) {//обработка события нажатия
        CSVInterface?.touchEvent(event)
            ?: throw Exception("Not implemented interface CustomSurfaceView")

    }

    private fun drawLayers(
        pathList: MutableList<Path>, paintList: MutableList<Paint>,
        activeLayer: Int, canvas: Canvas
    ) { //послойное рисование
        if (pathList.isNotEmpty()) {
            var i = 0
            while (i < activeLayer) {
                canvas.drawPath(pathList[i], paintList[i]) //рисуем массив
                i++
            }
        }
    }

    fun setColorBackgroundCanvas(color: Int) {
        colorBackgroundCanvas = color
    }
    fun setPathAndPaint(pathList: MutableList<Path>,
                        paintList: MutableList<Paint>){
        this.pathList = pathList
        this.paintList = paintList
    }
    fun setPath(pathList: MutableList<Path>) {
        this.pathList = pathList
    }

    fun setPaint(paintList: MutableList<Paint>) {
        this.paintList = paintList
    }

    fun setActiveLayer(activeLayer: Int) {
        this.activeLayer = activeLayer
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        CSVInterface?.setSizeChanged(w, h, oldw, oldh)
            ?: throw Exception("Not implemented interface CustomSurfaceView")
    }

    interface CustomSurfaceViewInterface { //интерфейс сообщает о Touch Action
        fun touchEvent(event: MotionEvent)
        fun setColorBackgroundCanvas(color: Int)
        fun setSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int)

    }
}
