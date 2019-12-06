package com.chen.baselibrary

import android.app.Application
import android.content.Context

 abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit  var context: Context
    }
}