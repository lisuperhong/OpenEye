package com.lisuperhong.openeye.utils

import com.lisuperhong.openeye.base.BaseActivity

import java.util.ArrayList

object ActivityManager {

    private val baseActivityList = ArrayList<BaseActivity>()

    fun addActivity(baseActivity: BaseActivity) {
        baseActivityList.add(baseActivity)
    }

    fun remove(baseActivity: BaseActivity) {
        baseActivityList.remove(baseActivity)
    }

    fun finishedAll() {
        if (baseActivityList.size > 0) {
            baseActivityList.clear()
        }
    }
}
