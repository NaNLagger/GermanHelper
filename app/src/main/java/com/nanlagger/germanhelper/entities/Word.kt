package com.nanlagger.germanhelper.entities

import com.orm.SugarRecord
import com.orm.dsl.Column
import com.orm.dsl.Table
import com.qb.gson.annotations.SerializedName
import com.quickblox.customobjects.model.QBCustomObject

/**
 * Created by lyashenko_se on 15.09.2016.
 */
@Table(name = "word")
class Word() : SugarRecord() {
    @Column(name = "name")
    private var _name: String = ""
    var name: String
        get() = _name
        set(value) {
            _name = value
        }

    constructor(name: String): this() {
        this._name = name
    }
}