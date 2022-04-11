package com.example.canvas_for_drawing.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.canvas_for_drawing.R

private lateinit var viewModel: FragmentSvViewModel
private lateinit var customSurfaceView: CustomSurfaceView

class CustomSurfaceViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_custom_surface_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customSurfaceView = view.findViewById(R.id.custom_surfaceView)
        viewModel = ViewModelProvider(this).get(FragmentSvViewModel::class.java)
        customSurfaceView.paths=viewModel.showCanvas()//показать содержимое при запуске фрагмента
       viewModel.setColorBack().observe(viewLifecycleOwner) { //заливка фона
           customSurfaceView.colorCanvas=it
        }
        customSurfaceView.modelDrawingObjectLD.observe(viewLifecycleOwner){ //рисование слоев ТАК БЫТЬ НЕ ДОЛЖНО, НАДО ПОДПРАВИТЬ
           customSurfaceView.paths = viewModel.paint(it)
        }
    }
}

//    override fun onStart() {
//        super.onStart()
//        println(customSurfaceView.getScreenOrientation())
//    }

