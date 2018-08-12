package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/11 17:36
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface DiscoveryContract {

    interface View : IBaseView {
        /**
         * 显示推荐数据
         */
        fun showContent(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<DiscoveryContract.View> {

        /**
         * 获取数据
         */
        fun discovery()
    }
}