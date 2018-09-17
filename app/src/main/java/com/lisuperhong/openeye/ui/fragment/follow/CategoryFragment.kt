package com.lisuperhong.openeye.ui.fragment.follow

import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseFragment
import com.lisuperhong.openeye.mvp.contract.CategoryContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.SquareCard
import com.lisuperhong.openeye.mvp.presenter.CategoryPresenter
import com.lisuperhong.openeye.ui.adapter.CategoryAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/16 13:40
 * Github: https://github.com/lisuperhong
 * Desc: 全部分类
 */
class CategoryFragment : BaseFragment(), CategoryContract.View {

    private val presenter by lazy { CategoryPresenter() }
    private var categoryAdapter: CategoryAdapter? = null
    private var dataList: ArrayList<BaseBean.Item>? = null

    override val layoutId: Int
        get() = R.layout.fragment_category

    override fun initView() {
        presenter.attachView(this)

        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setRefreshHeader(ClassicsHeader(activity))
        refreshLayout.setOnClickListener {
            initData()
        }

        categoryAdapter = CategoryAdapter(getContext()!!, ArrayList<BaseBean.Item>())
        val layoutManager = GridLayoutManager(activity, 2)
        categoryRecyclerView.layoutManager = layoutManager
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val item = dataList!![position]
                if (item.type == "rectangleCard") {
                    return 2
                } else {
                    return 1
                }
            }
        }
        categoryRecyclerView.adapter = categoryAdapter
        showLoading()
    }

    override fun initData() {
        presenter.getAllCategory()
    }

    override fun showContent(baseBean: BaseBean) {
        refreshLayout.finishRefresh()
        if (baseBean.itemList.isEmpty())
            return
        dataList = baseBean.itemList
        categoryAdapter?.setRefreshData(baseBean.itemList)
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
}
