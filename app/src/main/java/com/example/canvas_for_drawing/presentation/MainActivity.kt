package com.example.canvas_for_drawing.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
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
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var fragmentCustomSurfaceView: FragmentCustomSurfaceView
    private lateinit var gridLayout: GridLayout //палитра цветов
    private lateinit var inAnimator: Animation
    private lateinit var outAnimator: Animation

    private lateinit var iconPalette: MenuItem

    private lateinit var linearLayoutWidthBrush: LinearLayout
    private lateinit var textViewWidthBrush: TextView
    private lateinit var seekBarWidthBrush: SeekBar

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
        settingToolBar()//установки tool bar
        settingNavigationBar() //установки navigation bar
        initFragments(savedInstanceState)//инициализация фрагментов
        initObjectsAndView()//инициализация объектов и view
        viewModelObserve()//подписываемся на обновления
        setListener()//подключаем слушатели

    }

    private fun settingNavigationBar() {
        //прячем navigation bar
        viewModelMainActivity.settingNavigationBar(this)
    }

    private fun settingToolBar() {
        //задаем цвет и текст для ToolBar
        viewModelMainActivity.settingToolBar(this)
    }

    private fun initObjectsAndView() {
        initViewModels()//инициализируем вью модели
        inAnimator = AnimationUtils.loadAnimation(this, R.anim.alpha_in)
        outAnimator = AnimationUtils.loadAnimation(this, R.anim.alpha_out)
        gridLayout = findViewById(R.id.gridView_palette)
        outAnimator.setAnimationListener(this)
        inAnimator.setAnimationListener(this)
        seekBarWidthBrush = findViewById(R.id.seekBar_width_brush)
        textViewWidthBrush = findViewById(R.id.textView_width_brush)
        linearLayoutWidthBrush = findViewById(R.id.linearLayout_width_brush)
    }

    private fun initFragments(savedInstanceState: Bundle?) {
        viewModelMainActivity.initFragments(this,savedInstanceState)
    }

    private fun initViewModels() { //сюда вносятся вьюмодели, требующие инициализации
        viewModelCSV
        viewModelMainActivity
    }

    private fun viewModelObserve() {
        viewModelMainActivity.listViewColor.observe(this) {
            gridLayout.removeAllViews()
            for (color in it) {
                val frameLayout = LayoutInflater.from(this).inflate(R.layout.item_color_fl, null)
                val viewColor: View = frameLayout.findViewById(R.id.viewColor)
                viewColor.setOnClickListener { //установка слушателя на все цвета палитры
                    iconPalette.setIcon(
                        viewModelMainActivity.createColorCircleDrawables(color)
                    )//обновляем иконку

                    //палитры выбранным цветом
                    viewModelCSV.setColorStroke(color)
                    viewModelMainActivity.reversVisible()
                }
                viewColor.background = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    this.setColor(color)
                }
                gridLayout.addView(frameLayout)
            }
        }
        viewModelMainActivity.visible.observe(this) {
            if (it) gridLayout.startAnimation(inAnimator)
            else gridLayout.startAnimation(outAnimator)
            gridLayout.isVisible = it
        }

        viewModelCSV.vmStrokeWidth.observe(this) {
            textViewWidthBrush.text = it.toString()
        }
        viewModelCSV.visibleLinearLayoutWidthBrush.observe(this) {
            if (it) linearLayoutWidthBrush.visibility = View.VISIBLE
            else linearLayoutWidthBrush.visibility = View.INVISIBLE
        }
    }

    private fun setListener() {//подключаем все слушатели
        seekBarWidthBrush.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                setBrushSize(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    fun setBrushSize(progress: Int) {
        viewModelCSV.setProgressSeekBar(progress)
    }

    override fun onResume() {
        super.onResume()
        fragmentCustomSurfaceView = supportFragmentManager
            .findFragmentById(R.id.fragment_customSv_containerView) as FragmentCustomSurfaceView
//        mainActivityInterface = supportFragmentManager //интерфейс с FragmentButtonGroup
//            .findFragmentById(R.id.fragment_button_group) as MainActivityInterface
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {//загружаем меню
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.menuActivityButtonPalette)?.let {
            iconPalette = it
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //кнопки меню
        when (item.itemId) {
            R.id.menuActivityButtonClean -> {//очистить палитру
                viewModelCSV.clearCanvas()
            }

            R.id.menuActivityButtonPalette -> {//спрятать или показать палитру
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
        return if (permission != PackageManager.PERMISSION_GRANTED) {// вызывается если разрешение не было дано
            ActivityCompat.requestPermissions(
                this@MainActivity, permissionsStorage, 1
            ) //запрос на разрешение записи
            false
        } else {
            true
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



