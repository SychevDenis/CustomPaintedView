package com.example.canvas_for_drawing.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.lifecycle.MutableLiveData
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.InfoLayerCanvas
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.models.SettingPaintObject
import kotlinx.coroutines.*


class CustomSurfaceView @JvmOverloads constructor( //jvm помогает выбрать тот конструктор, который нужен
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    var modelDrawingObjectLD =
        MutableLiveData<DrawingObject>()//модель для передачи данных о рисовании в репозиторий
    var activeLayer = 0 //активный слой
    var infoLayerCanvas = InfoLayerCanvas(0, activeLayer)//часть модели modelDrawingObjectLD
    var paintObject = SettingPaintObject(1f, Color.BLACK)//часть модели modelDrawingObjectLD
    private val modelDrawingObject = DrawingObject(
        0F, 0F, 0,
        infoLayerCanvas, paintObject
    )
    var paths = mutableListOf<Path>() //слои объектов рисования
    var paints = mutableListOf<Paint>() //слои кистей
    private var canvas: Canvas? = Canvas() //создание холста
    var onSizeChanged = OnSizeChanged()//модель передачи размером вью
    var colorCanvas = Color.WHITE //default color
    val paint: Paint = Paint(Paint.SUBPIXEL_TEXT_FLAG).apply { //default paint
        style = Paint.Style.STROKE
        strokeWidth = 10f
        color = Color.BLACK
    }
    var painting = true//разрешение на рисование

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        scope.launch {
            while (painting) {
                canvas = null
                if (holder.surface.isValid) {
                    canvas = holder.lockCanvas()
                    canvas?.let {
                        it.drawColor(colorCanvas)//заливка цвета фона
                        drawLayers(it)//послойное рисование
                        holder.unlockCanvasAndPost(it)
                    }
                    delay(24)
                }
            }
        }.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        holder.removeCallback(this)
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        scope.cancel()
        holder.removeCallback(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                creatingModelDrawingObjectLD(event)
            }
            MotionEvent.ACTION_MOVE -> {
                creatingModelDrawingObjectLD(event)
            }
            MotionEvent.ACTION_UP -> {
                creatingModelDrawingObjectLD(event)
            }
        }
        return true
    }

    private fun drawLayers(canvas: Canvas) { //послойное рисование
        if (paths.isNotEmpty()) {
            var i = 1
            while (i <= activeLayer) {
                canvas.drawPath(paths[i - 1], paints[i - 1]) //рисуем массив
                i++
            }
        }
    }

    private fun creatingModelDrawingObjectLD(//создание модели координат рисования и слоев
        event: MotionEvent
    ) {
        modelDrawingObjectLD.value =
            modelDrawingObject.apply {
                eventX = event.x
                eventY = event.y
                eventAction = event.action
                infoLayer = infoLayerCanvas()
                settingPaint = paintObject
                Log.i("log", paintObject.strokeWidth.toString())
                Log.i("log", paintObject.color.toString())
            }
    }

    private fun infoLayerCanvas(): InfoLayerCanvas {
        infoLayerCanvas.apply {
            activeLayerCanvas = activeLayer
            layerSizeCanvas = paths.size
        }
        return this.infoLayerCanvas
    }

    fun paint(settingPaintObject: SettingPaintObject): SettingPaintObject {
        paintObject.apply {
            strokeWidth = settingPaintObject.strokeWidth
            color = settingPaintObject.color
        }
        return this.paintObject
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        onSizeChanged = OnSizeChanged(w, h, oldw, oldh)
    }
}
