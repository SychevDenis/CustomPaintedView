package com.example.canvas_for_drawing.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.di.DaggerComponentActivity
import com.example.canvas_for_drawing.presentation.fragments.FragmentButtonGroup
import com.example.canvas_for_drawing.presentation.fragments.FragmentCustomSurfaceView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    private lateinit var fragmentCustomSurfaceView: FragmentCustomSurfaceView

    private lateinit var gridLayout: GridLayout
    //private lateinit var fragmentButtonGroup: FragmentButtonGroup

    private lateinit var inAnimator: Animation
    private lateinit var outAnimator: Animation

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) { //если активность создана впервые, то делаем транзакцию
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_customSv_containerView, FragmentCustomSurfaceView())
                .replace(R.id.fragment_button_group, FragmentButtonGroup())
                .commit()
        }
        inAnimator = AnimationUtils.loadAnimation(this, R.anim.alpha_in)
        outAnimator = AnimationUtils.loadAnimation(this, R.anim.alpha_out)
        gridLayout = findViewById(R.id.gridView)
        initViewModels()//инициализируем вью модели

        outAnimator.setAnimationListener(this)
        inAnimator.setAnimationListener(this)

        viewModelObserve()//подписываемся на обновления
    }

    private fun viewModelObserve() {
        viewModelMainActivity.listViewColor.observe(this) {
            gridLayout.removeAllViews()
            for (color in it) {
                val frameLayout = LayoutInflater.from(this).inflate(R.layout.item_color_rv, null)
                val viewColor: View = frameLayout.findViewById(R.id.viewColor)
                viewColor.setOnClickListener { view -> //установка слушателя на все цвета палитры
                    viewModelCSV.setColorStroke(color)
                }
                viewColor.background = ColorDrawable(color)
                gridLayout.addView(frameLayout)
            }
        }
        viewModelMainActivity.visible.observe(this) {
            if (it) gridLayout.startAnimation(inAnimator)
            else gridLayout.startAnimation(outAnimator)
            gridLayout.isVisible = it
        }
    }

    private fun initViewModels() {
        //сюда вносятся вьюмодели, требующие инициализации
        viewModelCSV
        viewModelMainActivity
    }


    override fun onResume() {
        super.onResume()
        fragmentCustomSurfaceView = supportFragmentManager
            .findFragmentById(R.id.fragment_customSv_containerView) as FragmentCustomSurfaceView
        //        fragmentButtonGroup = supportFragmentManager

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //загружаем меню
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //кнопки меню
        when (item.itemId) {
            R.id.menuActivityButtonClean -> {
                viewModelCSV.clearCanvas()
            }

            R.id.menuActivityButtonPalette -> {
                viewModelMainActivity.reversVisible()
            }

            R.id.menuActivityButtonSaveImage -> {
                save()//сохранить канвас в jpg
            }
        }
        return true
    }


    private fun save() {
        if (getPermissionWriteReadExternalExternalStorage()) {
            if (viewModelCSV.saveCanvas())
                Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Ошибка сохранения", Toast.LENGTH_SHORT).show()
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

    override fun onAnimationStart(animation: Animation?) {
        println("FragmentBG start animation ${animation.toString()}")
    }

    override fun onAnimationEnd(animation: Animation?) {
        println("FragmentBG end animation ${animation.toString()}")
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

}

