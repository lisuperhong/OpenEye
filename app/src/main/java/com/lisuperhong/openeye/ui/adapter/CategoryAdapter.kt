package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.gson.Gson
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.SquareCard
import com.lisuperhong.openeye.utils.DensityUtil
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.JumpActivityUtil
import com.lisuperhong.openeye.utils.TypefaceUtil
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.item_squarecard.view.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/17 10:21
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class CategoryAdapter(context: Context, dataList: ArrayList<BaseBean.Item>) :
    RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    private var context: Context? = null
    private var dataList: ArrayList<BaseBean.Item>

    init {
        this.context = context
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ItemHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_squarecard, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ItemHolder, position: Int) {
        val item = dataList[position]
        val gson = Gson()
        val dataMap = item.data as Map<*, *>
        var dataJson: JSONObject? = null
        try {
            dataJson = JSONObject(dataMap)
        } catch (e: JSONException) {
            Logger.d(e.printStackTrace())
        }
        val squareCard = gson.fromJson(dataJson.toString(), SquareCard::class.java)
        holder.squareCardTv.typeface =
                TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        holder.squareCardTv.text = squareCard.title

        if (item.type == "squareCard") {
            val width =
                (DensityUtil.getScreenWidth(context!!) - DensityUtil.dip2px(context!!, 8f)) / 2
            ImageLoad.loadImage(holder.squareCardIv, squareCard.image, width, width)
        } else if (item.type == "rectangleCard") {
            val width = DensityUtil.getScreenWidth(context!!) - DensityUtil.dip2px(context!!, 4f)
            ImageLoad.loadImage(holder.squareCardIv, squareCard.image, width, width / 2)
        }

        holder.squareCardRl.setOnClickListener {
            JumpActivityUtil.parseActionUrl(context!!, squareCard.actionUrl)
        }
    }

    private fun clearAll() = dataList.clear()

    /**
     * 初始化或刷新数据
     */
    fun setRefreshData(datas: ArrayList<BaseBean.Item>) {
        notifyItemRangeRemoved(0, itemCount)
        clearAll()
        dataList.addAll(datas)
        notifyItemRangeInserted(0, datas.size)
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var squareCardRl: RelativeLayout = view.squareCardRl
        var squareCardIv: ImageView = view.squareCardIv
        var squareCardTv: TextView = view.squareCardTv
    }
}