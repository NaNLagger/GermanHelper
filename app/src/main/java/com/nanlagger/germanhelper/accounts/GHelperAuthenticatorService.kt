package com.nanlagger.germanhelper.accounts

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by lyashenko_se on 11.08.2016.
 */
class GHelperAuthenticatorService : Service() {
    private lateinit var mAuthenticator: GHelperAccountAuthenticator

    override fun onCreate() {
        super.onCreate()
        mAuthenticator = GHelperAccountAuthenticator(applicationContext)
    }

    override fun onBind(intent: Intent): IBinder {
        return mAuthenticator.iBinder
    }
}
