package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.CategoryDetailContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/20 22:54
 * Github: https://github.com/lisuperhong
 * Desc: 分类详情
 */
class CategoryDetailPresenter : BasePresenter<CategoryDetailContract.View>(),
    CategoryDetailContract.Presenter {

    override fun getCategoryInfo(id: Long) {
        checkViewAttached()
        val observer = object : BaseObserver<CategoryTabInfo>() {
            override fun onSuccess(data: CategoryTabInfo) {
                rootView?.showCategoryInfo(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.hideLoading()
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().getCategoryInfo(id, observer)
    }

    override fun getVideoList(id: Long) {
        val observer = object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.hideLoading()
                rootView?.showVideoList(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.hideLoading()
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().getCategoryVideoList(id, observer)
    }

    override fun loadMore(url: String) {
        val observer = object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.hideLoading()
                rootView?.showVideoList(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.hideLoading()
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().loadMoreData(url, observer)
    }
}