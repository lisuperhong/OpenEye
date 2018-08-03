package com.lisuperhong.openeye.ui.fragment

import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.ui.activity.MainActivity


class AttentionFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_attention

    override fun initView() {
        (baseActivity as MainActivity).showToolbar("关注")
    }

    override fun initData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        (baseActivity as MainActivity).showToolbar("关注")
        super.onHiddenChanged(hidden)
    }
}
