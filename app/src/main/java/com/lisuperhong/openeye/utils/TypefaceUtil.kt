package com.lisuperhong.openeye.utils

import android.graphics.Typeface
import com.lisuperhong.openeye.BaseApplication

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 15:30
 * Desc: 字体工具
 */
object TypefaceUtil {

    const val Lobster = "Lobster-1.4"
    const val FZLanTingXiHei = "FZLanTingHeiS-L-GB-Regular"
    const val FZLanTingCuHei = "FZLanTingHeiS-DB1-GB-Regular"
    const val DCB = "DIN-Condensed-Bold"

    fun getTypefaceFromAsset(type: String): Typeface {
        return when (type) {
            Lobster -> Typeface.createFromAsset(
                BaseApplication.context.assets,
                "fonts/$Lobster.otf"
            )

            FZLanTingXiHei -> Typeface.createFromAsset(
                BaseApplication.context.assets,
                "fonts/$FZLanTingXiHei.TTF"
            )

            FZLanTingCuHei -> Typeface.createFromAsset(
                BaseApplication.context.assets,
                "fonts/$FZLanTingCuHei.TTF"
            )

            DCB -> Typeface.createFromAsset(
                BaseApplication.context.assets,
                "fonts/$DCB.ttf"
            )

            else -> Typeface.createFromAsset(
                BaseApplication.context.assets,
                "fonts/$Lobster.TTF"
            )
        }
    }
}