package com.nanlagger.germanhelper

import android.accounts.Account
import com.nanlagger.germanhelper.accounts.GHelperAccount
import com.nanlagger.germanhelper.entities.Word

/**
 * Created by lyashenko_se on 15.09.2016.
 */
object Controller {
    private var _user: Account? = null
    var user: Account
        get() {
            return if(_user == null)
                GHelperAccount("unknown")
            else
                _user!!
        }
        set(value) {
            _user = value
        }

}