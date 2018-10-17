package com.lisuperhong.openeye.ui.activity

import android.graphics.Typeface
import android.os.Bundle
import com.lisuperhong.openeye.BaseApplication
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.base.BaseFragmentAdapter
import com.lisuperhong.openeye.mvp.contract.TabInfoContract
import com.lisuperhong.openeye.mvp.model.bean.TabInfoBean
import com.lisuperhong.openeye.mvp.presenter.TabInfoPresenter
import com.lisuperhong.openeye.ui.fragment.rank.TabRankFragment
import com.lisuperhong.openeye.utils.Constant
import kotlinx.android.synthetic.main.activity_hot_content.*

class HotContentActivity : BaseActivity(), TabInfoContract.View {

    private val presenter by lazy { TabInfoPresenter() }
    private val titleList = ArrayList<String>()
    private val fragmentList = ArrayList<BaseFragment>()

    override fun layoutId(): Int = R.layout.activity_hot_content

    override fun initView() {
        presenter.attachView(this)
        toolbarTitleTv.text = "热门内容"
        toolbarBackIv.setOnClickListener {
            onBackPressed()
        }

        presenter.getPopularTabInfo(intent.getStringExtra(Constant.INTENT_TAG_POPULAR_URL))
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun showTabInfo(tabInfoBean: TabInfoBean) {
        tabInfoBean.tabInfo.tabList.mapTo(titleList) { it.name }
        tabInfoBean.tabInfo.tabList.mapTo(fragmentList) { TabRankFragment.newInstance(it.apiUrl) }
        hotViewPager.adapter = BaseFragmentAdapter(supportFragmentManager, fragmentList, titleList)
        hotViewPager.offscreenPageLimit = tabInfoBean.tabInfo.tabList.size - 1
        slidingTabLayout.setViewPager(hotViewPager)
        slidingTabLayout.currentTab = tabInfoBean.tabInfo.defaultIdx

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

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(errorMsg: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}

