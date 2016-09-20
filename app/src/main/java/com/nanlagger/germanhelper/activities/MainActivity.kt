package com.nanlagger.germanhelper.activities

import android.accounts.Account
import android.accounts.AccountManager
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.nanlagger.germanhelper.Controller
import com.nanlagger.germanhelper.R
import com.nanlagger.germanhelper.accounts.GHelperAccount

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val accounts = AccountManager.get(this).getAccountsByType(GHelperAccount.ACCOUNT_TYPE)
        if (accounts.size == 0) {
            startActivityForResult(Intent(this, LoginActivity::class.java), ACTION_LOGIN)
        } else {
            if(accounts.size == 1) {
                setUser(accounts[0])
            } else {
                selectAccount(accounts)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            ACTION_LOGIN ->
                if(resultCode == RESULT_OK) {
                    startActivity(Intent(this, TestActivity::class.java))
                    finish()
                }
        }
    }

    private fun setUser(account: Account) {
        Controller.user = account
        startActivity(Intent(this, TestActivity::class.java))
        finish()
    }

    private fun selectAccount(accounts: Array<Account>) {
        AlertDialog.Builder(this)
                .setItems(accounts.map { it.name }.toTypedArray(), DialogInterface.OnClickListener { dialogInterface, i -> setUser(accounts[i]) })
                .setTitle("Select account")
                .create()
                .show()
    }

    companion object {
        val ACTION_LOGIN = 1
    }
}

