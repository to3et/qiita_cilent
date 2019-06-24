package com.sample.qiitaclient.dagger

import androidx.lifecycle.ViewModelProvider
import com.sample.qiitaclient.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}