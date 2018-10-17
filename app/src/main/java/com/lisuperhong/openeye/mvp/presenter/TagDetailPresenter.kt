package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.TagDetailContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 01:21
 * Github: https://github.com/lisuperhong
 * Desc: 标签详情
 */
class TagDetailPresenter : BasePresenter<TagDetailContract.View>(), TagDetailContract.Presenter {

    override fun getTagInfo(id: Long) {
        checkViewAttached()
        val observer = object : BaseObserver<CategoryTabInfo>() {
            override fun onSuccess(data: CategoryTabInfo) {
                rootView?.showTagInfo(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.hideLoading()
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().getTagInfo(id, observer)
    }

    override fun getVideos(id: Long) {
        val observer = object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.hideLoading()
                rootView?.showVideos(data)
            }

            override fun onFailure(errorMsg: String) {
                rootView?.hideLoading()
                rootView?.showError(errorMsg)
            }
        }
        addDispose(observer)
        DataRepository.getInstance().getTagVideos(id, observer)
    }

    override fun loadMore(url: String) {
        val observer = object : BaseObserver<BaseBean>() {
            override fun onSuccess(data: BaseBean) {
                rootView?.hideLoading()
                rootView?.showVideos(data)
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