package com.lisuperhong.openeye.ui.fragment


import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_rank.*

class RankFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_rank

    override fun initView() {
        StatusBarUtil.setPaddingSmart(getContext()!!, toolbar)
    }

    override fun initData() {

    }
}
