package com.nanlagger.germanhelper.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nanlagger.germanhelper.R
import com.nanlagger.germanhelper.views.StatusView

/**
 * Created by lyashenko_se on 08.09.2016.
 */

class TestActivity : AppCompatActivity() {

    private val statusView: StatusView by lazy { findViewById(R.id.statusView) as StatusView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        statusView.setPoints(Array(10, {x -> "Point $x"}))
    }
}
