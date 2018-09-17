package com.lisuperhong.openeye.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Rect
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.shuyu.gsyvideoplayer.utils.NetworkUtils
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/16 17:49
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class ScrollCalculatorHelper constructor(playId: Int, rangeTop: Int, rangeBottom: Int) {

    private var firstVisible = 0
    private var lastVisible = 0
    private var visibleCount = 0
    private var playId = 0
    private var rangeTop = 0
    private var rangeBottom = 0
    private var runnable: PlayRunnable? = null

    private val playHandler = Handler()

    init {
        this.playId = playId
        this.rangeTop = rangeTop
        this.rangeBottom = rangeBottom
    }

    fun onScrollStateChanged(view: RecyclerView, scrollState: Int) {
        when (scrollState) {
            RecyclerView.SCROLL_STATE_IDLE -> playVideo(view)
        }
    }

    fun onScroll(
        firstVisibleItem: Int,
        lastVisibleItem: Int,
        visibleItemCount: Int
    ) {
        if (firstVisible == firstVisibleItem) {
            return
        }
        firstVisible = firstVisibleItem
        lastVisible = lastVisibleItem
        visibleCount = visibleItemCount
    }


    private fun playVideo(view: RecyclerView?) {

        if (view == null) {
            return
        }

        val layoutManager = view.layoutManager

        var gsyBaseVideoPlayer: GSYBaseVideoPlayer? = null

        var needPlay = false

        for (i in 0 until visibleCount) {
            if (layoutManager.getChildAt(i) != null && layoutManager.getChildAt(i).findViewById<GSYBaseVideoPlayer>(playId) != null
            ) {
                val player =
                    layoutManager.getChildAt(i).findViewById<GSYBaseVideoPlayer>(playId)
                val rect = Rect()
                player.getLocalVisibleRect(rect)
                val height = player.height
                //说明第一个完全可视
                if (rect.top == 0 && rect.bottom == height) {
                    gsyBaseVideoPlayer = player
                    if (((player.currentPlayer.currentState == GSYBaseVideoPlayer.CURRENT_STATE_NORMAL || player.currentPlayer.currentState == GSYBaseVideoPlayer.CURRENT_STATE_ERROR))) {
                        needPlay = true
                    }
                    break
                }

            }
        }

        if (gsyBaseVideoPlayer != null && needPlay) {
            if (runnable != null) {
                val tmpPlayer = runnable!!.gsyBaseVideoPlayer
                playHandler.removeCallbacks(runnable)
                runnable = null
                if (tmpPlayer === gsyBaseVideoPlayer) {
                    return
                }
            }
            runnable = PlayRunnable(gsyBaseVideoPlayer)
            //降低频率
            playHandler.postDelayed(runnable, 400)
        }
    }

    private inner class PlayRunnable(internal var gsyBaseVideoPlayer: GSYBaseVideoPlayer?) :
        Runnable {

        override fun run() {
            var inPosition = false
            //如果未播放，需要播放
            if (gsyBaseVideoPlayer != null) {
                val screenPosition = IntArray(2)
                gsyBaseVideoPlayer!!.getLocationOnScreen(screenPosition)
                val halfHeight = gsyBaseVideoPlayer!!.height / 2
                val rangePosition = screenPosition[1] + halfHeight
                //中心点在播放区域内
                if (rangePosition >= rangeTop && rangePosition <= rangeBottom) {
                    inPosition = true
                }
                if (inPosition) {
                    startPlayLogic(gsyBaseVideoPlayer!!, gsyBaseVideoPlayer!!.context)
                }
            }
        }
    }


    /***************************************自动播放的点击播放确认 */
    private fun startPlayLogic(gsyBaseVideoPlayer: GSYBaseVideoPlayer, context: Context) {
        if (!com.shuyu.gsyvideoplayer.utils.CommonUtil.isWifiConnected(context)) {
            //这里判断是否wifi
            showWifiDialog(gsyBaseVideoPlayer, context)
            return
        }
        gsyBaseVideoPlayer.startPlayLogic()
    }

    private fun showWifiDialog(gsyBaseVideoPlayer: GSYBaseVideoPlayer, context: Context) {
        if (!NetworkUtils.isAvailable(context)) {
            Toast.makeText(
                context,
                context.resources.getString(com.shuyu.gsyvideoplayer.R.string.no_net),
                Toast.LENGTH_LONG
            ).show()
            return
        }
        val builder = AlertDialog.Builder(context)
        builder.setMessage(context.resources.getString(com.shuyu.gsyvideoplayer.R.string.tips_not_wifi))
        builder.setPositiveButton(
            context.resources.getString(com.shuyu.gsyvideoplayer.R.string.tips_not_wifi_confirm)
        ) { dialog, which ->
            dialog.dismiss()
            gsyBaseVideoPlayer.startPlayLogic()
        }
        builder.setNegativeButton(
            context.resources.getString(com.shuyu.gsyvideoplayer.R.string.tips_not_wifi_cancel),
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                }
            })
        builder.create().show()
    }
}