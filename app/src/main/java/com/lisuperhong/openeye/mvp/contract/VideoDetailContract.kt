package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.VideoSmallCard

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/15 23:25
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface VideoDetailContract {

    interface View : IBaseView {

//        fun showVideoInfo(item: BaseBean.Item)
//
//        fun showBackground(url: String)

        fun showRelatedVideo(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {

        fun loadVideoInfo(videoSmallCard: VideoSmallCard)

        fun requestRelatedVideo(id: Long)
    }
}