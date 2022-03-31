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
import java.lang.Exception

class CustomSurfaceView @JvmOverloads constructor( //jvm помогает выбрать тот конструктор, который нужен
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {


    private lateinit var canvas: Canvas //полотно рисования
    private var colorCanvas = Color.WHITE
    private val paint: Paint = Paint(Paint.SUBPIXEL_TEXT_FLAG)
    private val scope = CoroutineScope(Dispatchers.Default)
    var clean: Boolean = false //переменная отчистки экрана
    var back: Boolean = false //переменная назад
    var next: Boolean = false //переменная вперед
    var paths = mutableListOf<Path>() //слои нарисованных объектов
    var pathsLayer = 0 //переменная активного слоя
    var shou = false //переменная обновления картинки

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
                if (clean) { //если нажата кнопка отчистки экрана
                    while (paths.size > 0) {
                        paths.removeLast()
                        pathsLayer = 0
                        shou = true
                    }
                    clean = false
                }
                if (back) { //если нажата кнопка отчистки назад
                    if (pathsLayer > 0) {
                        pathsLayer--
                        shou = true
                    }
                    back = false
                }
                if (next) { //если нажата кнопка отчистки вперед
                    if (pathsLayer < paths.size) {
                        pathsLayer++
                        shou = true
                    }
                    next = false
                }
                if (shou) {
                    canvas = holder.lockCanvas()
                    canvas.drawColor(Color.WHITE)
                    if (paths.isNotEmpty()) { //если массив не пустой
                        try { //иногда выдает ошибку, пока не разобрался почему, по этому сделал конструкцию try catch
                            var i = 1
                            while (i <= pathsLayer) {
                                canvas.drawPath(paths[i - 1], paint) //рисуем массив
                                i++
                            }
                            Log.i("log", pathsLayer.toString())
                            shou = false
                        } catch (e: Exception) {
                        }
                    }
                    holder.unlockCanvasAndPost(canvas)
                    delay(24)// пауза для частоты кадров 48/1сек
                }
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
            MotionEvent.ACTION_DOWN -> { //при нажатии клавиши
                if (pathsLayer < paths.size) {
                    while (pathsLayer < paths.size) {
                        paths.removeLast()
                    }
                }
                shou = true
                val path = Path() //создаем новый объект path
                paths.add(path) //добавляем его в массив
                paths.last().moveTo(event.x, event.y)  //рисуем начальную точку
                pathsLayer++
            }
            MotionEvent.ACTION_MOVE -> {
                paths.last().lineTo(event.x, event.y)
                shou = true
            }
            //рисуем объект
            MotionEvent.ACTION_UP -> {
            }//добавляем в массив при отпускании
        }
        return true
    }
}
