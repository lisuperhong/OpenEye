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
class MultipleItemAdapter(context: Context, datas: ArrayList<BaseBean.Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var datas: ArrayList<BaseBean.Item>? = null

    init {
        this.context = context
        this.datas = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        val item: BaseBean.Item = datas!!.get(position)
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

    private fun clearAll() = datas?.clear()

    /**
     * 初始化或刷新数据
     */
    fun setRefreshData(datas: ArrayList<BaseBean.Item>) {
        clearAll()
        this.datas?.addAll(datas)
        notifyItemRangeInserted(0, datas.size)
    }

    /**
     * 加载更多数据
     */
    fun setLoadMore(datas: ArrayList<BaseBean.Item>) {
        this.datas?.addAll(datas)
        notifyItemRangeInserted(itemCount, datas.size)
    }
}