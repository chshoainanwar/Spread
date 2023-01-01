package com.dev.spread.base

import android.app.Application



class BaseApplication : Application() {
    companion object {
        private const val ONESIGNAL_APP_ID = "5ac02dab-eed5-4f82-914e-5940c43b67b2"
        var instance: BaseApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        World.init(this)
    }
}
