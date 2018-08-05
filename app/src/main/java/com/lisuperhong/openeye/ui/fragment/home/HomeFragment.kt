package com.lisuperhong.openeye.ui.fragment.home

import android.graphics.Typeface
import com.lisuperhong.openeye.BaseApplication
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.base.BaseFragmentAdapter
import com.lisuperhong.openeye.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private val titles = listOf("推荐", "发现", "日报", "分类")
    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView() {
        StatusBarUtil.setPaddingSmart(getContext()!!, slidingTabLayout)
        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(RecommendFragment())
        fragmentList.add(DiscoveryFragment())
        fragmentList.add(DailyFragment())
        fragmentList.add(CategoryFragment())
        viewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragmentList, titles)
        slidingTabLayout.setViewPager(viewPager)

        // 设置tab字体(不起作用?)
        val fontType =
            Typeface.createFromAsset(
                BaseApplication.context.assets,
                "fonts/FZLanTingHeiS-L-GB-Regular.TTF"
            )
        (0 until titles.size)
            .forEach {
                slidingTabLayout.getTitleView(it).typeface = fontType
            }
    }

    override fun initData() {

    }

}