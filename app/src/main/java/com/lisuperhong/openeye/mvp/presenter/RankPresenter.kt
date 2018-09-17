package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.RankContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.TabInfoBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/14 23:04
 * Github: https://github.com/lisuperhong
 * Desc: 排行榜
 */
class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter {

    override fun getTabInfo() {
        checkViewAttached()
        DataRepository.getInstance()
            .getRankList(object : BaseObserver<TabInfoBean>() {
                override fun onSuccess(data: TabInfoBean) {
                    rootView?.showTabInfo(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.showError(errorMsg)
                }
            })
    }
}