package com.pru.composeapp.fakeapi.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    companion object {
        lateinit var context: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}