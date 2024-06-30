package com.example.canvas_for_drawing.presentation

import android.graphics.Color

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.domain.models.Pair
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.domain.use_cases.color_use_cases.SetColorStrokeUseCases
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.BackLayerUseCase
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.ClearCanvasUseCase
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.CreatingNewThreadUseCases
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.NextLayerUseCase
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.PaintUseCase
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.SaveCanvasUseCase
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.SetColorBackgroundUseCase
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.SetProgressSeekBarUseCase
import com.example.canvas_for_drawing.domain.use_cases.custom_surface_view.SetSizeChanged

import javax.inject.Inject

class ViewModelCSV @Inject constructor(
    private val setColorBackgroundUseCase: SetColorBackgroundUseCase,
    private val backLayersUseCase: BackLayerUseCase,
    private val setProgressSeekBarUseCase: SetProgressSeekBarUseCase,
    private val clickNextUseCase: NextLayerUseCase,
    private val clearCanvasUseCase: ClearCanvasUseCase,
    private val setSizeChangedUseCase: SetSizeChanged,
    private val paintUseCase: PaintUseCase,
    private val saveCanvas: SaveCanvasUseCase,

    private val creatingNewThreadUseCases: CreatingNewThreadUseCases,
    private val setColorStrokeUseCases: SetColorStrokeUseCases,


) : ViewModel() {
    val colorBackgroundCanvasLD = MutableLiveData(Color.WHITE) //стандартный цвет фона
    val pairLD = MutableLiveData(Pair())
    val activeLayerLD = MutableLiveData(FIRST_LAYER)//активный слой
    var vmStrokeWidth = MutableLiveData(10f)//ширина кисти
    var visibleLinearLayoutWidthBrush = MutableLiveData(false)//ширина кисти
    private var lastAction=NONE //последнее выполненное действие

    private var onSizeChanged = OnSizeChanged()//размеры customSurfaceView

    private var colorStroke = Color.BLACK


    private fun getActiveLayer(): Int {
        activeLayerLD.value?.let { return it } ?: return 0
    }


    fun setColorStroke(color: Int) {
        colorStroke = setColorStrokeUseCases.setColorStroke(color)
    }


    private fun setActiveLayer(it: Int) {
        activeLayerLD.value = it
    }

    fun setColorBack(color: Int) { //установить значение заднего фона
        colorBackgroundCanvasLD.value = setColorBackgroundUseCase.setColorBack(color)
    }

    private fun getColorBack(): Int { //установить значение заднего фона
        colorBackgroundCanvasLD.value?.let { return it } ?: return Color.WHITE
    }

    fun backLayers() {//назад по слоям
        lastAction = BACK_LAYER
        val newLayer = backLayersUseCase.backLayer(getActiveLayer())
        setActiveLayer(newLayer)
    }

    fun nextLayers() {//вперед по слоям
        val newLayer = clickNextUseCase.nextLayer(getActiveLayer(), getListSize())
        setActiveLayer(newLayer)
    }

    fun clearCanvas() {//очистить холст
        //если предыдущее действие было не отчистка экрана
        if (lastAction!=CLEAN_CANVAS) {
            lastAction = CLEAN_CANVAS
            val pair = getPairValue()
            val backColor = getColorBack()
            creatingNewThread() //начинаем ветку с активного слоя
            val cleanerPair =
                clearCanvasUseCase.clearCanvas(pair, onSizeChanged, backColor) as Pair?
            setPairValue(cleanerPair)
            nextLayers()
        }
    }

    fun setSizeChanged(onSizeChanged: OnSizeChanged) {
        //установка высоты и ширины (для очистки экрана)
        this.onSizeChanged = setSizeChangedUseCase.setSizeChanged(onSizeChanged)
    }

    fun saveCanvas(): Boolean {
        pairLD.value?.let { pair ->
                activeLayerLD.value?.let { activeLayer ->
                    return saveCanvas.saveCanvas(onSizeChanged,pair,activeLayer)
                }
            }
        return false
    }

    fun setProgressSeekBar(progress: Int) { //установка ширины кисти
        vmStrokeWidth.value=
            setProgressSeekBarUseCase.setProgressSeekBar(progress).toFloat() ?: 10f
    }

    fun paint(drawingObject: DrawingObject) {//рисуем объект
        lastAction = PAINT
        //добавляем данные о ширине и цвете кисти
        drawingObject.let {
            it.strokeWidth = vmStrokeWidth.value?: 10f
            it.color = colorStroke
        }

        if (drawingObject.eventAction == ACTION_DOWN) { //если тач был нажат
            paintMoveTo(drawingObject)
        } else {
            paintLineTo(drawingObject)
        }
    }

    private fun paintLineTo(drawingObject: DrawingObject) { //продолжаем рисование
        drawingObject.let {
            val pair = getPairValue()
            paintUseCase.paintLineTo(drawingObject, pair)
        }
    }

    private fun paintMoveTo(drawingObject: DrawingObject) {
        //начала нового объекта рисования
        creatingNewThread() //начинаем ветку с активного слоя
        drawingObject.let {
            val pair = getPairValue()
            paintUseCase.paintMoveTo(drawingObject, pair)
        }
        nextLayers()//создаем новый слой
    }

    private fun getPairValue( //читаем PairList
    ): Pair {
        pairLD.value?.let { pairLD.value = it; return it } ?: return Pair()
    }


    private fun setPairValue(pair: Pair?) //читаем PairList
    {
        if (pair != null) {
            pairLD.value = pair
        }
    }
    private fun creatingNewThread() { //создание новой ветки истории рисования
        creatingNewThreadUseCases.create(getPairValue(), getActiveLayer())
    }

    private fun getListSize(): Int {//возвращаем количество слоев
        return getPairValue().getListSize() //вернуть количество слоев
    }

    companion object {
        private const val FIRST_LAYER = 0//номер начального слоя рисования

        //список экшенов с тачем
        private const val ACTION_DOWN = 0
        private const val ACTION_UP = 1
        private const val ACTION_MOVE = 2

        //список последний действий с изображением

        private const val NONE=0  //ничего
        private const val PAINT=1 //было выполнено рисование
        private const val CLEAN_CANVAS=2 //был очищен экран
        private const val BACK_LAYER=3 //шаг назад по слоям
    }
}