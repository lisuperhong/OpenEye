package com.lisuperhong.openeye.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.mvp.contract.TagDetailContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo
import com.lisuperhong.openeye.mvp.presenter.TagDetailPresenter
import com.lisuperhong.openeye.ui.adapter.MultiItemAdapter
import com.lisuperhong.openeye.utils.Constant
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.TypefaceUtil
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_tag_detail.*

class TagDetailActivity : BaseActivity(), TagDetailContract.View {

    private val presenter by lazy { TagDetailPresenter() }
    private var tagName: String? = null
    private var tagId: Long = 0
    private var multiItemAdapter: MultiItemAdapter? = null
    private var isRefresh = false
    private var nextPageUrl: String? = null

    override fun layoutId(): Int = R.layout.activity_tag_detail

    override fun initView() {
        presenter.attachView(this)

        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) >= appBarLayout!!.totalScrollRange) { // 收缩状态
                toolbar.setNavigationIcon(R.drawable.ic_action_back)
                toolbarTitleTv.text = tagName
            } else { // 展开或过度状态
                toolbar.setNavigationIcon(R.drawable.ic_action_back_white)
                toolbarTitleTv.text = ""
            }
        }

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        refreshLayout.setRefreshHeader(ClassicsHeader(this))
        refreshLayout.setEnableAutoLoadMore(true)
        refreshLayout.setOnClickListener {
            isRefresh = true
            presenter.getVideos(tagId)
        }

        refreshLayout.setOnLoadMoreListener {
            isRefresh = false
            if (nextPageUrl != null) {
                presenter.loadMore(nextPageUrl!!)
            } else {
                refreshLayout.setEnableLoadMore(false)
            }
        }

        multiItemAdapter = MultiItemAdapter(this, ArrayList<BaseBean.Item>())
        tagDetailRecycleView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        tagDetailRecycleView.adapter = multiItemAdapter
        isRefresh = false
        showLoading()
    }

    override fun initData(savedInstanceState: Bundle?) {
        tagId = intent.getLongExtra(Constant.INTENT_TAG_ID, 0)
        presenter.getTagInfo(tagId)
    }

    override fun showTagInfo(categoryTabInfo: CategoryTabInfo) {
        toolbar.title = ""
        val tagInfo = categoryTabInfo.tagInfo
        tagName = tagInfo.name
        tagNameTv.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        tagNameTv.text = tagName
        tagDetailInfoTv.text =
                "${tagInfo.tagVideoCount}作品/${tagInfo.tagFollowCount}关注者/${tagInfo.tagDynamicCount}动态"
        ImageLoad.loadImage(tagDetailIv, tagInfo.headerImage)
        presenter.getVideos(tagId)
    }

    override fun showVideos(baseBean: BaseBean) {
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
