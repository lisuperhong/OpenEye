package com.lisuperhong.openeye.ui.fragment


import android.support.v4.app.Fragment

import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.ui.activity.MainActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class MyFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_my

    override fun initView() {
        (context as MainActivity).hideToolbar()
    }

    override fun initData() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        (context as MainActivity).hideToolbar()
        super.onHiddenChanged(hidden)
    }
}
