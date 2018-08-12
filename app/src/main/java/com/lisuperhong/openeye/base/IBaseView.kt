package com.lisuperhong.openeye.base

interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(errorMsg: String)
}