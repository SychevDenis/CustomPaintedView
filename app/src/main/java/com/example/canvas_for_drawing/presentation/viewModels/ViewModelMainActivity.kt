package com.example.canvas_for_drawing.presentation.viewModels

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.domain.use_cases.activity.CreateColorCircleDrawablesUseCase
import com.example.canvas_for_drawing.domain.use_cases.activity.GetDateTimeUseCase
import com.example.canvas_for_drawing.domain.use_cases.activity.GetPermissionWriteReadExternalStorageUseCase
import com.example.canvas_for_drawing.domain.use_cases.activity.InitFragmentsUseCase
import com.example.canvas_for_drawing.domain.use_cases.activity.PaintIconButtonWidthBrushUseCase
import com.example.canvas_for_drawing.domain.use_cases.activity.UpdateBackgroundColorAppUseCase
import com.example.canvas_for_drawing.domain.use_cases.activity.SettingNavigationBarUseCase
import com.example.canvas_for_drawing.domain.use_cases.activity.SettingToolBarUseCase
import com.example.canvas_for_drawing.domain.use_cases.color.AddColorInPaletteUseCase
import javax.inject.Inject


class ViewModelMainActivity @Inject constructor(
    private val addColorInPaletteUseCases: AddColorInPaletteUseCase,
    private val getDateTimeUseCase: GetDateTimeUseCase,
    private val createColorCircleDrawablesUseCase: CreateColorCircleDrawablesUseCase,
    private val settingToolBarUseCase: SettingToolBarUseCase,
    private val settingNavigationBarUseCase: SettingNavigationBarUseCase,
    private val initFragmentsUseCase: InitFragmentsUseCase,
    private val getPermissionWriteReadUseCase: GetPermissionWriteReadExternalStorageUseCase,
    private val updateBackgroundColorAppUseCase: UpdateBackgroundColorAppUseCase,
    private val paintIconButtonWidthBrushUseCase: PaintIconButtonWidthBrushUseCase
) : ViewModel() {
    var vmListViewColor = MutableLiveData(mutableListOf<Int>())//лист цветов палитры
    var vmVisiblePalette = MutableLiveData(false)//видимость палитры
    var vmVisibleViewWidthBrush = MutableLiveData(false)//видимость кисти
    var vmStrokeWidth = MutableLiveData(10f)//стандартная ширина кисти
    private var colorBack = Color.BLACK //стандартный цвет заднего фона

    init {
        //добавляем все нужные цвета в палитру
        addColor(Color.RED)
        addColor(Color.GREEN)
        addColor(Color.BLUE)
        addColor(Color.LTGRAY)
        addColor(Color.MAGENTA)
        addColor(Color.YELLOW)
        addColor(Color.BLACK)
        //addColor(Color.WHITE)
    }

    fun paintIconButtonWidthBrush(width: Float,buttonWidthBrush:ImageButton):Boolean{
        return paintIconButtonWidthBrushUseCase.paint(width,buttonWidthBrush)
    }
    fun setBackColor(color: Int) { //установка в переменную background приложения
        colorBack = color
    }

    fun updateBackgroundColorApp( //обновить background элементов
        frameLayout: FrameLayout,
        buttonBack: ImageButton, buttonNext: ImageButton, buttonWidthBrush: ImageButton
    ) {
        val color = getColorBackStr()
        updateBackgroundColorAppUseCase.update(
            color, frameLayout, buttonBack, buttonNext, buttonWidthBrush
        )
    }

    fun setProgressSeekBar(progress: Int) { //установка ширины кисти из view настройки ширины кисти
        vmStrokeWidth.value = progress.toFloat() ?: 10f
    }

    fun addColor(color: Int): Boolean {//добавить цвет в палитру
        val oldList = getListViewColor()
        val newList = addColorInPaletteUseCases.addColor(color, oldList, MAX_LENGTH)
        setListViewColor(newList)
        return oldList != newList //если цвет добавлен, возвращаем true, иначе false
    }

//    fun removeColor(): Boolean { //удалить цвет из палитры (функция в разработке)
//        val oldList = getListViewColor()
//        val newList = removeColorInBarUseCases.removeColor(oldList)
//        setListViewColor(newList)
//        return oldList != newList //если цвет удален, возвращаем true, иначе false
//    }
    private fun getVisibilityPalette(): Boolean {//читаем видимость палитры
        return vmVisiblePalette.value ?: return false
    }

    private fun getVisibilityViewWidthBrush(): Boolean {//читаем видимость
        // панели ширины кисти
        return vmVisibleViewWidthBrush.value ?: return false
    }

    private fun setVisiblePalette(newVisible: Boolean): Boolean {//устанавливаем видимость палитры
        vmVisiblePalette.value = newVisible
        return vmVisiblePalette.value ?: return false
    }

    private fun setVisibleViewWidthBrush(newVisible: Boolean): Boolean {//устанавливаем видимость
        // панели ширины кисти
        vmVisibleViewWidthBrush.value = newVisible
        return vmVisibleViewWidthBrush.value ?: return false
    }

    fun visibilityInversionViewWidthBrush(): Boolean { //инверсия видимости
        // панели ширины кисти
        return setVisibleViewWidthBrush(!getVisibilityViewWidthBrush())
    }

    fun visibilityInvisibleVisiblePalette(): Boolean {//инверсия видимости палитры
        return setVisiblePalette(!getVisibilityPalette())
    }

    fun getDateTime(): String {
        return getDateTimeUseCase.getDateTime(FORMAT_FILE_PICTURE)
    }


    private fun getListViewColor(): MutableList<Int> {
        return vmListViewColor.value?.toMutableList() ?: return mutableListOf()
    }

    private fun setListViewColor(list: MutableList<Int>) {
        vmListViewColor.value = list
    }

    fun createColorCircleDrawables(color: Int): LayerDrawable {//функция
        //цвета иконки палитры
        return createColorCircleDrawablesUseCase.create(color) as LayerDrawable
    }

    fun settingNavigationBar(activity: Activity) {
        //прячем navigation bar
        settingNavigationBarUseCase.setting(activity)
    }

    fun settingToolBar(activity: AppCompatActivity, title: String) {
        //задаем цвет и текст для ToolBar
        settingToolBarUseCase.setting(activity, title, getColorBackInt())
    }

    fun initFragments(activity: AppCompatActivity, savedInstanceState: Bundle?) {
        initFragmentsUseCase.init(activity, savedInstanceState)
    }

    fun getPermissionWriteReadExternalStorage(context: Activity): Boolean {
        //получение разрешений на сохранение
        return getPermissionWriteReadUseCase.get(context)
    }

    fun getColorBackStr(): String {
        return String.format("#%08X", colorBack)//форматируем полученный цвет в hex код типа string
    }

    private fun getColorBackInt(): Int {
        return colorBack
    }

    companion object {
        const val MAX_LENGTH = 12 //максимальный размер палитры
        const val FORMAT_FILE_PICTURE = ".jpg" //формат файла картинки для сохранения
    }
}
