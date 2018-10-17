package com.lisuperhong.openeye.ui.fragment.follow

import android.graphics.Typeface
import com.lisuperhong.openeye.BaseApplication
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.base.BaseFragmentAdapter
import kotlinx.android.synthetic.main.fragment_follow.*

class FollowFragment : BaseFragment() {

    private val titleList = listOf("作者", "作品")

    override val layoutId: Int
        get() = R.layout.fragment_follow

    override fun initView() {
        titleTv.text = "关注"
        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(AuthorFragment())
        fragmentList.add(ProductionFragment())
        followViewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragmentList, titleList)
        followViewPager.offscreenPageLimit = 2
        slidingTabLayout.setViewPager(followViewPager)
        slidingTabLayout.currentTab = 0

        // 设置tab字体(不起作用?)
        val fontType =
            Typeface.createFromAsset(
                BaseApplication.context.assets,
                "fonts/FZLanTingHeiS-L-GB-Regular.TTF"
            )
        (0 until titleList.size)
            .forEach {
                slidingTabLayout.getTitleView(it).typeface = fontType
            }
    }

    override fun initData() {

    }
}
