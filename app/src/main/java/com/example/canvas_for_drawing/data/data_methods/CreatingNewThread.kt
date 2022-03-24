package com.example.canvas_for_drawing.data.data_methods

import com.example.canvas_for_drawing.domain.models.Pair
import java.lang.Exception
import javax.inject.Inject

class CreatingNewThread @Inject constructor() {
    fun create(pair:Pair,activeLayer:Int):Boolean {
        return try {
            pair.removeToActiveLayer(activeLayer)
            true
        }catch (e:Exception){
            println("CreatingNewThread ${e.message}")
            false
        }
    }
}