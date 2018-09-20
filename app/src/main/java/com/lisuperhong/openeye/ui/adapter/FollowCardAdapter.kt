package com.lisuperhong.openeye.ui.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.FollowCard
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.JumpActivityUtil
import com.lisuperhong.openeye.utils.TimeUtil
import com.lisuperhong.openeye.utils.TypefaceUtil
import kotlinx.android.synthetic.main.item_followcard.view.*

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 17:22
 * Desc:
 */
class FollowCardAdapter(context: Context, datas: ArrayList<FollowCard>) :
    RecyclerView.Adapter<FollowCardAdapter.ViewHolder>() {

    private var context: Context? = null
    private var dataList: ArrayList<FollowCard> = ArrayList<FollowCard>()

    init {
        this.context = context
        this.dataList = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_today_recommend, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val followCard = dataList[position]
        val header = followCard.header
        holder.followCardTitle.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        holder.followCardTitle.text = header.title
        holder.followCardSubTitle.text = header.description
        when (header.iconType) {
            "round" -> ImageLoad.loadCircleImage(holder.followCardIconIv, header.icon)
            "square" -> ImageLoad.loadImage(holder.followCardIconIv, header.icon, 5)
            else -> ImageLoad.loadImage(holder.followCardIconIv, header.icon)
        }

        val content = followCard.content
        val data = content.data
        val cover = data.cover
        ImageLoad.loadImage(holder.followCardCoverIv, cover.feed, 5)
        holder.followCardTimeTv.text = TimeUtil.secToTime(data.duration)

        holder.followCardCoverIv.setOnClickListener {
            val videoSmallCard = followCard.content.data
            JumpActivityUtil.startVideoDetail(context as Activity, holder.followCardCoverIv, videoSmallCard)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var followCardCoverIv: ImageView = view.followCardCoverIv
        var followCardTimeTv: TextView = view.followCardTimeTv
        var followCardIconIv: ImageView = view.followCardIconIv
        var followCardTitle: TextView = view.followCardTitle
        var followCardSubTitle: TextView = view.followCardSubTitle
    }
}