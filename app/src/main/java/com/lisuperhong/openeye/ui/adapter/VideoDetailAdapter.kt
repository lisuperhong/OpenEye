package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.google.gson.Gson
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.TextCard
import com.lisuperhong.openeye.mvp.model.bean.VideoSmallCard
import com.lisuperhong.openeye.utils.Constant
import com.orhanobut.logger.Logger
import org.json.JSONException
import org.json.JSONObject

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/13 22:36
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class VideoDetailAdapter(context: Context, datas: ArrayList<BaseBean.Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var context: Context? = null
    private var arrayList: ArrayList<BaseBean.Item>

    init {
        this.context = context
        this.arrayList = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createMultiViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return arrayList.size
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
            is VideoDetailInfoViewHolder -> {
                val videoSmallCard = gson.fromJson(dataJson.toString(), VideoSmallCard::class.java)
                bindVideoDetailInfoHolder(videoSmallCard, holder)
            }

            is TextCardItemHolder -> {
                val textCard = gson.fromJson(dataJson.toString(), TextCard::class.java)
                bindTextCardItemHolder(context!!, textCard, holder)
            }

            is VideoSmallCardItemHolder -> {
                val videoSmallCard = gson.fromJson(dataJson.toString(), VideoSmallCard::class.java)
                bindVideoSmallCardItemHolder(context!!, videoSmallCard, holder, true)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item: BaseBean.Item = arrayList[position]
        return when {
            position == 0 -> Constant.ITEM_TYPE_VIDEODETAIL

            item.type == "textCard" -> Constant.ITEM_TYPE_TEXTCARD

            item.type == "videoSmallCard" -> Constant.ITEM_TYPE_VIDEOSMALLCARD

            else -> throw IllegalAccessException("实体类解析出错，出现其他类型")
        }
    }

    fun addData(item: BaseBean.Item) {
        arrayList.clear()
        notifyDataSetChanged()
        arrayList.add(item)
        notifyItemInserted(0)
    }

    fun addData(items: ArrayList<BaseBean.Item>) {
        arrayList.addAll(items)
        notifyItemRangeInserted(1, items.size)
    }
}