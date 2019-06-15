package com.sample.qiitaclient.dagger

import com.sample.qiitaclient.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ClientModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}