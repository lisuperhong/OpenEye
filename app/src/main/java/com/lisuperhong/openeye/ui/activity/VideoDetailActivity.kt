package com.lisuperhong.openeye.ui.activity

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.transition.Transition
import android.widget.ImageView
import android.widget.Toast
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.base.BaseActivity
import com.lisuperhong.openeye.mvp.contract.VideoDetailContract
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.VideoSmallCard
import com.lisuperhong.openeye.mvp.presenter.VideoDetailPresenter
import com.lisuperhong.openeye.ui.adapter.VideoDetailAdapter
import com.lisuperhong.openeye.utils.Constant
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.StatusBarUtil
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_video_detail.*

class VideoDetailActivity : BaseActivity(), VideoDetailContract.View,
    VideoDetailAdapter.VideoItemClickListener {

    private lateinit var videoSmallCard: VideoSmallCard
    private val presenter by lazy { VideoDetailPresenter() }
    private var videoDetailAdapter: VideoDetailAdapter? = null

    private var orientationUtils: OrientationUtils? = null
    private var isPlay = false
    private var isPause = false

    private var isTransition: Boolean = false

    private var transition: Transition? = null

    companion object {

        val FROM_TRANSITION = "TRANSITION"
        val IMG_TRANSITION = "IMG_TRANSITION"
    }

    override fun layoutId(): Int {
        return R.layout.activity_video_detail
    }

    override fun initView() {
        presenter.attachView(this)

        //状态栏透明和间距处理
//        StatusBarUtil.immersive(this)
//        StatusBarUtil.setPaddingSmart(this, videoPlayer)

        videoDetailAdapter = VideoDetailAdapter(this, ArrayList<BaseBean.Item>())
        videoDetailAdapter?.setVideoItemClickListener(this)
        videoDetailRecycleView.layoutManager = LinearLayoutManager(this)
        videoDetailRecycleView.adapter = videoDetailAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        videoSmallCard = intent.getSerializableExtra(Constant.INTENT_VIDEO_DETAIL) as VideoSmallCard
        isTransition = intent.getBooleanExtra(FROM_TRANSITION, false)

        // 播放器配置
        videoViewConfig()
        // 跳转过渡动画
        initTransition()
    }

    override fun setVideoUrl(url: String) {
        videoPlayer.setUp(url, false, "")
        //开始自动播放
        videoPlayer.startPlayLogic()
    }

    override fun setVideoData(videoSmallCard: VideoSmallCard) {
        val item: BaseBean.Item = BaseBean.Item("videoSmallCard", null, 0, -1, videoSmallCard)
        videoDetailAdapter?.addData(item)
        presenter.requestRelatedVideo(videoSmallCard.id)
    }

    override fun showRelatedVideo(baseBean: BaseBean) {
        if (baseBean.itemList.isEmpty())
            return
        videoDetailAdapter?.addData(baseBean.itemList)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun itemClick(relatedVideoSmallCard: VideoSmallCard) {
        presenter.loadVideoInfo(relatedVideoSmallCard)
    }

    private fun initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION)
            addTransitionListener()
            startPostponedEnterTransition()
        } else {
            loadVideoInfo()
        }
    }

    private fun videoViewConfig() {
        // 外部辅助的旋转，帮助全屏
        orientationUtils = OrientationUtils(this, videoPlayer)
        //初始化不打开外部的旋转
        orientationUtils?.isEnable = false

        // 增加封面
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ImageLoad.loadImage(imageView, videoSmallCard.cover.feed)

        videoPlayer.thumbImageView = imageView
        videoPlayer.setIsTouchWiget(true)
        videoPlayer.isRotateViewAuto = false
        videoPlayer.isLockLand = false
        videoPlayer.isAutoFullWithSize = true
        videoPlayer.isShowFullAnimation = false
        videoPlayer.isNeedLockFull = true

        videoPlayer.setVideoAllCallBack(object : GSYSampleCallBack() {
            override fun onPrepared(url: String, vararg objects: Any) {
                super.onPrepared(url, objects)
                //开始播放了才能旋转和全屏
                orientationUtils?.isEnable = true
                isPlay = true
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, objects)
                orientationUtils?.backToProtVideo()
            }
        })

        //设置返回按键功能
        videoPlayer.backButton.setOnClickListener { _ -> onBackPressed() }
        //设置全屏按键功能
        videoPlayer.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils?.resolveByClick()
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            videoPlayer.startWindowFullscreen(this, true, true)
        }
        //锁屏事件
        videoPlayer.setLockClickListener { _, lock -> orientationUtils?.isEnable = !lock }
    }

    private fun addTransitionListener() {
        transition = window.sharedElementEnterTransition
        transition?.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(p0: Transition?) {

            }

            override fun onTransitionResume(p0: Transition?) {

            }

            override fun onTransitionPause(p0: Transition?) {

            }

            override fun onTransitionCancel(p0: Transition?) {

            }

            override fun onTransitionStart(p0: Transition?) {
                loadVideoInfo()
                transition?.removeListener(this)
            }
        })
    }

    private fun loadVideoInfo() {
        presenter.loadVideoInfo(videoSmallCard)
    }

    /**
     * 监听返回键
     */
    override fun onBackPressed() {
        orientationUtils?.backToProtVideo()
        if (GSYVideoManager.backFromWindowFull(this))
            return

        GSYVideoManager.releaseAllVideos()
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) run {
            super.onBackPressed()
        } else {
            finish()
            overridePendingTransition(R.anim.anim_out, R.anim.anim_in)
        }
    }

    override fun onPause() {
        videoPlayer.currentPlayer.onVideoPause()
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        videoPlayer.currentPlayer.onVideoResume(false)
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay) {
            videoPlayer.currentPlayer.release()
        }
        orientationUtils?.releaseListener()
        presenter.detachView()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (isPlay && !isPause) {
            videoPlayer.onConfigurationChanged(this, newConfig, orientationUtils)
        }
    }
}
