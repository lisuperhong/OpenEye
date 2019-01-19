package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/5 13:43
 * Github: https://github.com/lisuperhong
 * Desc: RecommendFragment页面契约类
 */
interface RecommendContract {

    interface View : IBaseView {

        /**
         * 显示推荐数据
         */
        fun showContent(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {

        /**
         * 获取推荐接口数据
         */
        fun requestAllRec(page: Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData(url: String)
    }
}