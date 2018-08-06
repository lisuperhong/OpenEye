package com.lisuperhong.openeye.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.utils.Constant
import kotlinx.android.synthetic.main.list_followcard_item.view.*
import kotlinx.android.synthetic.main.list_squarecard_collection_item.view.*

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 14:35
 * Desc:
 */

fun createMultiViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    when (viewType) {
        Constant.ITEM_TYPE_SQUARECARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_squarecard_collection_item, parent, false)
            return SquareCardItemHolder(view)
        }

        Constant.ITEM_TYPE_FLLOWCARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_followcard_item, parent, false)
            return FollowCardItemHolder(view)
        }

        else -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_squarecard_collection_item, parent, false)
            return EmptyItemHolder(view)
        }
    }
}

class SquareCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var squareSubTitle: TextView = view.squareSubTitle
    var squareTitle: TextView = view.squareTitle
    var squareCardRecyclerView: RecyclerView = view.squareCardRecyclerView
}

class FollowCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var followCardCoverIv: ImageView = view.followCardCoverIv
    var followCardTimeTv: TextView = view.followCardTimeTv
    var followCardIconIv: ImageView = view.followCardIconIv
    var followCardTitle: TextView = view.followCardTitle
    var followCardSubTitle: TextView = view.followCardSubTitle
}

class EmptyItemHolder(view: View) : RecyclerView.ViewHolder(view)