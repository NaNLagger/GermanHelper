package com.nanlagger.germanhelper

import android.app.Application
import com.quickblox.core.QBSettings
import okhttp3.OkHttpClient

/**
 * Created by lyashenko_se on 11.08.2016.
 */
class GHelperApp : Application() {
    companion object {
        val APP_ID = "42971"
        val AUTH_KEY = "KETVyJpwx8-E6rm"
        val AUTH_SECRET = "Ex8P7kNwQWdJ5Az"
        val ACCOUNT_KEY = "u1x1uUyyqkjhAGpuGQkJ"
    }

    override fun onCreate() {
        super.onCreate()
        QBSettings.getInstance().init(applicationContext, APP_ID, AUTH_KEY, AUTH_SECRET)
        QBSettings.getInstance().accountKey = ACCOUNT_KEY
    }
}
