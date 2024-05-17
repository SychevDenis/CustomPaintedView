package com.example.canvas_for_drawing.di

import androidx.lifecycle.ViewModel
import com.example.canvas_for_drawing.presentation.ViewModelCSV
import com.example.canvas_for_drawing.presentation.ViewModelMainActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ModuleViewModel {
    @IntoMap
    @StringKey("ViewModelCSV")
    @Binds
    fun bindViewModelCSV(viewModelCSV:ViewModelCSV):ViewModel

    @IntoMap
    @StringKey("ViewModelMainActivity")
    @Binds
    fun bindViewModelMainActivity(viewModelMainActivity: ViewModelMainActivity):ViewModel
}