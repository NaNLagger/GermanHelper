package com.nanlagger.germanhelper.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
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

    private var _countPoint = 0

    private var _sizePoint = 30f
    private var sizePoint: Float
        get() = _sizePoint
        set(value) {
            _sizePoint = value
            invalidate()
            requestLayout()
        }

    private var _sizeLine = 5f
    private var sizeLine: Float
        get() = _sizeLine
        set(value) {
            _sizeLine = value
            invalidate()
            requestLayout()
        }

    private val inactivePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val activePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val points = ArrayList<Point>()

    init {
        inactivePaint.color = Color.GRAY
        activePaint.color = Color.BLUE
        textPaint.color = Color.BLACK
        textPaint.textSize = 30f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        refresh()
    }

    private fun refresh() {
        val width_line = (width  - _countPoint * sizePoint) / (_countPoint - 1)
        val radius = sizePoint / 2
        for (i in 0.._countPoint - 1) {
            points[i].position = Pair(i * (width_line + sizePoint) + radius, height.toFloat() / 2)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (point in points) {
            canvas.drawCircle(point.position.first, point.position.second, sizePoint / 2, activePaint)
            canvas.drawText(
                    point.text,
                    point.position.first - point.textBounds.width() / 2,
                    point.position.second + sizePoint + point.textBounds.height(),
                    textPaint)
        }
        for (i in 0..(_countPoint - 2)) {
            canvas.drawRect(
                    points[i].position.first, points[i].position.second - sizeLine / 2,
                    points[i + 1].position.first, points[i + 1].position.second + sizeLine / 2, activePaint)
        }
    }

    fun setPoints(names: Array<String>) {
        points.clear()
        _countPoint = names.size
        val hyi = 0.._countPoint - 1
        for (i in hyi) {
            val point = Point()
            point.text = names[i]
            points += (point)
        }
        invalidate()
        requestLayout()
    }

    inner class Point {
        private var _position = Pair<Float, Float>(0f, 0f)
        var position: Pair<Float, Float>
            get() = _position
            set(value) {
                _position = value
            }
        private var _text = ""
        var text: String
            get() = _text
            set(value) {
                _text = value
                textPaint.getTextBounds(_text, 0, _text.length, textBounds)
            }
        private var _active = false
        var active: Boolean
            get() = _active
            set(value) {
                _active = value
            }

        val textBounds: Rect = Rect()
    }
}