package com.example.canvas_for_drawing.di.modules

import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.presentation.viewModels.ViewModelCSV
import com.example.canvas_for_drawing.presentation.viewModels.ViewModelMainActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ModuleViewModel {
    @IntoMap
    @StringKey("ViewModelCSV")
    @Binds
    fun bindViewModelCSV(viewModelCSV: ViewModelCSV):ViewModel

    @IntoMap
    @StringKey("ViewModelMainActivity")
    @Binds
    fun bindViewModelMainActivity(viewModelMainActivity: ViewModelMainActivity):ViewModel
 }