package com.example.cryptocoinloodos.application

import android.content.Context
import com.example.cryptocoinloodos.di.AppComponent
import com.example.cryptocoinloodos.di.AppModule
import com.example.cryptocoinloodos.di.DaggerAppComponent

object AppConfig {

    private lateinit var context: Context
     lateinit var appComponent: AppComponent


    fun init(context: Context) {
        this.context = context
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(AppConfig.context))
            .build()

    }
}