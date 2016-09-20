package com.nanlagger.germanhelper.accounts

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import com.quickblox.auth.QBAuth
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser

/**
 * Created by lyashenko_se on 11.08.2016.
 */
class LoginService : Service() {

    private var asyncLoginLoader: AsyncLoginLoader? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (asyncLoginLoader != null) {
            asyncLoginLoader?.cancel(false)
        }
        if (intent != null) {
            asyncLoginLoader = AsyncLoginLoader(intent.getStringExtra(EMAIL_KEY), intent.getStringExtra(PASSWORD_KEY), this)
            asyncLoginLoader?.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        asyncLoginLoader?.cancel(false)
    }

    class AsyncLoginLoader(val email: String, val password: String, val service: Service) : AsyncTask<String, Int, Pair<QBUser?, String?>>() {

        override fun doInBackground(vararg p0: String?): Pair<QBUser?, String?> {
            val user = QBUser()
            user.email = email
            user.password = password
            var resultUser: QBUser? = null
            var resultToken: String? = null
            try {
                resultToken = QBAuth.createSessionByEmail(user).token
                resultUser = QBUsers.signInByEmail(user.email, user.password)
            } catch (e: QBResponseException) {

            }
            return Pair(resultUser, resultToken)
        }

        override fun onPostExecute(result: Pair<QBUser?, String?>) {
            super.onPostExecute(result)
            val intent = Intent(LOGIN_ACTION)
            if (result.first != null && result.second != null) {
                result.first?.password = password
                intent.putExtra(USER_KEY, result.first)
                intent.putExtra(TOKEN_KEY, result.second)
            } else {
                intent.putExtra(ERROR_KEY, "error")
            }
            service.sendBroadcast(intent)
            service.stopSelf()
        }
    }

    companion object {
        val LOGIN_ACTION = "login_action"
        val ERROR_KEY = "error"
        val USER_KEY = "user"
        val EMAIL_KEY = "email"
        val PASSWORD_KEY = "password"
        val TOKEN_KEY = "token"
    }
}