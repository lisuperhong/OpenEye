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
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.VideoBeanForClient
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.TimeUtil
import com.lisuperhong.openeye.utils.TypefaceUtil
import kotlinx.android.synthetic.main.list_videocollection_item.view.*

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/11 23:33
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class VideoCollectionAdapter(context: Context, dataList: ArrayList<VideoBeanForClient>) :
    RecyclerView.Adapter<VideoCollectionAdapter.ViewHolder>() {

    private var context: Context? = null
    private var dataList: ArrayList<VideoBeanForClient> = java.util.ArrayList<VideoBeanForClient>()

    init {
        this.context = context
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.list_videocollection_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoBeanForClient = dataList[position]
        val data = videoBeanForClient.data
        holder.videoCollectionTitleTv.typeface =
                TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        holder.videoCollectionTitleTv.text = data.title
        holder.videoCollectionSubTitleTv.text = "#${data.category}"
        ImageLoad.loadImage(holder.videoCollectionCoverIv, data.cover.feed, 5)
        holder.videoCollectionTimeTv.text = TimeUtil.secToTime(data.duration)

        holder.videoCollectionLl.setOnClickListener {
            startVideoDetail(
                context as Activity,
                holder.videoCollectionCoverIv,
                videoBeanForClient.data
            )
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var videoCollectionLl: LinearLayout = view.videoCollectionLl
        var videoCollectionCoverIv: ImageView = view.videoCollectionCoverIv
        var videoCollectionTimeTv: TextView = view.videoCollectionTimeTv
        var videoCollectionTitleTv: TextView = view.videoCollectionTitleTv
        var videoCollectionSubTitleTv: TextView = view.videoCollectionSubTitleTv
    }
}