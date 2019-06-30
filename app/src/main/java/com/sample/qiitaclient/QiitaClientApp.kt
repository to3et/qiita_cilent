package com.sample.qiitaclient

import android.app.Application
import com.sample.qiitaclient.dagger.AppComponent
import com.sample.qiitaclient.dagger.ApplicationModule
import com.sample.qiitaclient.dagger.DaggerAppComponent

class QiitaClientApp : Application() {

    val appComponent : AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}