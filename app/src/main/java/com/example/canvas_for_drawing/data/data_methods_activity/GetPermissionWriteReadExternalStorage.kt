package com.example.canvas_for_drawing.data.data_methods_activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import javax.inject.Inject

class GetPermissionWriteReadExternalStorage @Inject constructor() {
    fun get(context: Activity): Boolean { //получение разрешений на сохранение
        val permission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permissionsStorage = arrayOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return if (permission != PackageManager.PERMISSION_GRANTED) {
            // вызывается если разрешение не было дано
            ActivityCompat.requestPermissions(
                context, permissionsStorage, 1
            ) //запрос на разрешение записи
            false
        } else {
            true
        }
    }

}