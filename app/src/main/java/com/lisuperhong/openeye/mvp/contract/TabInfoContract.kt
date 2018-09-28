package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.TabInfoBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/14 23:00
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface TabInfoContract {

    interface View : IBaseView {
        /**
         * 显示tab信息
         */
        fun showTabInfo(tabInfoBean: TabInfoBean)
    }

    interface Presenter : IBasePresenter<View> {
        /**
         * 获取排行榜tab信息
         */
        fun getRankTabInfo()

        /**
         * 获取tag热门内容tab信息
         */
        fun getPopularTabInfo(url: String)
    }
}