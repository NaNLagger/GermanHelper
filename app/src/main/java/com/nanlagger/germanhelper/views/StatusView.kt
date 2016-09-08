package com.nanlagger.germanhelper.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * Created by lyashenko_se on 08.09.2016.
 */
class StatusView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private var _countPoint = 5
    private var countPoint: Int
        get() = _countPoint
        set(value) {
            _countPoint = value
        }

    private var _sizePoint = 30f
    private var sizePoint: Float
        get() = _sizePoint
        set(value) {
            _sizePoint = value
        }

    private var _sizeLine = 5f
    private var sizeLine: Float
        get() = _sizeLine
        set(value) {
            _sizeLine = value
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun generatePosition() {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.RED
        val width_line = (width  - countPoint * sizePoint) / (countPoint - 1)
        val radius = sizePoint / 2
        val pointPositions = ArrayList<Pair<Float, Float>>()
        for (i in 0..countPoint) {
            pointPositions += Pair(i * (width_line + sizePoint) + radius, height.toFloat() / 2)
            canvas.drawCircle(i * (width_line + sizePoint) + radius, height.toFloat() / 2, radius, paint)
        }
        for (i in 0..(countPoint - 1)) {
            canvas.drawRect(pointPositions[i].first, pointPositions[i].second - sizeLine / 2, pointPositions[i + 1].first, pointPositions[i + 1].second + sizeLine / 2, paint)
        }
    }
}