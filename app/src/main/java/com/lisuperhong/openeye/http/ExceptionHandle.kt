package com.lisuperhong.openeye.http

import android.net.ParseException

import com.google.gson.JsonParseException

import org.json.JSONException

import java.net.ConnectException
import java.util.concurrent.TimeoutException

import retrofit2.HttpException

class ExceptionHandle {

    /**
     * 约定异常
     */
    internal object ERROR {
        /**
         * 未知错误
         */
        val UNKNOWN = 1000
        /**
         * 解析错误
         */
        val PARSE_ERROR = 1001
        /**
         * 网络错误
         */
        val NETWORK_ERROR = 1002
        /**
         * 协议出错
         */
        val HTTP_ERROR = 1003

        /**
         * 证书出错
         */
        val SSL_ERROR = 1005

        /**
         * 连接超时
         */
        val TIMEOUT_ERROR = 1006
    }

    class ResponseThrowable(throwable: Throwable, var code: Int) : Throwable(throwable) {
        var msg: String? = null
    }

    companion object {

        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val BAD_GATEWAY = 502
        private const val SERVICE_UNAVAILABLE = 503
        private const val GATEWAY_TIMEOUT = 504

        fun handleException(e: Throwable): ResponseThrowable {
            val ex: ResponseThrowable
            if (e is HttpException) {
                ex = ResponseThrowable(e, ERROR.HTTP_ERROR)
                when (e.code()) {
                    UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.msg =
                            "网络错误"
                    else -> ex.msg = "网络错误"
                }
                return ex
            } else if (e is ApiException) {
                ex = ResponseThrowable(e, e.code)
                ex.msg = e.message
                return ex
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                ex = ResponseThrowable(e, ERROR.PARSE_ERROR)
                ex.msg = "解析错误"
                return ex
            } else if (e is ConnectException) {
                ex = ResponseThrowable(e, ERROR.NETWORK_ERROR)
                ex.msg = "连接失败"
                return ex
            } else if (e is javax.net.ssl.SSLHandshakeException) {
                ex = ResponseThrowable(e, ERROR.SSL_ERROR)
                ex.msg = "证书验证失败"
                return ex
            } else if (e is TimeoutException) {
                ex = ResponseThrowable(e, ERROR.TIMEOUT_ERROR)
                ex.msg = "连接超时"
                return ex
            } else if (e is java.net.SocketTimeoutException) {
                ex = ResponseThrowable(e, ERROR.TIMEOUT_ERROR)
                ex.msg = "连接超时"
                return ex
            } else {
                ex = ResponseThrowable(e, ERROR.UNKNOWN)
                ex.msg = "未知错误"
                return ex
            }
        }
    }
}
