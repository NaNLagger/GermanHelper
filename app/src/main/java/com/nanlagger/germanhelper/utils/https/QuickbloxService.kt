package com.nanlagger.germanhelper.utils.https

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import com.nanlagger.germanhelper.entities.Word
import com.orm.SugarRecord

/**
 * Created by lyashenko_se on 15.09.2016.
 */
class QuickbloxService : Service() {

    private var asyncLoader: AsyncLoader<Word>? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (asyncLoader != null) {
            asyncLoader?.cancel(false)
        }
        if (intent != null) {
            asyncLoader = AsyncLoader<Word>(Word::class.java, this)
            asyncLoader?.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        asyncLoader?.cancel(false)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    companion object {
        val KEY_CLASS_NAME = "CLASS_NAME"
        val KEY_ACTION = "ACTION"
        val KEY_IDS = "IDS"
    }
}