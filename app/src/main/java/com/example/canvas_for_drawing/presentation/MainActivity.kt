package com.example.canvas_for_drawing.presentation

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
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
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.di.DaggerComponentActivity
import com.example.canvas_for_drawing.presentation.fragments.FragmentButtonGroup
import com.example.canvas_for_drawing.presentation.viewModels.ViewModelCSV
import com.example.canvas_for_drawing.presentation.viewModels.ViewModelMainActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Animation.AnimationListener {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var fragmentButtonGroup: FragmentButtonGroup
    private lateinit var gridLayout: GridLayout //палитра цветов
    private lateinit var inAnimator: Animation
    private lateinit var outAnimator: Animation
    private lateinit var iconPalette: MenuItem
    private lateinit var viewWidthBrush: LinearLayout
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
        initObjectsAndView(this)//инициализация объектов и view
        viewModelObserve()//подписываемся на обновления
        settingApp(this,savedInstanceState)//настройки приложения
        setListener()//подключаем слушатели
    }

    private fun initObjectsAndView(activity: AppCompatActivity) {
        initViewModels()//инициализируем вью модели
        inAnimator = AnimationUtils.loadAnimation(activity, R.anim.alpha_in)
        outAnimator = AnimationUtils.loadAnimation(activity, R.anim.alpha_out)
        gridLayout = findViewById(R.id.gridView_palette)
        activity as MainActivity
        outAnimator.setAnimationListener(activity)
        inAnimator.setAnimationListener(activity)
        seekBarWidthBrush = findViewById(R.id.seekBar_width_brush)
        textViewWidthBrush = findViewById(R.id.textView_width_brush)
        viewWidthBrush = findViewById(R.id.linearLayout_width_brush)
    }

    private fun initViewModels() { //сюда вносятся вьюмодели, требующие инициализации
        viewModelCSV
        viewModelMainActivity
    }

    private fun settingApp(activity: AppCompatActivity, savedInstanceState: Bundle?) {
        //настройки приложения
        initFragments(activity, savedInstanceState)//инициализация фрагментов
        settingNavigationBar(activity) //настройка NavigationBar
        setBackColor(getColor(activity, R.color.background_color)) //цвет заднего фона приложения
        setColorSpace(getColor(activity,R.color.white)) //установка цвета холста
        settingPalette()//настройки палитры
        settingToolBar(activity, "Кисть") //установка заголовка
    }

    private fun getColor(activity: AppCompatActivity, color: Int):Int{ //получить цвет из colors
        return ContextCompat.getColor(activity, color)
    }
    private fun initFragments(activity: AppCompatActivity, savedInstanceState: Bundle?) {
        viewModelMainActivity.initFragments(activity, savedInstanceState)//инициализация фрагментов
    }

    private fun settingNavigationBar(activity: AppCompatActivity) {
        //настройка NavigationBar (скрыть NavigationBar)
        viewModelMainActivity.settingNavigationBar(activity)
    }

    private fun settingPalette() {
       //добавляем все нужные цвета в палитру по порядку
        addColorPalette(Color.RED)
        addColorPalette(Color.GREEN)
        addColorPalette(Color.BLUE)
        addColorPalette(Color.LTGRAY)
        addColorPalette(Color.MAGENTA)
        addColorPalette(Color.YELLOW)
        addColorPalette(Color.BLACK)
    }
    private fun settingToolBar(activity: AppCompatActivity, title: String) {
        //настройки tool bar (установка заголовка)
        viewModelMainActivity.settingToolBar(activity, title)
    }

    private fun setBackColor(color: Int) {//установка BackgroundColor
       viewModelMainActivity.setBackColor(color)
    }
    private fun addColorPalette(color: Int) {//установка BackgroundColor
       viewModelMainActivity.addColor(color)
    }
    private fun setColorSpace(color: Int) {//установка BackgroundColor
        viewModelCSV.setColorSpace(color)
    }

    private fun viewModelObserve() {
        viewModelMainActivity.vmListViewColor.observe(this) {
            //при изменении набора цветов пересобрать gridLayout
            gridLayout.removeAllViews()
            for (color in it) {
                val frameLayout = LayoutInflater.from(this).inflate(R.layout.item_color_fl, null)
                val viewColor: View = frameLayout.findViewById(R.id.viewColor)
                viewColor.setOnClickListener { //установка слушателя на все цвета палитры
                    iconPalette.setIcon( //обновляем иконку палитры выбранным цветом
                        viewModelMainActivity.createColorCircleDrawables(color)
                    )
                    viewModelCSV.setColorStroke(color)//выбор цвета кисти
                    viewModelMainActivity.visibilityInvisibleVisiblePalette()
                }
                viewColor.background = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    this.setColor(color)
                }
                gridLayout.addView(frameLayout)
            }
        }
        viewModelMainActivity.vmVisiblePalette.observe(this) {
            //при изменении видимости палитры
            if (it) gridLayout.startAnimation(inAnimator)
            else gridLayout.startAnimation(outAnimator)
            gridLayout.isVisible = it
        }
        viewModelMainActivity.vmStrokeWidth.observe(this) {
            //при изменении размера кисти
            viewModelCSV.setStrokeWidth(it)
            textViewWidthBrush.text = it.toString()
        }
        viewModelMainActivity.vmVisibleViewWidthBrush.observe(this) {
            //при изменении видимости панели регулировки размера кисти
            viewWidthBrush.isVisible = it
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

    fun setBrushSize(progress: Int) { // установка ширины кисти
        viewModelMainActivity.setProgressSeekBar(progress)
    }

    override fun onResume() {
        super.onResume()
        fragmentButtonGroup = supportFragmentManager
            .findFragmentById(R.id.fragment_button_group) as FragmentButtonGroup
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
                settingToolBar(this,"Кисть")
                val lastColor= viewModelMainActivity.getLastSavedColor()
                viewModelCSV.setColorStroke(lastColor)
                viewModelMainActivity.visibilityInvisibleVisiblePalette()
            }

            R.id.menuActivityButtonSaveImage -> {
                save(this)//сохранить канвас в jpg
            }

            R.id.menuActivityButtonEraser -> {
                settingToolBar(this,"Ластик")
                viewModelCSV.setEraser()//выбрать ластик
            }
        }
        return true
    }

    private fun save(activity: AppCompatActivity) {//функция сохранения изображения
        if (getPermissionWriteReadExternalExternalStorage(activity)) {
            val dateTime = viewModelMainActivity.getDateTime()
            if (viewModelCSV.saveCanvas(dateTime))
                Toast.makeText(activity, "Сохранено $dateTime", Toast.LENGTH_SHORT).show()
            else Toast.makeText(activity, "Ошибка сохранения", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPermissionWriteReadExternalExternalStorage(activity: AppCompatActivity)
    : Boolean { //получение разрешений на сохранение
        return viewModelMainActivity.getPermissionWriteReadExternalStorage(activity)
    }

    override fun onAnimationStart(animation: Animation?) {
        //    println("FragmentBG start animation ${animation.toString()}")
    }

    override fun onAnimationEnd(animation: Animation?) {
        //    println("FragmentBG end animation ${animation.toString()}")
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }
}



