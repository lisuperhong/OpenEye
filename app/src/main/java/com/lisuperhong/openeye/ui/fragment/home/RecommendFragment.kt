package com.lisuperhong.openeye.ui.fragment.home

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.lisuperhong.openeye.BaseApplication

import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.mvp.contract.RecommendContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.presenter.RecommendPresenter
import com.lisuperhong.openeye.ui.adapter.MultiItemAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.fragment_recommend.*

/**
 * A simple [Fragment] subclass.
 *
 */
class RecommendFragment : BaseFragment(), RecommendContract.View {

    private val presenter by lazy { RecommendPresenter() }

    private var multiItemAdapter: MultiItemAdapter? = null

    private var page = 0

    private var isRefresh = false

    override val layoutId: Int
        get() = R.layout.fragment_recommend

    override fun initView() {
        presenter.attachView(this)
        refreshLayout.setRefreshHeader(ClassicsHeader(activity))
        refreshLayout.setEnableAutoLoadMore(true)
        refreshLayout.setOnRefreshListener {
            page = 0
            isRefresh = true
            initData()
        }

        refreshLayout.setOnLoadMoreListener {
            page++
            isRefresh = false
            initData()
        }

        multiItemAdapter = MultiItemAdapter(getContext()!!, ArrayList<BaseBean.Item>())
        recommendRecycleView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recommendRecycleView.adapter = multiItemAdapter
        recommendRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //0 表示停止滑动的状态 SCROLL_STATE_IDLE
                //1表示正在滚动，用户手指在屏幕上 SCROLL_STATE_TOUCH_SCROLL
                //2表示正在滑动。用户手指已经离开屏幕 SCROLL_STATE_FLING
                when (newState) {
                    0 -> {
                        Glide.with(BaseApplication.context).resumeRequests()
                    }
                    1 -> {
                        Glide.with(BaseApplication.context).resumeRequests()
                    }
                    2 -> {
                        Glide.with(BaseApplication.context).pauseRequests()
                    }
                }
            }
        })
        isRefresh = false
        showLoading()
    }

    override fun initData() {
        presenter.requestAllRec(page)
    }

    override fun showContent(baseBean: BaseBean) {
        stopRefresh()
        if (baseBean.itemList.isEmpty())
            return
        if (isRefresh) {
            multiItemAdapter?.setRefreshData(baseBean.itemList)
        } else{
            multiItemAdapter?.setLoadMore(baseBean.itemList)
        }
    }

    override fun showError(errorMsg: String) {
        stopRefresh()
        Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        loadingProgressBar.show()
    }

    override fun hideLoading() {
        loadingProgressBar.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun stopRefresh() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }
}
