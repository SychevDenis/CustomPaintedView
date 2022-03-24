package com.example.canvas_for_drawing.domain.models

import java.lang.Exception

/*
 * Все сложные объекты представленны типами "Any" для сохранения принципов чистой
 * архитекруты.
 * Что бы использовать данный класс для чтения данных из методов, при необходимости,
 * требуется явно приводить их к нужным типам.
 * Нужный тип будет указан в комментарии к функции
 * Так же нужно самостоятельно заботиться о том, что бы загруженные данные
 * в методы были нужного типа, так как тут отсутствуют проверки типов данных
 * (тип данных можно понять по названию переменных)
*/
class Pair { //объект для работы с custom view
    private fun setList(
        newListPath: MutableList<Any>, //установить новое значение листам
        newListPaint: MutableList<Any>
    ):Boolean {
        return if (newListPath.size==newListPaint.size) {
            listPath.clear()
            listPaint.clear()
            listPath.addAll(newListPath)
            listPaint.addAll(newListPaint)
            true
        } else false
    }

    fun add(path: Any, paint: Any):Boolean {//добавить объекты в оба листа
        return try {
            listPath.add(path)
            listPaint.add(paint)
            true
        } catch (e:Exception){
            false
        }
    }

    fun getListSize(): Int {//вернуть длину обоих листов
        return if (listPath.size == listPaint.size && listPath.isNotEmpty())
            listPath.size
        else ERROR_VALUE
    }

    fun getListPaint(): MutableList<Any> {//при чтении привести к типу MutableList<Paint>
        return listPaint.toMutableList()
    }

    fun getListPath(): MutableList<Any> {//при чтении привести к типу MutableList<Path>
        return listPath.toMutableList()
    }

    fun editLastElementPaint(paint: Any) {//редактируем последний элемент Paint
        listPaint[listPaint.lastIndex] = paint
    }

    fun editLastElementPath(path: Any) {//редактируем последний элемент Path
        listPath[listPath.lastIndex] = path
    }

    fun getLastElementPaint(): Any {//при чтении привести к типу Paint
        var paint=Any()
        paint=listPaint[listPaint.lastIndex]
        return paint
    }

    fun getLastElementPath(): Any {//при чтении привести к типу Path
        var path=Any()
        path=listPath[listPath.lastIndex]
        return path
    }

    fun removeToActiveLayer(activeLayer: Int) {//удалить слои до активного слоя
        while (getListSize() > activeLayer && activeLayer>=0) {
            removeLast()
        }
    }
    private fun removeLast() {//удалить последние элементы
        listPaint.removeLast()
        listPath.removeLast()
    }
    companion object {
        private val listPath: MutableList<Any> = mutableListOf()
        private val listPaint: MutableList<Any> = mutableListOf()
        private const val ERROR_VALUE = -1 //значение в случае ошибки
    }
}