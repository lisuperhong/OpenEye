package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.VideoDetailContract
import com.lisuperhong.openeye.mvp.model.DataRepository
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.VideoSmallCard
import com.lisuperhong.openeye.rx.scheduler.BaseObserver

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/15 23:25
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class VideoDetailPresenter : BasePresenter<VideoDetailContract.View>(),
    VideoDetailContract.Presenter {

    override fun loadVideoInfo(videoSmallCard: VideoSmallCard) {

    }

    override fun requestRelatedVideo(id: Long) {
        checkViewAttached()
        DataRepository.getInstance()
            .videoRelated(id, object : BaseObserver<BaseBean>() {
                override fun onSuccess(data: BaseBean) {
                    rootView?.showRelatedVideo(data)
                }

                override fun onFailure(errorMsg: String) {
                    rootView?.showError(errorMsg)
                }
            })
    }
}