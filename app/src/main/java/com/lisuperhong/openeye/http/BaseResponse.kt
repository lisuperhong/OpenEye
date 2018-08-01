package com.lisuperhong.openeye.http

class BaseResponse<T>(val code: Int, val message: String, val content: T) {

    val isSuccess: Boolean get() = code == 0
}