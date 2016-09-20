package com.nanlagger.germanhelper.controllers

import com.orm.SugarRecord

/**
 * Created by lyashenko_se on 16.09.2016.
 */
object ContentController {

    fun <T> getClass(type: Class<T>, id: Long): T {
        return SugarRecord.findById(type, id)
    }
}