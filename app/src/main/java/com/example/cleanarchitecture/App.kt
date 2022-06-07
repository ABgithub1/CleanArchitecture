package com.example.cleanarchitecture

import android.app.Application
import com.example.cleanarchitecture.koin.allViewModelsModule
import com.example.data.koin.allDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                allDataModule,
                allViewModelsModule
            )
        }

    }
}