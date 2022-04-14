package com.example.canvas_for_drawing.domain.models

data class DrawingObject(
    var eventX: Float,
    var eventY: Float,
    var eventAction: Int,
    var infoLayer:InfoLayerCanvas
)
{
    companion object {
        val ACTION_DOWN = 0
        val ACTION_MOVE = 2
        val ACTION_UP = 1
    }
}
