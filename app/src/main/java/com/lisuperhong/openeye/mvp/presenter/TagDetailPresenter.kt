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
        DataRepository.getInstance()
            .getTagInfo(id, object : BaseObserver<CategoryTabInfo>() {
                override fun onSuccess(data: CategoryTabInfo) {
                    rootView?.showTagInfo(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }

    override fun getVideos(id: Long) {
        DataRepository.getInstance()
            .getTagVideos(id, object : BaseObserver<BaseBean>() {
                override fun onSuccess(data: BaseBean) {
                    rootView?.hideLoading()
                    rootView?.showVideos(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }

    override fun loadMore(url: String) {
        DataRepository.getInstance()
            .loadMoreData(url, object : BaseObserver<BaseBean>() {
                override fun onSuccess(data: BaseBean) {
                    rootView?.hideLoading()
                    rootView?.showVideos(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.hideLoading()
                    rootView?.showError(errorMsg)
                }
            })
    }
}