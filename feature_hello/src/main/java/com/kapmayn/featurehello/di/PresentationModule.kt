package com.kapmayn.featurehello.di

import androidx.lifecycle.ViewModel
import com.kapmayn.coreuikit.viewmodels.ViewModelKey
import com.kapmayn.coreuikit.viewmodels.ViewModelModule
import com.kapmayn.featurehello.presentation.viewmodels.HelloViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(HelloViewModel::class)
    abstract fun helloViewModel(viewModel: HelloViewModel): ViewModel
}