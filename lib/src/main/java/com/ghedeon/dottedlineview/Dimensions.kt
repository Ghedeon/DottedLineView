package com.ghedeon.dottedlineview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet


fun Context.styledAttrs(attrs: AttributeSet, styleArray: IntArray, block: TypedArray.() -> Unit) =
    obtainStyledAttributes(attrs, styleArray).read(block)

fun TypedArray.read(block: TypedArray.() -> Unit) {
    try {
        block()
    } finally {
        this.recycle()
    }
}

fun TypedArray.dimen(key: Int, defaultValue: Float): Float {
    return getDimension(key, defaultValue)
}
