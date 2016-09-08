package com.nanlagger.germanhelper.activities

import android.accounts.Account
import android.accounts.AccountAuthenticatorActivity
import android.accounts.AccountManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.nanlagger.germanhelper.R
import com.nanlagger.germanhelper.accounts.GHelperAccount
import com.nanlagger.germanhelper.accounts.LoginService
import com.quickblox.users.model.QBUser

class LoginActivity : AccountAuthenticatorActivity() {

    private val edtEmail: EditText by lazy { findViewById(R.id.edtEmail) as EditText }
    private val edtPassword: EditText by lazy { findViewById(R.id.edtPassword) as EditText }
    private val btnLogin: Button by lazy { findViewById(R.id.btnLogin) as Button }
    private val rootView: View by lazy { findViewById(R.id.root) }
    private val pnlLogin: View by lazy { findViewById(R.id.pnlLogin)}
    private val progress: ProgressBar by lazy { findViewById(R.id.progress) as ProgressBar }

    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.hasExtra(LoginService.USER_KEY) && intent.hasExtra(LoginService.TOKEN_KEY)) {
                val user = intent.getSerializableExtra(LoginService.USER_KEY) as QBUser
                val token = intent.getStringExtra(LoginService.TOKEN_KEY)
                onTokenReceived(GHelperAccount(user.login), user.password, token)
            } else {
                Snackbar.make(
                        rootView,
                        if (intent.hasExtra(LoginService.ERROR_KEY))
                            intent.getStringExtra(LoginService.ERROR_KEY)
                        else
                            getString(R.string.unknown_error),
                        Snackbar.LENGTH_LONG)
                .show()
            }
            progress.visibility = View.INVISIBLE
            pnlLogin.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setResult(RESULT_CANCELED)
        btnLogin.setOnClickListener {
            view ->
            val intent = Intent(this, LoginService::class.java)
            intent.putExtra(LoginService.EMAIL_KEY, edtEmail.text.toString())
            intent.putExtra(LoginService.PASSWORD_KEY, edtPassword.text.toString())
            startService(intent)
            progress.visibility = View.VISIBLE
            pnlLogin.visibility = View.INVISIBLE
        }
        registerReceiver(mBroadcastReceiver, IntentFilter(LoginService.LOGIN_ACTION))
    }

    override fun onDestroy() {
        unregisterReceiver(mBroadcastReceiver)
        super.onDestroy()
    }

    fun onTokenReceived(account: Account, password: String, token: String) {
        val am = AccountManager.get(this)
        val result = Bundle()
        if (am.addAccountExplicitly(account, password, Bundle())) {
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
            result.putString(AccountManager.KEY_AUTHTOKEN, token)
            am.setAuthToken(account, GHelperAccount.TOKEN_TYPE, token)
        } else {
            result.putString(AccountManager.KEY_ERROR_MESSAGE, getString(R.string.account_already_exists))
        }
        setAccountAuthenticatorResult(result)
        setResult(RESULT_OK)
        finish()
    }
}
