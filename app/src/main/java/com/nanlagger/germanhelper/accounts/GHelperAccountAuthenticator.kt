package com.nanlagger.germanhelper.accounts

import android.accounts.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nanlagger.germanhelper.activities.LoginActivity

/**
 * Created by lyashenko_se on 11.08.2016.
 */
class GHelperAccountAuthenticator(val context: Context) : AbstractAccountAuthenticator(context) {

    override fun editProperties(accountAuthenticatorResponse: AccountAuthenticatorResponse, s: String): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun addAccount(accountAuthenticatorResponse: AccountAuthenticatorResponse, accountType: String, authTokenType: String, strings: Array<String>, options: Bundle): Bundle {
        val result = Bundle()
        result.putParcelable(AccountManager.KEY_INTENT, Intent(context, LoginActivity::class.java))
        return result
    }

    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(accountAuthenticatorResponse: AccountAuthenticatorResponse, account: Account, bundle: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(accountAuthenticatorResponse: AccountAuthenticatorResponse, account: Account, authTokenType: String, options: Bundle): Bundle? {
        val result = Bundle()
        val am = AccountManager.get(context.applicationContext)
        val authToken = am.peekAuthToken(account, authTokenType)
        if (!authToken.isNullOrEmpty()) {
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken)
        } else {
            result.putParcelable(AccountManager.KEY_INTENT, Intent(context, LoginActivity::class.java))
        }
        return result
    }

    override fun getAuthTokenLabel(s: String): String? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun updateCredentials(accountAuthenticatorResponse: AccountAuthenticatorResponse, account: Account, s: String, bundle: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun hasFeatures(accountAuthenticatorResponse: AccountAuthenticatorResponse, account: Account, strings: Array<String>): Bundle? {
        return null
    }
}
