package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/14 23:30
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface TabRankContract {

    interface View : IBaseView {
        /**
         * 显示推荐数据
         */
        fun showContent(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {
        /**
         * 获取排行视频信息
         */
        fun requestRankList(apiUrl: String)
    }
}