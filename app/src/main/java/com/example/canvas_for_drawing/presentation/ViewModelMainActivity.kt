package com.example.canvas_for_drawing.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.domain.use_case.color_use_cases.AddColorInBarUseCases
import com.example.canvas_for_drawing.domain.use_case.color_use_cases.RemoveColorInBarUseCases
import javax.inject.Inject

class ViewModelMainActivity @Inject constructor(
    private val addColorInBarUseCases: AddColorInBarUseCases,
    private val removeColorInBarUseCases: RemoveColorInBarUseCases
) : ViewModel() {
    var listViewColor = MutableLiveData(mutableListOf<Int>())
    var visible = MutableLiveData(true)

    fun addColor(color: Int): Boolean {
        val oldList = getListViewColor()
        val newList = addColorInBarUseCases.addColor(color, oldList, maxLength)
        setListViewColor(newList)
        return oldList != newList
    }

    fun removeColor(): Boolean {
        val oldList = getListViewColor()
        val newList = removeColorInBarUseCases.removeColor(oldList)
        setListViewColor(newList)
        return oldList != newList
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

    companion object {
        const val maxLength = 12 //размер палитры
    }
}
