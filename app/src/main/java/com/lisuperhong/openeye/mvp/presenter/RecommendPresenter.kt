package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.http.ApiService
import com.lisuperhong.openeye.http.RetrofitManager
import com.lisuperhong.openeye.mvp.contract.RecommendContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver
import com.lisuperhong.openeye.rx.scheduler.IoMainScheduler
import com.lisuperhong.openeye.utils.Constant

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/5 13:58
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class RecommendPresenter : BasePresenter<RecommendContract.View>(), RecommendContract.Presenter {

    override fun requestAllRec(page: Int) {
        // 检测是否绑定 View
        checkViewAttached()
        DataRepository.getInstance().allRec(page, object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.hideLoading()
                rootView?.showContent(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.showError(errorMsg)
                rootView?.hideLoading()
            }
        })
    }
}