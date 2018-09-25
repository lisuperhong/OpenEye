package com.lisuperhong.openeye.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.*
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.utils.Constant
import kotlinx.android.synthetic.main.custom_toolbar.*

class WebViewActivity : BaseActivity() {

    private var webView: WebView? = null
    private var url: String? = null

    override fun layoutId(): Int = R.layout.activity_web_view

    override fun initView() {
        webView = findViewById(R.id.webView)
        toolbarTitleTv.text = ""
        toolbarBackIv.setOnClickListener {
            if (webView!!.canGoBack()) {
                webView?.goBack()
            } else {
                finish()
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        url = intent.getStringExtra(Constant.INTENT_WEB_VIEW_URL)

        val webSettings = webView!!.settings
        webSettings.loadWithOverviewMode = true
        webSettings.setSupportMultipleWindows(true)
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        webSettings.loadsImagesAutomatically = Build.VERSION.SDK_INT >= 19
        webSettings.useWideViewPort = true
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.defaultTextEncodingName = "UTF-8"
        deleteDatabase("WebView.db")
        deleteDatabase("WebViewCache.db")
        webView?.clearHistory()
        webView?.clearFormData()

        //设置不用系统浏览器打开,直接显示在当前Webview
        webView?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view!!.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (!webView!!.settings.loadsImagesAutomatically) {
                    webView!!.settings.loadsImagesAutomatically = true
                }
            }
        }

        webView?.loadUrl(url)
    }

    override fun onResume() {
        super.onResume()
        webView?.onResume()
        webView?.resumeTimers()
    }

    override fun onPause() {
        super.onPause()
        webView?.onPause()
        webView?.pauseTimers()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView!!.canGoBack()) {
                webView?.goBack()//返回上一页面
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    //销毁Webview
    override fun onDestroy() {
        super.onDestroy()
        webView?.loadUrl("about:blank")
        webView?.stopLoading()
        webView?.clearHistory()
        webView?.webChromeClient = null
        webView?.webViewClient = null
        (webView?.parent as ViewGroup).removeView(webView)
        webView?.destroy()
        webView = null
    }

}
