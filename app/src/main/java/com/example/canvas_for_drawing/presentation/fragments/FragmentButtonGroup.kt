package com.example.canvas_for_drawing.presentation.fragments

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.presentation.MainActivityInterface
import com.example.canvas_for_drawing.presentation.ViewModelCSV


class FragmentButtonGroup() : Fragment() {


    private val viewModelCSV: ViewModelCSV by activityViewModels()//нужно искать способ вставить

    //это через инъекцию, но я пока не знаю как
    //private lateinit var buttonBack: Button
    //private lateinit var buttonNext: Button
   // private lateinit var fragmentButtonGroupInterface: FragmentButtonGroupInterface
    private lateinit var buttonWidthBrush: ImageButton
    //private var visibleLinearLayoutWidthBrush = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_button_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVariables(view)//инициализация всех вью
        viewModelObserve()
        setListener(view)
    }

    fun viewModelObserve() {//подписываем вью модели на обновления
        viewModelCSV.vmStrokeWidth.observe(this) {
            paintIconButtonWidthBrush(it)
        }
    }

    private fun setListener(view: View) {//подключаем все слушатели

//        buttonBack.setOnClickListener() {
//            viewModelCSV.backLayers()
//        }
        buttonWidthBrush.setOnClickListener() {
            println("FBG "+ viewModelCSV.visibleLinearLayoutWidthBrush.value)
            viewModelCSV.visibleLinearLayoutWidthBrush.value =
                viewModelCSV.visibleLinearLayoutWidthBrush.value?.not()

//            fragmentButtonGroupInterface.linearLayoutWidthBrushVisibleInversion(
//                visibleLinearLayoutWidthBrush
//            )
//            visibleLinearLayoutWidthBrush = !visibleLinearLayoutWidthBrush
        }
//
//        buttonNext.setOnClickListener() {
//            viewModelCSV.nextLayers()
//        }

    }


    private fun initVariables(view: View) { //инициализация переменных
//        fragmentButtonGroupInterface = //инициализируем интерфейс с активити
//            requireActivity() as FragmentButtonGroupInterface //к котрому прикреплен фрагмент

//        buttonBack = view.findViewById(R.id.button_back)
//        buttonNext = view.findViewById(R.id.button_next)
        buttonWidthBrush = view.findViewById(R.id.view_fragment_button_group)
        paintIconButtonWidthBrush(10f)
    }

    private fun paintIconButtonWidthBrush(width: Float) { //
        buttonWidthBrush.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FE0000"))
        val localWidth = 150 // Ширина Bitmap
        val localHeight = 150 // Высота Bitmap
        val bitmap = Bitmap.createBitmap(localWidth, localHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        canvas.drawCircle((localWidth / 2).toFloat(), (localHeight / 2).toFloat(), (width / 1.5f)+2f, paint)
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        canvas.drawCircle((localWidth / 2).toFloat(), (localHeight / 2).toFloat(), 70F, paint)
        buttonWidthBrush.setImageBitmap(bitmap)
    }
//    override fun buttonWidthBrushEditIcon(width: Float) {//интерфейс получения размера иконки
//        // из активности
//        paintIconButtonWidthBrush(width)
//    }
}

interface FragmentButtonGroupInterface {
    fun linearLayoutWidthBrushVisibleInversion(visibleLinearLayoutWidthBrush: Boolean)//инверсия видимости
}

