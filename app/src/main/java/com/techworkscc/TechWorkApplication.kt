package com.techworkscc

import android.app.Application
import com.techworkscc.di.appModule
import com.techworkscc.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TechWorkApplication: Application() {
    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@TechWorkApplication)
            modules(appModule, networkModule)
        }
        super.onCreate()
    }
}