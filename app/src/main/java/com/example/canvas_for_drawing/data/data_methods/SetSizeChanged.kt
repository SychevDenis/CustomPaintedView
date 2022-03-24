package com.example.canvas_for_drawing.data.data_methods

import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import javax.inject.Inject

class SetSizeChanged @Inject constructor() {
    fun setSizeChanged(onSizeChanged: OnSizeChanged): OnSizeChanged{
        return onSizeChanged
    }
}