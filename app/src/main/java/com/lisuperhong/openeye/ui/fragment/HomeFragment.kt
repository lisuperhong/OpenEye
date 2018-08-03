package com.lisuperhong.openeye.ui.fragment

import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.ui.activity.MainActivity

class HomeFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView() {
        (baseActivity as MainActivity).hideToolbar()
    }

    override fun initData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        (baseActivity as MainActivity).hideToolbar()
        super.onHiddenChanged(hidden)
    }
}