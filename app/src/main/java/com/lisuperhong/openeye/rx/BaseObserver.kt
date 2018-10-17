package com.lisuperhong.openeye.rx.scheduler

import com.lisuperhong.openeye.http.ExceptionHandle
import com.orhanobut.logger.Logger
import io.reactivex.observers.DisposableObserver

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/1 15:13
 * Desc: 观察者基类
 */
abstract class BaseObserver<T> : DisposableObserver<T>() {

    override fun onNext(data: T) {
        Logger.d("BaseObserver onNext: " + data.toString())
        onSuccess(data)
    }

    override fun onComplete() {
        Logger.d("BaseObserver onComplete")
    }

    override fun onError(e: Throwable) {
        Logger.d("BaseObserver onError")
        onFailure(ExceptionHandle.handleException(e).msg ?: "未知错误")
    }

    abstract fun onSuccess(data: T)
    abstract fun onFailure(errorMsg: String)
}