package com.lisuperhong.openeye.ui.fragment.rank

import android.os.Bundle
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/13 23:40
 * Github: https://github.com/lisuperhong
 * Desc: 排行榜tab
 */
class TabRankFragment : BaseFragment() {

    private var apiUrl: String? = null

    companion object {
        const val API_URL = "apiIUrl"

        fun newInstance(apiUrl: String): TabRankFragment {
            val fragment = TabRankFragment()
            val bundle = Bundle()
            bundle.putString(API_URL, apiUrl)
            fragment.arguments = bundle
            return fragment
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_tab_rank

    override fun initView() {

    }

    override fun initData() {
        apiUrl = arguments!!.getString(API_URL)
    }
}