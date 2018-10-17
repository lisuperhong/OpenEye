package com.lisuperhong.openeye.ui.fragment.rank

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.mvp.contract.TabRankContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.presenter.TabRankPresenter
import com.lisuperhong.openeye.ui.adapter.MultiItemAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.fragment_tab_rank.*

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/13 23:40
 * Github: https://github.com/lisuperhong
 * Desc: 排行榜各tab
 */
class TabRankFragment : BaseFragment(), TabRankContract.View {

    private var apiUrl = ""
    private val presenter by lazy { TabRankPresenter() }
    private var multiItemAdapter: MultiItemAdapter? = null

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
        presenter.attachView(this)
        apiUrl = arguments!!.getString(API_URL)
        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setRefreshHeader(ClassicsHeader(activity))
        refreshLayout.setOnRefreshListener {
            initData()
        }

        multiItemAdapter = MultiItemAdapter(getContext()!!, ArrayList<BaseBean.Item>())
        rankRecycleView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rankRecycleView.adapter = multiItemAdapter
        showLoading()
    }

    override fun initData() {
        presenter.requestRankList(apiUrl)
    }

    override fun showContent(baseBean: BaseBean) {
        refreshLayout.finishRefresh()
        if (baseBean.itemList.isEmpty())
            return
        multiItemAdapter?.setRefreshData(baseBean.itemList)
    }

    override fun showLoading() {
        loadingProgressBar.show()
    }

    override fun hideLoading() {
        loadingProgressBar.hide()
    }

    override fun showError(errorMsg: String) {
        refreshLayout.finishRefresh()
        Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}