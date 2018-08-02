package com.lisuperhong.openeye.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.Toolbar
import butterknife.BindView
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.mvp.model.bean.TabEntity
import com.lisuperhong.openeye.ui.fragment.AttentionFragment
import com.lisuperhong.openeye.ui.fragment.HomeFragment
import com.lisuperhong.openeye.ui.fragment.MyFragment
import com.lisuperhong.openeye.ui.fragment.RankFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

//    @BindView(R.id.toolbar)
//    lateinit var toolbar: Toolbar
//    @BindView(R.id.tab_layout)
//    var tabLayout: CommonTabLayout? = null

    private val tabTitles = listOf("首页", "排行", "关注", "我的")
    // 选中图标
    private val selectedIcons = intArrayOf(
        R.drawable.ic_tab_strip_icon_feed_selected,
        R.drawable.ic_tab_strip_icon_rank_selected,
        R.drawable.ic_tab_strip_icon_follow_selected,
        R.drawable.ic_tab_strip_icon_profile_selected
    )
    // 未选中图标
    private val unSelectedIcons = intArrayOf(
        R.drawable.ic_tab_strip_icon_feed,
        R.drawable.ic_tab_strip_icon_rank,
        R.drawable.ic_tab_strip_icon_follow,
        R.drawable.ic_tab_strip_icon_profile
    )

    private val tabEntities = ArrayList<CustomTabEntity>()
    private var curIndex = 0
    private var homeFragment: HomeFragment? = null
    private var rankFragment: RankFragment? = null
    private var attentionFragment: AttentionFragment? = null
    private var myFragment: MyFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = curIndex
        setCurrentFragment(curIndex)
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData(savedInstanceState: Bundle?) {
//        if (savedInstanceState != null) {
//            curIndex = savedInstanceState.getInt("curTabIndex")
//        }
//        tab_layout.currentTab = curIndex
//        initTab()
//        setCurrentFragment(curIndex)
    }

    private fun initTab() {
        (0 until tabTitles.size)
            .mapTo(tabEntities) { TabEntity(tabTitles[it], selectedIcons[it], unSelectedIcons[it]) }
        tab_layout.setTabData(tabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                setCurrentFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    private fun setCurrentFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideAllFragment(transaction)
        when (index) {
            0 -> homeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment().let {
                homeFragment = it
                transaction.add(R.id.fragment_container, homeFragment, "home")
            }

            1 -> rankFragment?.let {
                transaction.show(it)
            } ?: RankFragment().let {
                rankFragment = it
                transaction.add(R.id.fragment_container, rankFragment, "rank")
            }

            2 -> attentionFragment?.let {
                transaction.show(it)
            } ?: AttentionFragment().let {
                attentionFragment = it
                transaction.add(R.id.fragment_container, attentionFragment, "attention")
            }

            3 -> myFragment?.let {
                transaction.show(it)
            } ?: MyFragment().let {
                myFragment = it
                transaction.add(R.id.fragment_container, myFragment, "my")
            }

            else -> {

            }
        }

        curIndex = index
        tab_layout.currentTab = index
        transaction.commitAllowingStateLoss()
    }

    private fun hideAllFragment(transaction: FragmentTransaction) {
        homeFragment?.let { transaction.hide(it) }
        rankFragment?.let { transaction.hide(it) }
        attentionFragment?.let { transaction.hide(it) }
        myFragment?.let { transaction.hide(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("curTabIndex", curIndex)
        }
        super.onSaveInstanceState(outState)
    }
}
