package com.imeepwni.android.ankowiki.app

import android.app.*

/**
 * Create by guofeng on 2017/7/20.
 */
class App : Application() {
    companion object {
        private lateinit var app: App
        fun getInstance() = app
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}