package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.TabRankContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/14 23:40
 * Github: https://github.com/lisuperhong
 * Desc: 排行榜
 */
class TabRankPresenter : BasePresenter<TabRankContract.View>(), TabRankContract.Presenter {

    override fun requestRankList(apiUrl: String) {
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
        DataRepository.instance.loadMoreData(apiUrl, observer)
    }
}