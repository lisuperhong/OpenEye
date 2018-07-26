package com.lisuperhong.openeye.base

import android.app.Activity

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BasePresenter<T : IBaseView> : IBasePresenter<T> {

    private var rootView: T? = null
    private var compositeDisposable: CompositeDisposable? = null

    /**
     * 检查View是否存在
     */
    private val isViewAttached: Boolean
        get() = rootView != null

    override fun attachView(view: T) {
        rootView = view
    }

    override fun detachView() {
        rootView = null
        removeDispose()
    }

    /**
     * 将 [Disposable] 添加到 [CompositeDisposable] 中统一管理
     * 可在 [Activity.onDestroy] 中使用 [.removeDispose] 停止正在执行的 RxJava 任务，避免内存泄漏(框架已自行处理)
     *
     * @param disposable
     */
    fun addDispose(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable!!.add(disposable)
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    private fun removeDispose() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
        }
    }

    /**
     * 检查是否已经绑定view，没绑定统一抛出异常
     */
    fun checkViewAttached() {
        if (!isViewAttached)
            throw MvpViewNotAttachedException()
    }


    private class MvpViewNotAttachedException internal constructor()
        : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}
