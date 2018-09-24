package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.Banner
import com.lisuperhong.openeye.ui.activity.SpecialTopicDetailActivity
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.JumpActivityUtil
import kotlinx.android.synthetic.main.item_horizontal_banner.view.*

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/11 22:03
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class HorizontalBannerAdapter(context: Context, datas: ArrayList<Banner>) :
RecyclerView.Adapter<HorizontalBannerAdapter.ViewHolder>() {

    private var context: Context? = null
    private var dataList: ArrayList<Banner> = ArrayList<Banner>()

    init {
        this.context = context
        this.dataList = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_banner, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val banner = dataList[position]
        ImageLoad.loadImage(holder.horizontalBannerIv, banner.image, 5)

        holder.horizontalBannerIv.setOnClickListener {
            val uri: Uri = Uri.parse(banner.actionUrl)
            if (uri.host == "lightTopic") {
                SpecialTopicDetailActivity.start(context!!, banner.id, banner.title)
            } else {
                JumpActivityUtil.parseActionUrl(context!!, banner.actionUrl)
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var horizontalBannerIv: ImageView = view.horizontalBannerIv
    }
}