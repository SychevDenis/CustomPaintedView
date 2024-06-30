package com.example.canvas_for_drawing.presentation

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.domain.use_cases.color_use_cases.AddColorInBarUseCases
import com.example.canvas_for_drawing.domain.use_cases.color_use_cases.RemoveColorInBarUseCases
import com.example.canvas_for_drawing.presentation.fragments.FragmentButtonGroup
import com.example.canvas_for_drawing.presentation.fragments.FragmentCustomSurfaceView
import javax.inject.Inject

class ViewModelMainActivity @Inject constructor(
    private val addColorInBarUseCases: AddColorInBarUseCases,
    private val removeColorInBarUseCases: RemoveColorInBarUseCases
) : ViewModel() {
    var listViewColor = MutableLiveData(mutableListOf<Int>())
    var visible = MutableLiveData(false)

    init {
        //добавляем все нужные цвета в палитру
        addColor(Color.RED)
        addColor(Color.GREEN)
        addColor(Color.BLUE)
        addColor(Color.LTGRAY)
        addColor(Color.MAGENTA)
        addColor(Color.YELLOW)
        addColor(Color.BLACK)
       // addColor(Color.WHITE)
    }
    fun addColor(color: Int): Boolean {
        val oldList = getListViewColor()
        val newList = addColorInBarUseCases.addColor(color, oldList, maxLength)
        setListViewColor(newList)
        return oldList != newList //если цвет добавлен, возвращаем true, иначе false
    }

    fun removeColor(): Boolean {
        val oldList = getListViewColor()
        val newList = removeColorInBarUseCases.removeColor(oldList)
        setListViewColor(newList)
        return oldList != newList //если цвет удален, возвращаем true, иначе false
    }

    private fun getVisible(): Boolean {
        return visible.value ?: return false
    }

    private fun setVisible(newVisible: Boolean) {
        visible.value = newVisible
    }

    fun reversVisible(): Boolean {
        val revers = !getVisible()
        setVisible(revers)
        return revers
    }

    private fun getListViewColor(): MutableList<Int> {
        return listViewColor.value?.toMutableList() ?: return mutableListOf()
    }

    private fun setListViewColor(list: MutableList<Int>) {
        listViewColor.value = list
    }
    fun createColorCircleDrawables(color: Int): LayerDrawable {//функция отвечает
        // за цвет иконкипалитры

        // Создаем белый круг обводку
        val whiteDrawable = ShapeDrawable(OvalShape()).apply {
            this.paint.color = Color.WHITE
            this.intrinsicWidth = 75
            this.intrinsicHeight = 75
        }

        // Создаем внутренний круг
        val colorDrawable = ShapeDrawable(OvalShape()).apply {
            this.paint.color = color
        }
        // blackDrawable.draw(Canvas())

        // Объединяем два круга
        val layerDrawable =
            LayerDrawable(arrayOf(whiteDrawable, colorDrawable)).apply {
            this.setLayerInset(1, 8, 8, 8, 8) // Отступ для черного круга
        }
        return layerDrawable
    }

    fun settingNavigationBar(activity: Activity) {
        //прячем navigation bar
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN
    }
     fun settingToolBar(activity: AppCompatActivity) {
        //задаем цвет и текст для ToolBar
        activity.supportActionBar?.setBackgroundDrawable(
            ColorDrawable(Color.parseColor("#FF0000"))
        )
        activity.supportActionBar?.title = "Paint" //наименование приложения в левом верхнем углу
    }
    fun initFragments(activity: AppCompatActivity,savedInstanceState: Bundle?) {
        if (savedInstanceState == null) { //если активность создана впервые, то делаем транзакцию
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_customSv_containerView, FragmentCustomSurfaceView())
                .replace(R.id.fragment_button_group, FragmentButtonGroup())
                .commit()
        }
    }
    companion object {
        const val maxLength = 12 //максимальный размер палитры
    }
}
