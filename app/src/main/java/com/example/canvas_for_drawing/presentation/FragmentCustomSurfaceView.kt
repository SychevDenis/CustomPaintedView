package com.example.canvas_for_drawing.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.data.CanvasRepositoryImpl

private lateinit var viewModel: ViewModelFragmentSv
private lateinit var customSurfaceView: CustomSurfaceView
@SuppressLint("StaticFieldLeak")
private lateinit var buttonBack: Button
@SuppressLint("StaticFieldLeak")
private lateinit var buttonNext: Button

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
        viewModel = ViewModelProvider(this).get(ViewModelFragmentSv::class.java)
        customSurfaceView = view.findViewById(R.id.custom_surfaceView)
        buttonBack = view.findViewById(R.id.buttonBack)
        buttonNext = view.findViewById(R.id.buttonNext)

        buttonBack.setOnClickListener(){
            viewModel.clickBack(customSurfaceView.infoLayerCanvas())
            Log.i("log", customSurfaceView.activeLayer.toString() + " слой активный")
        }
        buttonNext.setOnClickListener(){
            viewModel.clickNext(customSurfaceView.infoLayerCanvas())
            Log.i("log", customSurfaceView.activeLayer.toString() + " слой активный")
        }
        viewModel.getInfoLayer().observe(viewLifecycleOwner){//обновление активного слоя во вью из репозитория
            customSurfaceView.activeLayer=it
        }
        customSurfaceView.paths =
            viewModel.showCanvas()//показать содержимое canvas при запуске фрагмента

        viewModel.setColorBack().observe(viewLifecycleOwner) { //заливка фона
            customSurfaceView.colorCanvas = it
        }
        customSurfaceView.modelDrawingObjectLD.observe(viewLifecycleOwner) { //рисование слоев ТАК БЫТЬ НЕ ДОЛЖНО, НАДО ПОДПРАВИТЬ
            customSurfaceView.paths = viewModel.paint(it)
        }

    }
}


