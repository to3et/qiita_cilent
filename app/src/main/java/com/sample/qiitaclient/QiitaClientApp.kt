package com.sample.qiitaclient

import android.app.Application
import com.sample.qiitaclient.dagger.AppComponent
import com.sample.qiitaclient.dagger.DaggerAppComponent

class QiitaClientApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}