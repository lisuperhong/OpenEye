package com.lisuperhong.openeye.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseFragment : Fragment() {

    protected val TAG = BaseFragment::class.java.simpleName

    /**
     * 当前Fragment是否可见
     */
    private var isFragmentVisible: Boolean = false

    /**
     * true表示rootView已加载完成
     */
    private var isPrepared: Boolean = false

    /**
     * 是否第一次加载
     */
    private var isFirstLoad = true

    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要isFragmentVisible & isPrepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new新的PagerAdapter，而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 [BaseFragment.setForceLoad]来让Fragment下次执行initData
     */
    private var forceLoad = false

    private var unBinder: Unbinder? = null
    private var baseActivity: BaseActivity? = null

    // 缓存Fragment的view
    private var rootView: View? = null

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = this.activity as BaseActivity?
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(layoutId, null)
            unBinder = ButterKnife.bind(this, rootView!!)
        }
        val parent = rootView!!.parent as ViewGroup
        parent.removeView(rootView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true
        isFirstLoad = true
        initView()
        initListener()
        lazyLoad()
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser Fragment是否显示
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            onVisible()
        } else {
            onInvisible()
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     * visible.
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            onVisible()
        } else {
            onInvisible()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isPrepared = false
        if (unBinder != null) {
            unBinder!!.unbind()
        }
    }

    protected fun onVisible() {
        isFragmentVisible = true
        lazyLoad()
    }

    protected fun onInvisible() {
        isFragmentVisible = false
    }

    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要isFragmentVisible & isPrepared
     */
    fun setForceLoad() {
        forceLoad = true
    }

    private fun lazyLoad() {
        if (isPrepared && isFragmentVisible) {
            if (isFirstLoad || forceLoad) {
                isFirstLoad = false
                forceLoad = false
                initData()
            }
        }
    }

    protected abstract fun initListener()

    /**
     * 若把初始化内容放到initData实现
     * 就是采用Lazy方式加载的Fragment
     * 若不需要Lazy加载则initData方法内留空,初始化内容放到initView即可
     */
    protected abstract fun initView()

    protected abstract fun initData()
}
