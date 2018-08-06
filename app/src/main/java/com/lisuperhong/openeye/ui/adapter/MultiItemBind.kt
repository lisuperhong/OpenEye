package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.FollowCard
import com.lisuperhong.openeye.mvp.model.bean.SquareCardCollection
import com.lisuperhong.openeye.utils.TypefaceUtil

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 15:56
 * Desc:
 */

fun bindMultiViewHolder(
    context: Context,
    dataList: ArrayList<BaseBean.Item>,
    holder: RecyclerView.ViewHolder,
    position: Int
) {
    when (holder) {
        is SquareCardItemHolder -> bindSquareCardItemHolder(context, dataList, holder, position)
    }

}

fun bindSquareCardItemHolder(
    context: Context,
    dataList: ArrayList<BaseBean.Item>,
    holder: RecyclerView.ViewHolder,
    position: Int
) {
    val data = dataList[position]
    val viewHolder: SquareCardItemHolder = holder as SquareCardItemHolder
    val squareCardCollection = data.data as SquareCardCollection
    val header = squareCardCollection.header
    if (header.font != null && header.font == "bigBold") {
        viewHolder.squareTitle.typeface =
                TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        viewHolder.squareTitle.text = header.title
    }
    if (header.subTitleFont != null && header.subTitleFont == "lobster") {
        viewHolder.squareSubTitle.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.Lobster)
        viewHolder.squareSubTitle.text = header.subTitleFont
    }

    viewHolder.squareTitle.setOnClickListener(View.OnClickListener {
    })

    val followCardList: ArrayList<FollowCard> = ArrayList<FollowCard>()
    val itemList = squareCardCollection.itemList
    for (item in itemList) {
        followCardList.add(item.data)
    }
    val linearLayout: RecyclerView.LayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    viewHolder.squareCardRecyclerView.layoutManager = linearLayout
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(viewHolder.squareCardRecyclerView)

    val adapter = FollowCardAdapter(context, followCardList)
    viewHolder.squareCardRecyclerView.adapter = adapter
}

fun bindFollowCardItemHolder(
    context: Context,
    dataList: ArrayList<BaseBean.Item>,
    holder: RecyclerView.ViewHolder,
    position: Int
) {

}