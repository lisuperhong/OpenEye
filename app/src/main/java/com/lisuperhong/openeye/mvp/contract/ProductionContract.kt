package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/16 00:32
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface ProductionContract {

    interface View : IBaseView {
        /**
         * 设置列表数据
         */
        fun showContent(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {

        fun communityFollow()

        /**
         * 加载更多数据
         */
        fun followLoadMore(url: String)
    }
}