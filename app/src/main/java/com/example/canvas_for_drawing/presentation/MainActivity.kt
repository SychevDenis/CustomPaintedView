package com.example.canvas_for_drawing.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.graphics.applyCanvas
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl
import com.example.canvas_for_drawing.presentation.fragments.FragmentButtonGroup
import com.example.canvas_for_drawing.presentation.fragments.FragmentCustomSurfaceView
import java.io.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_customSv_containerView, FragmentCustomSurfaceView())
                .add(R.id.fragment_button_group, FragmentButtonGroup())
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuActivityButtonClean -> {
                clean()//очистить канвас
            }
            R.id.menuActivityButtonSaveImage -> {
                save()//очистить канвас в jpg

            }
        }
        return true
    }

    fun onClickBack() {
        FragmentCustomSurfaceView.clickBack()
    }

    fun onClickNext() {
        FragmentCustomSurfaceView.clickNext()
    }
    fun clean() {
        FragmentCustomSurfaceView.clickClean()//метод отчистки экрана во врагменте
        Toast.makeText(this, "Экран очищен", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ResourceType")
    private fun save() {
        if (getPermissionWriteReadExternalExternalStorage()) {
            FragmentCustomSurfaceView.clickSave()
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPermissionWriteReadExternalExternalStorage():Boolean { //получение разрешений на сохранение
        val permission = ActivityCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permissionsStorage = arrayOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {// вызывается если разрешение не было дано
            ActivityCompat.requestPermissions(
                this@MainActivity, permissionsStorage, 1
            ) //запрос на разрешение записи
            Log.i("log", "false")
            return false
        } else {
            Log.i("log", "true")
            return true
        }
    }
}

