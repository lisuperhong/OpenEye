package com.lisuperhong.openeye.ui.fragment


import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.ui.activity.MainActivity


class RankFragment : BaseFragment() {

    private var mainActivity: MainActivity? = null

    override val layoutId: Int
        get() = R.layout.fragment_rank

    override fun initView() {
        mainActivity = context as MainActivity
        mainActivity?.showToolbar("排行榜")
    }

    override fun initData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        (context as MainActivity).showToolbar("排行榜")
        super.onHiddenChanged(hidden)
    }
}
