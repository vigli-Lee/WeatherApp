package com.plcoding.weatherapp

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}