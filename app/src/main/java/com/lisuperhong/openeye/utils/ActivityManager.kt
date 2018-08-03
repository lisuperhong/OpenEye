package com.lisuperhong.openeye.utils

import com.lisuperhong.openeye.base.BaseActivity
import java.util.*

object ActivityManager {

    private val activityStack = Stack<BaseActivity>()

    /**
     * 添加Activity到栈中
     */
    fun addActivity(baseActivity: BaseActivity) {
        activityStack.push(baseActivity)
    }

    /**
     * 移除指定的Activity
     */
    fun remove(baseActivity: BaseActivity) {
//        baseActivity.finish()
        activityStack.remove(baseActivity)
    }

    /**
     * 获取当前Activity
     */
    fun currentActivity(): BaseActivity {
        return activityStack.lastElement()
    }

    /**
     * 结束指定Activity
     */
    fun finishActivity(baseActivity: BaseActivity) {
        if (!baseActivity.isFinishing) {
            baseActivity.finish()
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishedAll() {
        for (item in activityStack) {
            finishActivity(item)
        }
        activityStack.clear()
    }

}
