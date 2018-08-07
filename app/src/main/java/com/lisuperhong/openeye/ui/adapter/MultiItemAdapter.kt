package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.google.gson.Gson
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.FollowCard
import com.lisuperhong.openeye.mvp.model.bean.SquareCardCollection
import com.lisuperhong.openeye.mvp.model.bean.TextCard
import com.lisuperhong.openeye.utils.Constant
import com.orhanobut.logger.Logger
import org.json.JSONException
import org.json.JSONObject

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
        val gson = Gson()
        val data = arrayList[position]
        val dataMap = data.data as Map<*, *>
        var dataJson: JSONObject? = null
        try {
            dataJson = JSONObject(dataMap)
        } catch (e: JSONException) {
            Logger.d(e.printStackTrace())
        }
        when (holder) {
            is SquareCardItemHolder -> {
                val squareCardCollection = gson.fromJson(dataJson.toString(), SquareCardCollection::class.java)
                bindSquareCardItemHolder(context!!, squareCardCollection, holder)
            }
            is TextCardItemHolder -> {
                val textCard = gson.fromJson(dataJson.toString(), TextCard::class.java)
                bindTextCardItemHolder(context!!, textCard, holder)
            }
            is FollowCardItemHolder -> {
                val followCard = gson.fromJson(dataJson.toString(), FollowCard::class.java)
                bindFollowCardItemHolder(context!!, followCard, holder)
            }

            else -> {
                val textCard = gson.fromJson(dataJson.toString(), TextCard::class.java)
                bindTextCardItemHolder(context!!, textCard, holder)
            }
        }
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