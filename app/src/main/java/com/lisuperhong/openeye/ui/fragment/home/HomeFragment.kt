package com.lisuperhong.openeye.ui.fragment.home

import android.graphics.Typeface
import com.lisuperhong.openeye.BaseApplication
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.base.BaseFragmentAdapter
import com.lisuperhong.openeye.event.ChangeTabEvent
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HomeFragment : BaseFragment() {

    private val titles = listOf("发现", "推荐", "日报", "分类")
    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initView() {
        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(DiscoveryFragment())
        fragmentList.add(RecommendFragment())
        fragmentList.add(DailyFragment())
        fragmentList.add(CategoryFragment())
        viewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragmentList, titles)
        viewPager.offscreenPageLimit = 4
        slidingTabLayout.setViewPager(viewPager)
        slidingTabLayout.currentTab = 1

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

        EventBus.getDefault().register(this)
    }

    override fun initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun toCategoryFragment(changeTabEvent: ChangeTabEvent) {
        slidingTabLayout.setCurrentTab(changeTabEvent.tabIndex, true)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}