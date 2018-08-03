package com.lisuperhong.openeye.ui.fragment


import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.ui.activity.MainActivity


class RankFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_rank

    override fun initView() {
        (baseActivity as MainActivity).showToolbar("排行榜")
    }

    override fun initData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        (baseActivity as MainActivity).showToolbar("排行榜")
        super.onHiddenChanged(hidden)
    }
}
