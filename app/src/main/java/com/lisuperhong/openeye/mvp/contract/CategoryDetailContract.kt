package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/20 22:49
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface CategoryDetailContract {

    interface View : IBaseView {

        fun showCategoryInfo(categoryTabInfo: CategoryTabInfo)

        fun showVideoList(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {

        fun getCategoryInfo(id: Long)

        fun getVideoList(id: Long)

        fun loadMore(url: String)
    }
}