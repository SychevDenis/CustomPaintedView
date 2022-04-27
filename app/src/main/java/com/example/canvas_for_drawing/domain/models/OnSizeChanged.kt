package com.example.canvas_for_drawing.domain.models

data class OnSizeChanged(
    val w: Int=0,
    val h: Int=0,
    val oldw: Int=0,
    val oldh: Int=0
)
