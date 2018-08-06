package com.lisuperhong.openeye.ui.fragment.home

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager

import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.mvp.contract.RecommendContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.presenter.RecommendPresenter
import com.lisuperhong.openeye.ui.adapter.MultiItemAdapter
import kotlinx.android.synthetic.main.fragment_recommend.*

/**
 * A simple [Fragment] subclass.
 *
 */
class RecommendFragment : BaseFragment(), RecommendContract.View {

    private val presenter by lazy { RecommendPresenter() }

    private var multiItemAdapter: MultiItemAdapter? = null

    private var num = 0

    override val layoutId: Int
        get() = R.layout.fragment_recommend

    override fun initView() {
        presenter.attachView(this)
    }

    override fun initData() {
        presenter.requestAllRec(num)
    }

    override fun showContent(baseBean: BaseBean) {
        multiItemAdapter = MultiItemAdapter(getContext()!!, baseBean.itemList)
        recommendRecycleView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recommendRecycleView.adapter = multiItemAdapter
    }

    override fun setLoadmore(baseBean: BaseBean) {

    }

    override fun showError(errorMsg: String) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
