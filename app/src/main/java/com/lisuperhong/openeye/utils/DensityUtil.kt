package com.lisuperhong.openeye.utils

import android.content.Context

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/6 23:03
 * Github: https://github.com/lisuperhong
 * Desc:
 */
object DensityUtil {

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}