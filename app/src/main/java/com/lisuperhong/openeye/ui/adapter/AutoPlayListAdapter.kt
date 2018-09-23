package com.lisuperhong.openeye.ui.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.AutoPlayFollowCard
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.utils.Constant
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.JumpActivityUtil
import com.lisuperhong.openeye.utils.TypefaceUtil
import com.orhanobut.logger.Logger
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.android.synthetic.main.item_autoplay_followcard.view.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 13:45
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class AutoPlayListAdapter(context: Context, dataList: ArrayList<BaseBean.Item>) :
    RecyclerView.Adapter<AutoPlayListAdapter.AutoPlayItemHolder>() {

    private var context: Context? = null
    private var dataList: ArrayList<BaseBean.Item>

    init {
        this.context = context
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoPlayItemHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_autoplay_followcard, parent, false)
        return AutoPlayItemHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(viewHolder: AutoPlayItemHolder, position: Int) {
        val gson = Gson()
        val dataBean = dataList[position]
        val dataMap = dataBean.data as Map<*, *>
        var dataJson: JSONObject? = null
        try {
            dataJson = JSONObject(dataMap)
        } catch (e: JSONException) {
            Logger.d(e.printStackTrace())
        }

        val autoPlayFollowCard =
            gson.fromJson(dataJson.toString(), AutoPlayFollowCard::class.java)
        val header = autoPlayFollowCard.header
        viewHolder.autoPlayCardOwnerTv.typeface =
                TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        viewHolder.titlePgcTv.typeface =
                TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        viewHolder.autoPlayCardOwnerTv.text = header.issuerName
        when (header.iconType) {
            "round" -> ImageLoad.loadCircleImage(viewHolder.autoPlayCardIconIv, header.icon)
            "square" -> ImageLoad.loadImage(viewHolder.autoPlayCardIconIv, header.icon, 5)
            else -> ImageLoad.loadImage(viewHolder.autoPlayCardIconIv, header.icon)
        }

        val content = autoPlayFollowCard.content
        val data = content.data
        viewHolder.titlePgcTv.text = data.title
        viewHolder.descriptionTv.text = data.description
        val cover = data.cover
        val consumption = data.consumption
        viewHolder.collectionCountTv.text = consumption.collectionCount.toString()
        viewHolder.replyCountTv.text = consumption.replyCount.toString()

        //增加封面
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ImageLoad.loadImage(imageView, cover.feed)

        val optionBuilder = GSYVideoOptionBuilder()
        optionBuilder.setIsTouchWiget(false)
            .setThumbImageView(imageView)
            .setUrl(data.playUrl)
            .setSetUpLazy(true)//lazy可以防止滑动卡顿
            .setVideoTitle(data.title)
            .setCacheWithPlay(true)
            .setRotateViewAuto(true)
            .setLockLand(true)
            .setPlayTag(Constant.AUTO_PLAY_TAG)
            .setShowFullAnimation(true)
            .setNeedLockFull(true)
            .setPlayPosition(position)
            .setReleaseWhenLossAudio(false)
            .setVideoAllCallBack(object : GSYSampleCallBack() {

                override fun onPrepared(url: String, vararg objects: Any) {
                    super.onPrepared(url, objects)
                    if (!viewHolder.autoPlayer.isIfCurrentIsFullscreen) {
                        //静音
                        GSYVideoManager.instance().isNeedMute = true
                    }
                }

                override fun onQuitFullscreen(url: String, vararg objects: Any) {
                    super.onQuitFullscreen(url, objects)
                    //全屏不静音
                    GSYVideoManager.instance().isNeedMute = true
                }

                override fun onEnterFullscreen(url: String?, vararg objects: Any) {
                    super.onEnterFullscreen(url, *objects)
                    GSYVideoManager.instance().isNeedMute = false
                    viewHolder.autoPlayer.getCurrentPlayer().getTitleTextView().setText(objects[0] as String)
                }
            }).build(viewHolder.autoPlayer)

        //增加title
        viewHolder.autoPlayer.titleTextView.visibility = View.GONE
        //设置返回键
        viewHolder.autoPlayer.backButton.visibility = View.GONE
        //设置全屏按键功能
        viewHolder.autoPlayer.fullscreenButton.visibility = View.GONE

        viewHolder.autoPlayCardLl.setOnClickListener {
            JumpActivityUtil.startVideoDetail(context as Activity, viewHolder.autoPlayer, data)
        }
    }

    private fun clearAll() = dataList.clear()

    /**
     * 初始化或刷新数据
     */
    fun setRefreshData(datas: ArrayList<BaseBean.Item>) {
        notifyItemRangeRemoved(0, itemCount)
        clearAll()
        dataList.addAll(datas)
        notifyItemRangeInserted(0, datas.size)
    }

    /**
     * 加载更多数据
     */
    fun setLoadMore(datas: ArrayList<BaseBean.Item>) {
        dataList.addAll(datas)
        notifyItemRangeInserted(itemCount, datas.size)
    }

    inner class AutoPlayItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var autoPlayCardLl: LinearLayout = view.autoPlayCardLl
        var autoPlayCardIconIv: ImageView = view.autoPlayCardIconIv
        var autoPlayCardOwnerTv: TextView = view.autoPlayCardOwnerTv
        var titlePgcTv: TextView = view.titlePgcTv
        var descriptionTv: TextView = view.playDescriptionTv
        var autoPlayer: StandardGSYVideoPlayer = view.autoPlayer
        var collectionCountTv: TextView = view.playCollectionCountTv
        var replyCountTv: TextView = view.playReplyCountTv
    }

}