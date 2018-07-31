package com.lisuperhong.openeye.http

class ApiException(val code: Int, message: String) : Throwable(message)
