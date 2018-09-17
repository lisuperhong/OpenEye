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
interface RankContract {

    interface View : IBaseView {
        /**
         * 显示tab信息
         */
        fun showTabInfo(tabInfoBean: TabInfoBean)
    }

    interface Presenter : IBasePresenter<View> {
        /**
         * 获取tab信息
         */
        fun getTabInfo()
    }
}