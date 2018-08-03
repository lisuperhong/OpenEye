package com.lisuperhong.openeye.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.jaeger.library.StatusBarUtil
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.mvp.model.bean.TabEntity
import com.lisuperhong.openeye.ui.fragment.AttentionFragment
import com.lisuperhong.openeye.ui.fragment.HomeFragment
import com.lisuperhong.openeye.ui.fragment.MyFragment
import com.lisuperhong.openeye.ui.fragment.RankFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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
    private var exitTime: Long = 0
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
        tabLayout.currentTab = curIndex
        setCurrentFragment(curIndex)
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    private fun initTab() {
        (0 until tabTitles.size)
            .mapTo(tabEntities) { TabEntity(tabTitles[it], selectedIcons[it], unSelectedIcons[it]) }
        tabLayout.setTabData(tabEntities)
        tabLayout.setOnTabSelectListener(object : OnTabSelectListener {
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
        tabLayout.currentTab = index
        transaction.commitAllowingStateLoss()
    }

    private fun hideAllFragment(transaction: FragmentTransaction) {
        homeFragment?.let { transaction.hide(it) }
        rankFragment?.let { transaction.hide(it) }
        attentionFragment?.let { transaction.hide(it) }
        myFragment?.let { transaction.hide(it) }
    }

    fun hideToolbar() {
        toolbar.visibility = View.GONE
    }

    fun showToolbar(title: String) {
        toolbar.visibility = View.VISIBLE
        titleTv.text = title
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置，防止崩溃时activity被系统回收，再加载导致fragment错乱
        outState.putInt("curTabIndex", curIndex)
        super.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(exitTime) <= 2000) {
                finish()
            } else {
                exitTime = System.currentTimeMillis()
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
