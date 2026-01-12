package com.example.winerrorhandling.app

import android.app.Application
import com.example.winerrorhandling.di.initKoin
import org.koin.android.ext.koin.androidContext

class MealApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MealApplication)
        }
    }
}