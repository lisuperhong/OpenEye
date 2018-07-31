package com.lisuperhong.openeye.http

class BaseResponse<T>(val code: Int, val message: String, val content: T) {

    fun isisSuccess(): Boolean = code == 0
}