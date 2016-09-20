package com.nanlagger.germanhelper.utils.https

import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import com.nanlagger.germanhelper.activities.LoginActivity
import com.nanlagger.germanhelper.entities.Word
import com.orm.SugarRecord
import com.qb.gson.Gson
import com.qb.gson.JsonObject
import com.qb.gson.reflect.TypeToken
import com.quickblox.core.exception.QBResponseException
import com.quickblox.core.request.QBRequestGetBuilder
import com.quickblox.customobjects.QBCustomObjects
import com.quickblox.customobjects.model.QBCustomObject
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

/**
 * Created by lyashenko_se on 15.09.2016.
 */
class AsyncLoader<T : SugarRecord>(val type: Class<T>) : AsyncTask<String, Float, Int>() {

    //val request = RequestAdapter(mContext).url("https://api.quickblox.com/data/${type.simpleName}.json").build()

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: Int) {
        super.onPostExecute(result)
    }

    override fun onProgressUpdate(vararg values: Float?) {
        super.onProgressUpdate(*values)
    }

    override fun doInBackground(vararg p0: String?): Int {
        try {
            val data = QBCustomObjects.getObjects(type.simpleName)
            data.map { type.getConstructor().newInstance() }
            return 200
        } catch (e: QBResponseException) {
            return e.httpStatusCode
        }
        /*val response = MainHttpClient.newCall(request).execute()
        return when(response.code()) {
            200 -> {


                val string = response.body().string()
                //val data = Gson().fromJson(string, Body::class.java)
                val data: Body<T> = Gson().fromJson(string, object : TypeToken<Body<T>>(){}.rawType) as Body<T>
                SugarRecord.saveInTx(data)
                200
            }
            401 -> {
                401
            }
            else -> {
                -1
            }
        }*/
    }

    class Body<R> {
        val class_name: String = ""
        val items: Array<R>? = null
    }
}