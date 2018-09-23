package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 01:18
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface TagDetailContract {

    interface View : IBaseView {

        fun showTagInfo(categoryTabInfo: CategoryTabInfo)

        fun showVideos(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {

        fun getTagInfo(id: Long)

        fun getVideos(id: Long)

        fun loadMore(url: String)
    }
}