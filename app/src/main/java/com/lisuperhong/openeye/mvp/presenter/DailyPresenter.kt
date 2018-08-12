package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.DailyContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/12 14:06
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class DailyPresenter : BasePresenter<DailyContract.View>(), DailyContract.Presenter {

    override fun feed(date: Long, num: Int) {
        checkViewAttached()
        DataRepository.getInstance()
            .feed(date, object : BaseObserver<BaseBean>() {
                override fun onSuccess(data: BaseBean) {
                    rootView?.hideLoading()
                    rootView?.showContent(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }

    override fun feedLoadMore(url: String) {
        checkViewAttached()
        DataRepository.getInstance()
            .feedLoadMore(url, object : BaseObserver<BaseBean>() {
                override fun onSuccess(data: BaseBean) {
                    rootView?.hideLoading()
                    rootView?.showContent(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }
}