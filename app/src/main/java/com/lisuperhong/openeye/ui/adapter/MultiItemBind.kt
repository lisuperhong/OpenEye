package com.lisuperhong.openeye.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lisuperhong.openeye.mvp.model.bean.FollowCard
import com.lisuperhong.openeye.mvp.model.bean.SquareCardCollection
import com.lisuperhong.openeye.mvp.model.bean.TextCard
import com.lisuperhong.openeye.utils.TypefaceUtil

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 15:56
 * Desc:
 */

fun bindSquareCardItemHolder(
    context: Context,
    squareCardCollection: SquareCardCollection,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: SquareCardItemHolder = holder as SquareCardItemHolder
    val header = squareCardCollection.header
    if (header.font != null && header.font == "bigBold") {
        viewHolder.squareTitle.typeface =
                TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        viewHolder.squareTitle.text = header.title
    }
    if (header.subTitleFont != null && header.subTitleFont == "lobster") {
        viewHolder.squareSubTitle.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.Lobster)
        viewHolder.squareSubTitle.text = header.subTitle
    }

    viewHolder.squareTitle.setOnClickListener(View.OnClickListener {
    })

    val followCardList: ArrayList<FollowCard> = ArrayList<FollowCard>()
    val itemList = squareCardCollection.itemList
    for (item in itemList) {
        followCardList.add(item.data)
    }

    val linearLayoutManager = LinearLayoutManager(context)
    linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

    viewHolder.squareCardRecyclerView.layoutManager = linearLayoutManager
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(viewHolder.squareCardRecyclerView)

    val adapter = FollowCardAdapter(context, followCardList)
    viewHolder.squareCardRecyclerView.adapter = adapter
}

fun bindFollowCardItemHolder(
    context: Context,
    followCard: FollowCard,
    holder: RecyclerView.ViewHolder
) {

}

fun bindTextCardItemHolder(
    context: Context,
    textCard: TextCard,
    holder: RecyclerView.ViewHolder
) {

}