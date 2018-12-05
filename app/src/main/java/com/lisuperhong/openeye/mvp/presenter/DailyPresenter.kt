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
 * Desc: 日报
 */
class DailyPresenter : BasePresenter<DailyContract.View>(), DailyContract.Presenter {

    override fun feed(date: Long, num: Int) {
        checkViewAttached()
        val observer = object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.hideLoading()
                rootView?.showContent(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.hideLoading()
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().feed(date, observer)
    }

    override fun feedLoadMore(url: String) {
        checkViewAttached()
        val observer = object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.showContent(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().loadMoreData(url, observer)
    }
}