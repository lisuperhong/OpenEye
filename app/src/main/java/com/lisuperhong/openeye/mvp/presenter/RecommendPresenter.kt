package com.lisuperhong.openeye.mvp.presenter

import com.lisuperhong.openeye.base.BasePresenter
import com.lisuperhong.openeye.mvp.contract.RecommendContract

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/5 13:58
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class RecommendPresenter : BasePresenter<RecommendContract.View>(), RecommendContract.Presenter {

    override fun requestAllRec(page: Int) {

    }
}