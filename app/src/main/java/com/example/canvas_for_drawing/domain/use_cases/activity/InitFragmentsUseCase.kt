package com.example.canvas_for_drawing.domain.use_cases.activity
import com.example.canvas_for_drawing.domain.repository_interfaces.CanvasRepositoryActivity
import javax.inject.Inject

class InitFragmentsUseCase
@Inject constructor(private val canvasRepository: CanvasRepositoryActivity) {
    fun init(activity: Any, savedInstanceState: Any?):Boolean{
       return canvasRepository.initFragment(activity,savedInstanceState)
    }
}