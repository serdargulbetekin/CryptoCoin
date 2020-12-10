package com.example.cryptocoinloodos.application

import android.app.Application

class CryptoCoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
    }
}