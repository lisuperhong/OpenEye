package com.lisuperhong.openeye.ui.activity

import android.os.Bundle
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class WebViewActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_web_view

    override fun initView() {
        toolbarTitleTv.text = ""
        toolbarBackIv.setOnClickListener {
            onBackPressed()
        }


    }

    override fun initData(savedInstanceState: Bundle?) {
    }
}
