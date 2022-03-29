package com.tutu.myapplication.application

import android.app.Application
import com.tutu.myapplication.di.application
import com.tutu.myapplication.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen))
        }
    }
}