package com.nanlagger.germanhelper.utils.https

import com.orm.SugarRecord
import okhttp3.Request

/**
 * Created by lyashenko_se on 15.09.2016.
 */
class RequestInfo<T: SugarRecord>(val request: Request, val type: Class<T>) {

}