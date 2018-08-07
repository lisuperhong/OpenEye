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
import kotlinx.android.synthetic.main.list_picturecard_item.view.*
import kotlinx.android.synthetic.main.list_squarecard_collection_item.view.*
import kotlinx.android.synthetic.main.list_textcard_item.view.*
import kotlinx.android.synthetic.main.list_videosmallcard_item.view.*

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


        Constant.ITEM_TYPE_TEXTCARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_textcard_item, parent, false)
            return TextCardItemHolder(view)
        }

        Constant.ITEM_TYPE_FLLOWCARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_followcard_item, parent, false)
            return FollowCardItemHolder(view)
        }

        Constant.ITEM_TYPE_VIDEOSMALLCARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_videosmallcard_item, parent, false)
            return VideoSmallCardItemHolder(view)
        }

        Constant.ITEM_TYPE_PICTUREFOLLOWCARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_picturecard_item, parent, false)
            return PictureFollowCardItemHolder(view)
        }

        else -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_textcard_item, parent, false)
            return TextCardItemHolder(view)
        }
    }
}

class SquareCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var squareSubTitle: TextView = view.squareSubTitle
    var squareTitle: TextView = view.squareTitle
    var squareCardRecyclerView: RecyclerView = view.squareCardRecyclerView
}

class TextCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var headerTv: TextView = view.headerTv
    var footerTv: TextView = view.footerTv
}

class FollowCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var followCardCoverIv: ImageView = view.followCardCoverIv
    var followCardTimeTv: TextView = view.followCardTimeTv
    var followCardIconIv: ImageView = view.followCardIconIv
    var followCardTitle: TextView = view.followCardTitle
    var followCardSubTitle: TextView = view.followCardSubTitle
}

class VideoSmallCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var videoSmallCardIv: ImageView = view.videoSmallCardIv
    var videoSmallCardTimeTv: TextView = view.videoSmallCardTimeTv
    var videoSmallCardTitle: TextView = view.videoSmallCardTitle
    var videoSmallCardSubTitle: TextView = view.videoSmallCardSubTitle
    var dividerView: View = view.dividerView
}

class PictureFollowCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var pictureCardIconTv: ImageView = view.pictureCardIconIv
    var pictureCardOwner: TextView = view.pictureCardOwner
    var descriptionTv: TextView = view.descriptionTv
    var pictureCardCoverIv: ImageView = view.pictureCardCoverIv
    var collectionCountTv: TextView = view.collectionCountTv
    var replyCountTv: TextView = view.replyCountTv
}
