package com.prmto.mova

import android.app.Application
import core.di.initKoin

class MovaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}