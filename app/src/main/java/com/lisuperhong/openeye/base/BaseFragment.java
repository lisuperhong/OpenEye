package com.lisuperhong.openeye.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = BaseFragment.class.getSimpleName();

    /**
     * 当前Fragment是否可见
     */
    private boolean isFragmentVisible;

    /**
     * true表示rootView已加载完成
     */
    private boolean isPrepared;

    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;

    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要isFragmentVisible & isPrepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new新的PagerAdapter，而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 {@link BaseFragment#setForceLoad(boolean)}来让Fragment下次执行initData
     */
    private boolean forceLoad = false;

    private Unbinder unBinder;
    private BaseActivity baseActivity;

    // 缓存Fragment的view
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.baseActivity = (BaseActivity) this.getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            unBinder = ButterKnife.bind(this, rootView);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        isFirstLoad = true;
        initView();
        initListener();
        lazyLoad();
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser Fragment是否显示
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     * visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isPrepared = false;
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    protected void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }

    protected void onInvisible() {
        isFragmentVisible = false;
    }

    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要isFragmentVisible & isPrepared
     */
    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }

    private void lazyLoad() {
        if (isPrepared && isFragmentVisible) {
            if (isFirstLoad || forceLoad) {
                isFirstLoad = false;
                forceLoad = false;
                initData();
            }
        }
    }

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void initListener();

    /**
     * 若把初始化内容放到initData实现
     * 就是采用Lazy方式加载的Fragment
     * 若不需要Lazy加载则initData方法内留空,初始化内容放到initView即可
     */
    protected abstract void initView();

    protected abstract void initData();
}
