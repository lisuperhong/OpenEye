package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.SpecialTopicsContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 18:14
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class SpecialTopicsPresenter : BasePresenter<SpecialTopicsContract.View>(),
    SpecialTopicsContract.Presenter {

    override fun getSpecialTopics() {
        checkViewAttached()
        DataRepository.getInstance()
            .getSpecialTopics(object : BaseObserver<BaseBean>() {
                override fun onSuccess(data: BaseBean) {
                    rootView?.hideLoading()
                    rootView?.showSpecialTopics(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }
}