package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.DiscoveryContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/11 17:36
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class DiscoveryPresenter : BasePresenter<DiscoveryContract.View>(), DiscoveryContract.Presenter {

    override fun discovery() {
        // 检测是否绑定 View
        checkViewAttached()
        val observer = object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.hideLoading()
                rootView?.showContent(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.showError(errorMsg)
                rootView?.hideLoading()
            }
        }
        addDispose(observer)
        DataRepository.getInstance().discovery(observer)
    }
}