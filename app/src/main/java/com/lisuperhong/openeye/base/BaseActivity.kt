package com.lisuperhong.openeye.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lisuperhong.openeye.utils.ActivityManager

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        ActivityManager.addActivity(this)
        val intent = intent
        if (intent != null) {
            handleIntent(intent)
        }

        initView()
        initData(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.remove(this)
    }

    /**
     * 通过Class跳转界面
     */
    fun startActivity(cls: Class<*>) {
        startAcvitity(cls, null)
    }

    fun startAcvitity(cls: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    fun startActivityForResult(cls: Class<*>, requestCode: Int) {
        startActivityForResult(cls, null, requestCode)
    }

    fun startActivityForResult(cls: Class<*>, bundle: Bundle? = null, requestCode: Int) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * 获取跳转传递的Intent数据
     */
    protected fun handleIntent(intent: Intent) {

    }

    /**
     * 获取布局Id
     */
    protected abstract fun layoutId(): Int

    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 初始化数据，从服务端或本地加载数据
     */
    protected abstract fun initData(savedInstanceState: Bundle?)
}