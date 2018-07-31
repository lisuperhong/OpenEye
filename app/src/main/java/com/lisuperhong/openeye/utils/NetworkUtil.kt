package com.lisuperhong.openeye.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil {

    companion object {

        /**
         * 检查网络是否可用
         *
         * @param context
         * @return
         */
        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}