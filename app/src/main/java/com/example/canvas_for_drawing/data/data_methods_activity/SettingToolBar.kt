package com.example.canvas_for_drawing.data.data_methods_activity
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class SettingToolBar @Inject constructor() {
    fun setting(activity: AppCompatActivity, title: String, color: Int): Boolean {
        activity.supportActionBar?.title = title ?: run {
            return false
        }
        activity.supportActionBar?.setBackgroundDrawable(ColorDrawable(color)) ?: run {
            return false
        }
        return true
    }
}