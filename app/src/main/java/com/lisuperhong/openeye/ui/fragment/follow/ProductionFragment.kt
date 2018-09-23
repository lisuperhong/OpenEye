package com.lisuperhong.openeye.ui.fragment.follow

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.mvp.contract.ProductionContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.presenter.ProductionPresenter
import com.lisuperhong.openeye.ui.adapter.MultiItemAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.fragment_production.*
import com.shuyu.gsyvideoplayer.GSYVideoManager
import android.support.v7.widget.RecyclerView
import com.lisuperhong.openeye.ui.adapter.AutoPlayListAdapter
import com.lisuperhong.openeye.utils.DensityUtil
import com.lisuperhong.openeye.utils.ScrollCalculatorHelper

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/16 00:15
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class ProductionFragment : BaseFragment(), ProductionContract.View {

    private val presenter by lazy { ProductionPresenter() }
    private var autoPlayListAdapter: AutoPlayListAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var isRefresh = false
    private var isFull = false
    private var nextPageUrl: String? = null

    private var scrollCalculatorHelper: ScrollCalculatorHelper? = null

    override val layoutId: Int
        get() = R.layout.fragment_production

    override fun initView() {
        presenter.attachView(this)

        refreshLayout.setRefreshHeader(ClassicsHeader(activity))
        refreshLayout.setEnableAutoLoadMore(true)
        refreshLayout.setOnRefreshListener {
            isRefresh = true
            initData()
        }

        refreshLayout.setOnLoadMoreListener {
            isRefresh = false
            if (nextPageUrl != null) {
                presenter.followLoadMore(nextPageUrl!!)
            } else {
                refreshLayout.setEnableLoadMore(false)
            }
        }

        autoPlayListAdapter = AutoPlayListAdapter(getContext()!!, ArrayList<BaseBean.Item>())
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        autoPlayRecycleView.layoutManager = linearLayoutManager
        autoPlayRecycleView.adapter = autoPlayListAdapter
        isRefresh = false
        showLoading()

        val playTop = DensityUtil.getScreenHeight(getContext()!!) / 2 - DensityUtil.dip2px(
            getContext()!!,
            180f
        )
        val playBottom = DensityUtil.getScreenHeight(getContext()!!) / 2 + DensityUtil.dip2px(
            getContext()!!,
            180f
        )
        scrollCalculatorHelper = ScrollCalculatorHelper(R.id.autoPlayer, playTop, playBottom)
        autoPlayRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var firstVisibleItem: Int = 0
            var lastVisibleItem: Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                scrollCalculatorHelper!!.onScrollStateChanged(recyclerView!!, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
                lastVisibleItem = linearLayoutManager!!.findLastVisibleItemPosition()
                //这是滑动自动播放的代码
                if (!isFull) {
                    scrollCalculatorHelper!!.onScroll(
                        firstVisibleItem,
                        lastVisibleItem,
                        lastVisibleItem - firstVisibleItem
                    )
                }
            }
        })

    }

    override fun initData() {
        presenter.communityFollow()
    }

    override fun showContent(baseBean: BaseBean) {
        stopRefresh()
        if (baseBean.itemList.isEmpty())
            return
        nextPageUrl = baseBean.nextPageUrl
        if (isRefresh) {
            autoPlayListAdapter?.setRefreshData(baseBean.itemList)
        } else {
            autoPlayListAdapter?.setLoadMore(baseBean.itemList)
        }
    }

    override fun showLoading() {
        loadingProgressBar.show()
    }

    override fun hideLoading() {
        loadingProgressBar.hide()
    }

    override fun showError(errorMsg: String) {
        stopRefresh()
        Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
    }

    private fun stopRefresh() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        //如果旋转了就全屏
        isFull = newConfig!!.orientation == ActivityInfo.SCREEN_ORIENTATION_USER
    }

    fun onBackPressed(): Boolean {
        return GSYVideoManager.backFromWindowFull(activity)
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        GSYVideoManager.releaseAllVideos()
    }

}