package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 18:11
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface SpecialTopicsContract {

    interface View : IBaseView {

        fun showSpecialTopics(bean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {

        fun getSpecialTopics()
    }
}