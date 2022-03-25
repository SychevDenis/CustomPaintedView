package com.example.canvas_for_drawing

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
import kotlinx.coroutines.*

class CustomSurfaceView @JvmOverloads constructor( //jvm помогает выбрать тот конструктор, который нужен
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {


    private lateinit var canvas: Canvas
    private val paint: Paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)
    val scope = CoroutineScope(Dispatchers.Default)
    private var work: Boolean = true
    var clean: Boolean = false //переменная отчистки экрана
    var back: Boolean = false //переменная назад
    var paths = mutableListOf<Path>() // слои

    init {
        holder.addCallback(this)
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 10f
            color = Color.RED
            isAntiAlias = true
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        Log.i("Surface", "1")
        scope.launch {
            canvas = holder.lockCanvas()
            canvas.drawColor(Color.WHITE)
            holder.unlockCanvasAndPost(canvas)

            while (true) {//цикл работы отрисовки
                if (clean) {
                    while (paths.size>0) {
                        paths.removeLast()
                    }
                    clean = false
                }
                if (back) {
                    if (paths.size>0) {
                        paths.removeLast()
                    }
                    back = false
                }
                if (work) {
                    canvas = holder.lockCanvas()
                    canvas.drawColor(Color.WHITE)
                    if (paths.isNotEmpty()) { //если массив не пустой
                        for (path in paths)
                        canvas.drawPath(path, paint) //рисуем последний элемент массива
                    }
                    holder.unlockCanvasAndPost(canvas)
                    delay(24)//для частоты кадров 48/1сек
                }
//                if (work) {
//                    canvas = holder.lockCanvas()
//                    canvas.drawColor(Color.WHITE)
//                    paint.color = Color.BLACK
//                    paint.style = Paint.Style.STROKE
//                    paint.strokeWidth = 10f
//                    canvas.drawPoint(canvasX++, canvasY++, paint)
//                    holder.unlockCanvasAndPost(canvas)
//                    delay(42)//для частоты кадров 24/1сек
//                }
            }
        }.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        Log.i("Surface", "2")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        scope.cancel()
        holder.removeCallback(this)
        Log.i("Surface", "Destroy")
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> { //при нажатии клавиши
                var path = Path() //создаем новый объект path
                if (paths.size>1) {
                }
                paths.add(path) //добавляем его в массив
                paths.last().moveTo(event.x, event.y)  //рисуем начальную точку
            }
            MotionEvent.ACTION_MOVE -> paths.last().lineTo(event.x, event.y) //рисуем объект
            MotionEvent.ACTION_UP -> {
                if (paths.size > 1) {

                }
            }//добавляем в массив при отпускании
        }
        return true
    }
}
