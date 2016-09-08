package com.nanlagger.germanhelper.accounts

import android.accounts.Account
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by lyashenko_se on 11.08.2016.
 */
class GHelperAccount : Account {

    constructor(name: String) : super(name, ACCOUNT_TYPE)

    constructor(parcel: Parcel) : super(parcel)

    companion object {
        val ACCOUNT_TYPE = "com.nanlagger.germanhelper.ACCOUNT"
        val TOKEN_TYPE = "com.nanlagger.germanhelper.TOKEN_FULL_ACCESS"

        val CREATOR: Parcelable.Creator<GHelperAccount> = object : Parcelable.Creator<GHelperAccount> {
            // распаковываем объект из Parcel
            override fun createFromParcel(parcel: Parcel): GHelperAccount {
                return GHelperAccount(parcel)
            }

            override fun newArray(size: Int): Array<GHelperAccount?> {
                return arrayOfNulls(size)
            }
        }
    }
}
