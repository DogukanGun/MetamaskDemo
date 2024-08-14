package com.dag.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MetamaskDemoApplication : Application() {
    companion object {
        lateinit var appInstance: MetamaskDemoApplication
        var baseUrl = "https://api.dogukangun.de/"
    }
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}