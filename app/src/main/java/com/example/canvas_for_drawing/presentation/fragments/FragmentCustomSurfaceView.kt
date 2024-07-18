package com.example.canvas_for_drawing.presentation.fragments

import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.canvas_for_drawing.R
import com.example.canvas_for_drawing.domain.models.DrawingObject
import com.example.canvas_for_drawing.domain.models.OnSizeChanged
import com.example.canvas_for_drawing.presentation.CustomSurfaceView
import com.example.canvas_for_drawing.presentation.viewModels.ViewModelCSV

private lateinit var customSurfaceView: CustomSurfaceView

class FragmentCustomSurfaceView() : Fragment(),
    CustomSurfaceView.CustomSurfaceViewInterface {

    private val viewModelCSV: ViewModelCSV by activityViewModels()//нужно искать способ вставить
    //это через инъекцию, но я пока не знаю как

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_custom_surface_view,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customSurfaceView = view.findViewById(R.id.custom_surfaceView)
        customSurfaceView.CSVInterface = this
        viewModelObserve()//подписываемся на нужные объекты
    }

    private fun viewModelObserve() {//подписываемся на обновления
        //обновление цвета заднего фона
        viewModelCSV.colorBackgroundCanvasLD.observe(viewLifecycleOwner) {
            customSurfaceView.setColorBackgroundCanvas(it)
        }
        //обновление активного слоя
        viewModelCSV.activeLayerLD.observe(viewLifecycleOwner) {
            customSurfaceView.setActiveLayer(it)
        }
        //обновление рисунка
        viewModelCSV.pairLD.observe(viewLifecycleOwner) {
            val listPath = it.getListPath() as? MutableList<Path> ?: return@observe
            val listPaint = it.getListPaint() as? MutableList<Paint> ?: return@observe
            customSurfaceView.setPathAndPaint(listPath, listPaint)
        }
    }

    override fun touchEvent(event: MotionEvent) {
        viewModelCSV.paint(DrawingObject(event.x, event.y, event.action))
    }

    override fun setColorBackgroundCanvas(color: Int) {
        viewModelCSV.setColorBack(color)
    }

    override fun setSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        viewModelCSV.setSizeChanged(OnSizeChanged(w, h, oldw, oldh))
    }
}




