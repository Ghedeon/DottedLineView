package com.ghedeon.dottedlineview

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Paint.Style.STROKE
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View


class DottedLineView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var radius: Float = DEFAULT_RADIUS
    var gap: Float = DEFAULT_GAP
    @ColorInt
    var color: Int = DEFAULT_COLOR

    private val paint: Paint by lazy {
        val paint = Paint(ANTI_ALIAS_FLAG)
        paint.style = STROKE
        paint.strokeWidth = 2 * radius
        paint.color = color
        paint
    }

    private val path: Path by lazy { Path() }

    init {
        context.styledAttrs(attrs, R.styleable.DottedLineView) {
            radius = dimen(R.styleable.DottedLineView_radius, DEFAULT_RADIUS)
            gap = dimen(R.styleable.DottedLineView_minGap, DEFAULT_GAP)
            color = getColor(R.styleable.DottedLineView_color, DEFAULT_COLOR)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        prettify()
        initPath()
    }

    /**
     * Avoid half cut circles
     */
    private fun prettify() {
        val diameter = 2 * radius
        val wholeCount: Int = height / (diameter + gap).toInt()
        val diffHeight: Float = height - wholeCount * (diameter + gap)
        if (diffHeight < diameter) { // the circle is cut
            gap += diffHeight / wholeCount
        }
    }

    private fun initPath() {
        val shape = Path()
        shape.addCircle(radius, 0f, radius, Path.Direction.CW)

        val offset = 0f
        val style = PathDashPathEffect.Style.ROTATE
        val advance = 2 * radius + gap
        paint.pathEffect = PathDashPathEffect(shape, advance, offset, style)

        path.moveTo(width / 2f, 0f)
        path.lineTo(width / 2f, height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    companion object {
        private const val DEFAULT_RADIUS = 8f
        private const val DEFAULT_GAP = 16f
        private const val DEFAULT_COLOR = Color.BLACK
    }
}
