package com.lisuperhong.openeye.ui.fragment

import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_rank.*

class AttentionFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_rank

    override fun initView() {
        StatusBarUtil.setPaddingSmart(getContext()!!, toolbar)
        titleTv.text = "关注"
    }

    override fun initData() {

    }
}
