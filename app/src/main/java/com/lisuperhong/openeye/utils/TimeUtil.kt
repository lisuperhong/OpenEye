package com.lisuperhong.openeye.utils

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/6 23:19
 * Github: https://github.com/lisuperhong
 * Desc:
 */
object TimeUtil {

    fun secToTime(time: Int): String {
        val timeStr: String?
        var hour = 0
        var minute = 0
        var second = 0
        if (time <= 0)
            return "00:00"
        else {
            minute = time / 60
            if (minute < 60) {
                second = time % 60
                timeStr = unitFormat(minute) + ":" + unitFormat(second)
            } else {
                hour = minute / 60
                if (hour > 99)
                    return "99:59:59"
                minute = minute % 60
                second = time - hour * 3600 - minute * 60
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second)
            }
        }
        return timeStr
    }

    private fun unitFormat(i: Int): String {
        var retStr: String? = null
        retStr = if (i in 0..9)
            "0" + Integer.toString(i)
        else
            "" + i
        return retStr
    }
}