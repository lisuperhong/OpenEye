package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.SpecialTopicDetailContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.LightTopicBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 23:02
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class SpecialTopicDetailPresenter : BasePresenter<SpecialTopicDetailContract.View>(),
    SpecialTopicDetailContract.Presenter {

    override fun getSpecialTopicDetail(url: String) {
        checkViewAttached()
        DataRepository.getInstance()
            .getSpecialTopicDetail(url, object : BaseObserver<LightTopicBean>() {
                override fun onSuccess(data: LightTopicBean) {
                    rootView?.hideLoading()
                    rootView?.showSpecialTopicDetail(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }
}