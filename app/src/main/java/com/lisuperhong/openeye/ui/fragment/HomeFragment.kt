package com.lisuperhong.openeye.ui.fragment

import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.ui.activity.MainActivity
import com.lisuperhong.openeye.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView() {
        (context as MainActivity).hideToolbar()
        text.setPadding(0, StatusBarUtil.getStatusBarHeight(getContext()!!), 0, 0)
    }

    override fun initData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        (context as MainActivity).hideToolbar()
        super.onHiddenChanged(hidden)
    }
}