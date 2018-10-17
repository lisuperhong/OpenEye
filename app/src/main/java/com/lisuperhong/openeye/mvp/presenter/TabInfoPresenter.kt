package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.TabInfoContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.TabInfoBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/14 23:04
 * Github: https://github.com/lisuperhong
 * Desc: Tab信息
 */
class TabInfoPresenter : BasePresenter<TabInfoContract.View>(), TabInfoContract.Presenter {

    override fun getRankTabInfo() {
        checkViewAttached()
        val observer = object : BaseObserver<TabInfoBean>() {
            override fun onSuccess(data: TabInfoBean) {
                rootView?.showTabInfo(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().getRankList(observer)
    }

    override fun getPopularTabInfo(url: String) {
        checkViewAttached()
        val observer = object : BaseObserver<TabInfoBean>() {
            override fun onSuccess(data: TabInfoBean) {
                rootView?.showTabInfo(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().getPopularTabInfo(url, observer)
    }
}