package com.example.calculator

import android.app.Application
import android.content.Context

class CalculatorApp:Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object{
       var appContext :Context? = null
    }

}