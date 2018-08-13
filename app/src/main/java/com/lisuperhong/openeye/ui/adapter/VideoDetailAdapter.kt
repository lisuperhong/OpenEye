package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.VideoSmallCard
import com.lisuperhong.openeye.utils.Constant

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/13 22:36
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class VideoDetailAdapter(context: Context, videoSmallCard: VideoSmallCard) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var videoSmallCard: VideoSmallCard
    private var arrayList: ArrayList<BaseBean.Item> = ArrayList<BaseBean.Item>()

    init {
        this.context = context
        this.videoSmallCard = videoSmallCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createMultiViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return arrayList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return Constant.ITEM_TYPE_VIDEODETAIL
        } else {
            if (position >= 1) {
                val item: BaseBean.Item = arrayList[position - 1]
                return when (item.type) {
                    "textCard" -> Constant.ITEM_TYPE_TEXTCARD

                    "videoSmallCard" -> Constant.ITEM_TYPE_VIDEOSMALLCARD

                    else ->
                        throw IllegalAccessException("Api 解析出错了，出现其他类型")
                }
            }
        }

        return super.getItemViewType(position)
    }

    fun addData(items: ArrayList<BaseBean.Item>) {
        arrayList.clear()
        arrayList.addAll(items)
        notifyItemRangeInserted(1, items.size)
    }
}