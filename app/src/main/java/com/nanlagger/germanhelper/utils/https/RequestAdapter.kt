package com.nanlagger.germanhelper.utils.https

import android.accounts.AccountManager
import okhttp3.Request
import android.content.Context
import com.nanlagger.germanhelper.Controller
import com.nanlagger.germanhelper.accounts.GHelperAccount

/**
 * Created by lyashenko_se on 15.09.2016.
 */
class RequestAdapter(val context: Context) : Request.Builder() {

    override fun build(): Request {
        this.addHeader("QB-Token", AccountManager.get(context).peekAuthToken(Controller.user, GHelperAccount.TOKEN_TYPE))
        return super.build()
    }
}