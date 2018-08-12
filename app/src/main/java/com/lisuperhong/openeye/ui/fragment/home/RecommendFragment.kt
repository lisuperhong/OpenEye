package com.lisuperhong.openeye.ui.fragment.home

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

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
        refreshLayout.autoLoadMore()
        refreshLayout.setRefreshHeader(ClassicsHeader(activity))
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
        isRefresh = false
        showLoading()
    }

    override fun initData() {
        presenter.requestAllRec(page)
    }

    override fun showContent(baseBean: BaseBean) {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
        if (baseBean.itemList.isEmpty())
            return
        if (isRefresh) {
            multiItemAdapter?.setRefreshData(baseBean.itemList)
        } else{
            multiItemAdapter?.setLoadMore(baseBean.itemList)
        }
    }

    override fun showError(errorMsg: String) {
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
}
