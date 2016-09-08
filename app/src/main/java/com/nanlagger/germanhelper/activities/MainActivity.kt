package com.nanlagger.germanhelper.activities

import android.accounts.AccountManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nanlagger.germanhelper.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (AccountManager.get(this).accounts.size == 0) {
            startActivityForResult(Intent(this, LoginActivity::class.java), ACTION_LOGIN)
        } else {
            startActivity(Intent(this, TestActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            ACTION_LOGIN ->
                if(resultCode != RESULT_OK)
                    finish()
                else
                    startActivity(Intent(this, TestActivity::class.java))
        }
    }

    companion object {
        val ACTION_LOGIN = 1
    }
}

