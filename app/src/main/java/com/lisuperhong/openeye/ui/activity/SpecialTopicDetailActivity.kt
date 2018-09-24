package com.lisuperhong.openeye.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.mvp.contract.SpecialTopicDetailContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.LightTopicBean
import com.lisuperhong.openeye.mvp.presenter.SpecialTopicDetailPresenter
import com.lisuperhong.openeye.ui.adapter.MultiItemAdapter
import com.lisuperhong.openeye.utils.Constant
import com.lisuperhong.openeye.utils.ImageLoad
import kotlinx.android.synthetic.main.activity_special_topic_detail.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class SpecialTopicDetailActivity : BaseActivity(), SpecialTopicDetailContract.View {

    private val presenter by lazy { SpecialTopicDetailPresenter() }
    private var title: String? = null
    private var specialTopicId: Int = 0
    private var multiItemAdapter: MultiItemAdapter? = null

    companion object {
        fun start(context: Context, id: Int, title: String) {
            val intent = Intent(context, SpecialTopicDetailActivity::class.java)
            intent.putExtra(Constant.INTENT_SPECIAL_TOPIC_ID, id)
            intent.putExtra(Constant.INTENT_SPECIAL_TOPIC_TITLE, title)
            context.startActivity(intent)
        }
    }

    override fun layoutId(): Int = R.layout.activity_special_topic_detail

    override fun initView() {
        presenter.attachView(this)
        toolbarBackIv.setOnClickListener {
            onBackPressed()
        }

        multiItemAdapter = MultiItemAdapter(this, ArrayList<BaseBean.Item>())
        recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = multiItemAdapter
        recyclerView.isNestedScrollingEnabled = false
        showLoading()
    }

    override fun initData(savedInstanceState: Bundle?) {
        specialTopicId = intent.getIntExtra(Constant.INTENT_SPECIAL_TOPIC_ID, 0)
        title = intent.getStringExtra(Constant.INTENT_SPECIAL_TOPIC_TITLE)
        toolbarTitleTv.text = title

        val url = Constant.HOST + "api/v3/lightTopics/internal/" + specialTopicId
        presenter.getSpecialTopicDetail(url)
    }

    override fun showSpecialTopicDetail(lightTopicBean: LightTopicBean) {
        ImageLoad.loadImage(headerIv, lightTopicBean.headerImage)
        briefTv.text = lightTopicBean.brief
        textTv.text = lightTopicBean.text
        if (lightTopicBean.itemList.isEmpty())
            return

        multiItemAdapter?.setRefreshData(lightTopicBean.itemList)
    }

    override fun showLoading() {
        loadingProgressBar.show()
    }

    override fun hideLoading() {
        loadingProgressBar.hide()
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
