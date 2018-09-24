package com.lisuperhong.openeye.mvp.contract

import com.lisuperhong.openeye.base.IBasePresenter
import com.lisuperhong.openeye.base.IBaseView
import com.lisuperhong.openeye.mvp.model.bean.LightTopicBean

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 23:00
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface SpecialTopicDetailContract {

    interface View : IBaseView {

        fun showSpecialTopicDetail(lightTopicBean: LightTopicBean)
    }

    interface Presenter : IBasePresenter<View> {

        fun getSpecialTopicDetail(url: String)
    }
}