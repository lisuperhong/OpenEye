package com.lisuperhong.openeye.base

interface IBasePresenter<in V: IBaseView> {

    /**
     * 绑定view
     */
    fun attachView(view: V)

    /**
     * 解绑view
     */
    fun detachView()
}