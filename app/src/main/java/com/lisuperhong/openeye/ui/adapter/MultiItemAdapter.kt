package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.utils.Constant

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/5 15:10
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class MultiItemAdapter(context: Context, datas: ArrayList<BaseBean.Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var arrayList: ArrayList<BaseBean.Item> = ArrayList<BaseBean.Item>()

    init {
        this.context = context
        this.arrayList = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createMultiViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindMultiViewHolder(context!!, arrayList, holder, position)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: BaseBean.Item = arrayList[position]
        return when (item.type) {
            "squareCardCollection" -> Constant.ITEM_TYPE_SQUARECARD

            "textCard" -> Constant.ITEM_TYPE_TEXTCARD

            "followCard" -> Constant.ITEM_TYPE_FLLOWCARD

            "videoSmallCard" -> Constant.ITEM_TYPE_VIDEOSMALLCARD

            "autoPlayFollowCard" -> Constant.ITEM_TYPE_AUTOPLAYFOLLOWCARD

            "banner" -> Constant.ITEM_TYPE_BANNER

            "banner2" -> Constant.ITEM_TYPE_BANNER

            "pictureFollowCard" -> Constant.ITEM_TYPE_PICTUREFOLLOWCARD

            else -> Constant.ITEM_TYPE_VIDEOSMALLCARD
        }
    }

    private fun clearAll() = arrayList.clear()

    /**
     * 初始化或刷新数据
     */
    fun setRefreshData(datas: ArrayList<BaseBean.Item>) {
        clearAll()
        this.arrayList.addAll(datas)
        notifyItemRangeInserted(0, datas.size)
    }

    /**
     * 加载更多数据
     */
    fun setLoadMore(datas: ArrayList<BaseBean.Item>) {
        this.arrayList.addAll(datas)
        notifyItemRangeInserted(itemCount, datas.size)
    }
}