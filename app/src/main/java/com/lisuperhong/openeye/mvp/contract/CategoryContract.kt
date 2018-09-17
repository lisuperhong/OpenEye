package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.BaseBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/17 10:22
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface CategoryContract {

    interface View : IBaseView {

        fun showContent(baseBean: BaseBean)
    }

    interface Presenter : IBasePresenter<View> {

        fun getAllCategory()
    }
}