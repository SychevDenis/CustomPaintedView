package com.example.canvas_for_drawing.data.data_methods_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.presentation.fragments.FragmentButtonGroup
import com.example.canvas_for_drawing.presentation.fragments.FragmentCustomSurfaceView
import javax.inject.Inject

class InitFragments @Inject constructor() {
    fun init(activity: Any, savedInstanceState: Any?): Boolean {
        activity as AppCompatActivity
        savedInstanceState as Bundle?
        return if (savedInstanceState == null) { //если активность создана впервые, то делаем транзакцию
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_customSv_containerView, FragmentCustomSurfaceView())
                .replace(R.id.fragment_button_group, FragmentButtonGroup())
                .commit()
            true
        } else false
    }
}