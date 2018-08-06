package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.FollowCard
import com.lisuperhong.openeye.utils.TypefaceUtil
import kotlinx.android.synthetic.main.list_followcard_item.view.*

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
            LayoutInflater.from(context).inflate(R.layout.list_followcard_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val followCard = dataList[position]
        holder.followCardTitle.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        holder.followCardTitle.text = followCard.header.title
        holder.followCardSubTitle.text = followCard.header.description
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var followCardCoverIv: ImageView = view.followCardCoverIv
        var followCardTimeTv: TextView = view.followCardTimeTv
        var followCardIconIv: ImageView = view.followCardIconIv
        var followCardTitle: TextView = view.followCardTitle
        var followCardSubTitle: TextView = view.followCardSubTitle
    }
}