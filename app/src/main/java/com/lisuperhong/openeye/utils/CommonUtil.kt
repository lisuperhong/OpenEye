package com.lisuperhong.openeye.utils

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.lisuperhong.openeye.BaseApplication

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/1 17:26
 * Desc: 通用工具类
 */
object CommonUtil {

    /**
     * 获取设备型号
     */
    fun getDeviceModel(): String {
        return android.os.Build.MODEL
    }

    fun getSystemVersion(): Int {
        return android.os.Build.VERSION.SDK_INT
    }

    fun getVersionCode(): Int {
        return getPackageInfo().versionCode
    }

    fun getVersionName(): String {
        return getPackageInfo().versionName
    }

    private fun getPackageInfo(): PackageInfo {
        var packageInfo: PackageInfo? = null
        try {
            val packageManager = BaseApplication.context.packageManager
            packageInfo = packageManager.getPackageInfo(
                BaseApplication.context.packageName,
                PackageManager.GET_CONFIGURATIONS
            )
            return packageInfo
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return packageInfo!!
    }
}