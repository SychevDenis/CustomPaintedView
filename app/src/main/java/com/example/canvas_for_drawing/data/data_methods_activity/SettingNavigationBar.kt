package com.example.canvas_for_drawing.data.data_methods_activity
import android.app.Activity
import android.view.View
import javax.inject.Inject

class SettingNavigationBar @Inject constructor() {
    fun setting(activity: Any): Boolean { //спрятать NavigationBar
        activity as Activity
        activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN) ?: run {
            return false
        }
        return true
    }
}