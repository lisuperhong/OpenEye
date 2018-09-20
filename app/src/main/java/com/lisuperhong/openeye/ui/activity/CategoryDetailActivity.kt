package com.lisuperhong.openeye.ui.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.mvp.contract.CategoryDetailContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo
import com.lisuperhong.openeye.mvp.presenter.CategoryDetailPresenter
import com.lisuperhong.openeye.ui.adapter.MultiItemAdapter
import com.lisuperhong.openeye.utils.Constant
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.TypefaceUtil
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_category_detail.*

class CategoryDetailActivity : BaseActivity(), CategoryDetailContract.View {

    private val presenter by lazy { CategoryDetailPresenter() }
    private var categoryName: String? = null
    private var categoryId: Long = 0
    private var multiItemAdapter: MultiItemAdapter? = null
    private var isRefresh = false
    private var nextPageUrl = ""

    override fun layoutId(): Int = R.layout.activity_category_detail

    override fun initView() {
        presenter.attachView(this)
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (verticalOffset == 0) { // 展开状态
                    toolbar.setNavigationIcon(R.drawable.ic_action_back_white)
                    toolbarTitleTv.text = ""
                } else if (Math.abs(verticalOffset) >= appBarLayout!!.totalScrollRange) { // 收缩状态
                    toolbar.setNavigationIcon(R.drawable.ic_action_back)
                    toolbarTitleTv.text = categoryName
                } else {
                    toolbar.setNavigationIcon(R.drawable.ic_action_back_white)
                    toolbarTitleTv.text = ""
                }
            }
        })

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        refreshLayout.setRefreshHeader(ClassicsHeader(this))
        refreshLayout.setOnRefreshListener {
            isRefresh = true
            presenter.getVideoList(categoryId)
        }

        refreshLayout.setOnLoadMoreListener {
            isRefresh = false
            presenter.loadMore(nextPageUrl)
        }

        multiItemAdapter = MultiItemAdapter(this, ArrayList<BaseBean.Item>())
        categoryDetailRecycleView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        categoryDetailRecycleView.adapter = multiItemAdapter
        isRefresh = false
        showLoading()
    }

    override fun initData(savedInstanceState: Bundle?) {
        categoryId = intent.getLongExtra(Constant.INTENT_CATEGORY_ID, 0)
        presenter.getCategoryInfo(categoryId)
    }


    override fun showCategoryInfo(categoryTabInfo: CategoryTabInfo) {
        toolbar.title = ""
        categoryName = categoryTabInfo.categoryInfo.name
        categoryNameTv.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        categoryNameTv.text = categoryName
        categoryTitleTv.text = categoryTabInfo.categoryInfo.description
        ImageLoad.loadImage(categoryDetailIv, categoryTabInfo.categoryInfo.headerImage)
        presenter.getVideoList(categoryId)
    }

    override fun showVideoList(baseBean: BaseBean) {
        stopRefresh()
        if (baseBean.itemList.isEmpty())
            return
        nextPageUrl = baseBean.nextPageUrl
        if (isRefresh) {
            multiItemAdapter?.setRefreshData(baseBean.itemList)
        } else {
            multiItemAdapter?.setLoadMore(baseBean.itemList)
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
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    private fun stopRefresh() {
        refreshLayout.finishRefresh()
        refreshLayout.finishLoadMore()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
