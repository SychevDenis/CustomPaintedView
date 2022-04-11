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


    private lateinit var canvas: Canvas //полотно рисования
    var colorCanvas = Color.WHITE
    val paint: Paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)
    private val scope = CoroutineScope(Dispatchers.IO)
    var tool = Tool.BRUSH //выбранный инструмент
    var clean: Boolean = false //переменная отчистки экрана
    var back: Boolean = false //переменная назад
    var next: Boolean = false //переменная вперед
    var shou = false //переменная обновления картинки
    var paths = mutableListOf<Path>() //слои нарисованных объектов
    var pathsLayer = 0 //переменная активного слоя


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
        Log.i("log","1")
        scope.launch {
            canvas = holder.lockCanvas()
            canvas.drawColor(colorCanvas)//заливка цвета фона
            holder.unlockCanvasAndPost(canvas)
            while (true) {//цикл работы отрисовки
                if (shou) {//рисуем экран
                    canvas = holder.lockCanvas()//разблокируем канвас
                    canvas.drawColor(colorCanvas)//производим заливку
                    if (paths.isNotEmpty()) { //если массив не пустой, то заполняем им канвас
                        var i = 1
                        while (i <= pathsLayer) {
                            canvas.drawPath(paths[i - 1], paint) //рисуем массив
                            i++
                        }
                        Log.i("log", pathsLayer.toString())
                        shou = false
                    }
                    holder.unlockCanvasAndPost(canvas) //блокируем канвас
                    delay(24)// пауза для частоты кадров 48/1сек
                }
                if (clean) { //если нажата кнопка отчистки экрана
                    while (paths.size > 0) {
                        paths.removeLast()
                        pathsLayer = 0
                        shou = true
                    }
                    clean = false
                }
                if (back) { //если нажата кнопка назад
                    if (pathsLayer > 0) {
                        pathsLayer--
                        shou = true
                    }
                    back = false
                }
                if (next) { //если нажата кнопка вперед
                    if (pathsLayer < paths.size) {
                        pathsLayer++
                        shou = true
                    }
                    next = false
                }
            }
        }.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        Log.i("log","2")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        scope.cancel()
        holder.removeCallback(this)
        Log.i("log","3")
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> { //при нажатии клавиши
                shou = true
                newBranch()//перезапись массива объектов, в случае отката назад и создания новой ветки
                val path = Path() //создаем новый объект path
                paths.add(path) //добавляем его в массив
                paths.last().moveTo(event.x, event.y)  //рисуем начальную точку.
                pathsLayer++
            }
            MotionEvent.ACTION_MOVE -> {
                shou = true
                paths.last().lineTo(event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }

    fun newBranch() {//перезапись массива объектов, в случае отката назад и создания новой ветки
        if (pathsLayer < paths.size) {
            while (pathsLayer < paths.size) {
                paths.removeLast()
            }
        }
    }
}
