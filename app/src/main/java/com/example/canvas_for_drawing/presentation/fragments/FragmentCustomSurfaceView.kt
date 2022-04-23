package com.example.canvas_for_drawing.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.domain.models.SettingPaintObject
import com.example.canvas_for_drawing.presentation.CustomSurfaceView
import com.example.canvas_for_drawing.presentation.ViewModelFragmentCustomSurfaceView

private lateinit var viewModel: ViewModelFragmentCustomSurfaceView
private lateinit var customSurfaceView: CustomSurfaceView


class FragmentCustomSurfaceView : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_surface_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // Log.i("log", "OnCreateSV")
        viewModel = ViewModelProvider(this).get(ViewModelFragmentCustomSurfaceView::class.java)
        customSurfaceView = view.findViewById(R.id.custom_surfaceView)
        viewModelObserve()//подписываемся на нужные объекты

        //   buttonBack = view.findViewById(R.id.buttonBack)
        //  buttonNext = view.findViewById(R.id.buttonNext)

        //        buttonBack.setOnClickListener() {
//            viewModel.clickBack(customSurfaceView.infoLayerCanvas())
//            Log.i("log", customSurfaceView.activeLayer.toString() + " слой активный")
//        }
//        buttonNext.setOnClickListener() {
//            viewModel.clickNext(customSurfaceView.infoLayerCanvas())
//            Log.i("log", customSurfaceView.activeLayer.toString() + " слой активный")
//        }

    }


    companion object {
        fun clickClean() { //метод отчистки экрана
            viewModel.cleanCanvas()
        }
        fun clickSave() { //метод отчистки экрана
            viewModel.saveCanvas(customSurfaceView.onSizeChanged)
        }
        fun clickBack(){ //метод клик назад
            viewModel.clickBack(customSurfaceView.infoLayerCanvas)
        }
        fun clickNext(){ //метод клик вперед
            viewModel.clickNext(customSurfaceView.infoLayerCanvas)
        }
        fun settingPaint(settingPaintObject: SettingPaintObject){//метод отправки данных о paint объекте из buttonFragment
           viewModel.settingPaint(customSurfaceView.paint(settingPaintObject))
        }
    }

    private fun viewModelObserve() {
        viewModel.getInfoLayer().observe(viewLifecycleOwner) {//обновление активного слоя
                customSurfaceView.activeLayer = it
                customSurfaceView.infoLayerCanvas.activeLayerCanvas=it
            }
        viewModel.showCanvasPaths().observe(viewLifecycleOwner) { //показать содержимое canvas
            customSurfaceView.paths = it
        }
        viewModel.showCanvasPaint().observe(viewLifecycleOwner) { //показать содержимое canvas
            customSurfaceView.paints = it
        }

        viewModel.setColorBack().observe(viewLifecycleOwner) { //заливка фона
            customSurfaceView.colorCanvas = it
        }

        viewModel.paint(customSurfaceView.modelDrawingObjectLD) //рисование объектов
            .observe(viewLifecycleOwner) {
            viewModel.paint(customSurfaceView.modelDrawingObjectLD)

        }
    }
}




