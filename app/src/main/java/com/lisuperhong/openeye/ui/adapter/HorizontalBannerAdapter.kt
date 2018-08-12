package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.Banner
import com.lisuperhong.openeye.mvp.model.bean.HorizontalScrollCard
import com.lisuperhong.openeye.utils.ImageLoad
import kotlinx.android.synthetic.main.list_horizontal_banner_item.view.*

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
        var view = LayoutInflater.from(context).inflate(R.layout.list_horizontal_banner_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val banner = dataList[position]
        ImageLoad.loadImage(holder.horizontalBannerIv, banner.image, 5)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var horizontalBannerIv: ImageView = view.horizontalBannerIv
    }
}