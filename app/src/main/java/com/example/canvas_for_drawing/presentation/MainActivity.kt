package com.example.canvas_for_drawing.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.di.DaggerComponentActivity
import com.example.canvas_for_drawing.presentation.fragments.FragmentButtonGroup
import com.example.canvas_for_drawing.presentation.fragments.FragmentCustomSurfaceView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var viewColor: View
    private lateinit var inflater: LayoutInflater
    private lateinit var fragmentCustomSurfaceView: FragmentCustomSurfaceView
    //private lateinit var fragmentButtonGroup: FragmentButtonGroup

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModelCSV by lazy { //получение view model через фабрику
        ViewModelProvider(this, viewModelFactory)[ViewModelCSV::class.java]
    }
    private val viewModelMainActivity by lazy { //получение view model через фабрику
        ViewModelProvider(this, viewModelFactory)[ViewModelMainActivity::class.java]
    }

    init {
        //отправляем объект активити в dagger2
        DaggerComponentActivity.create().inject(this)
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflater = LayoutInflater.from(this)
        gridLayout = findViewById(R.id.gridView)
        //addNewViewColor(Color.RED,inflater,gridLayout)


        if (savedInstanceState == null) { //если активность создана впервые, то делаем транзакцию
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_customSv_containerView, FragmentCustomSurfaceView())
                .replace(R.id.fragment_button_group, FragmentButtonGroup())
                .commit()
        }
        viewModelCSV //инициализируем
        viewModelMainActivity //инициализируем
    }

    private fun addNewViewColor(color: Int,inflater: LayoutInflater,gridLayout:ViewGroup): ViewGroup {
        val frameLayout = inflater.inflate(R.layout.item_color_rv, null)
        val viewColor: View = frameLayout.findViewById(R.id.viewColor)
        val colorDrawable = ColorDrawable(color)
        viewColor.background=colorDrawable
        gridLayout.addView(frameLayout)
        return gridLayout
    }

    override fun onResume() {
        super.onResume()
        fragmentCustomSurfaceView = supportFragmentManager
            .findFragmentById(R.id.fragment_customSv_containerView) as FragmentCustomSurfaceView
//        fragmentButtonGroup = supportFragmentManager
//            .findFragmentById(R.id.fragment_button_group) as FragmentButtonGroup

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //загружаем меню
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //кнопки меню

        when (item.itemId) {
            R.id.menuActivityButtonClean -> {
                viewModelCSV.clearCanvas()
                //       ?: run { throw Exception("fragmentCustomSurfaceView is null") }
            }

            R.id.menuActivityButtonPalette -> {
                viewModelCSV.setColorBack(Color.RED)
//                    ?: run {
//                        throw Exception("fragmentCustomSurfaceView is null")
//                    }
            }

            R.id.menuActivityButtonSaveImage -> {
                //  save()//сохранить канвас в jpg
            }
        }
        return true
    }


    private fun save() {
        if (getPermissionWriteReadExternalExternalStorage()) {
//            if (viewModelCSV.saveCanvas())
//                Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
//            else Toast.makeText(this, "Ошибка сохранения", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getPermissionWriteReadExternalExternalStorage(): Boolean { //получение разрешений на сохранение
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
            return false
        } else {
            return true
        }

    }


}

