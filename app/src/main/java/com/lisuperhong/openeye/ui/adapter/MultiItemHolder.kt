package com.lisuperhong.openeye.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.utils.Constant
import kotlinx.android.synthetic.main.item_auto_followcard.view.*
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.item_briefcard.view.*
import kotlinx.android.synthetic.main.item_video_detail_info.view.*
import kotlinx.android.synthetic.main.item_followcard.view.*
import kotlinx.android.synthetic.main.item_horizontalscrollcard.view.*
import kotlinx.android.synthetic.main.item_picturecard.view.*
import kotlinx.android.synthetic.main.item_squarecard_collection.view.*
import kotlinx.android.synthetic.main.item_textcard.view.*
import kotlinx.android.synthetic.main.item_videocollectionwithbrief.view.*
import kotlinx.android.synthetic.main.item_videosmallcard.view.*

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 14:35
 * Desc:
 */

fun createMultiViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    when (viewType) {
        Constant.ITEM_TYPE_SQUARECARD_COLLECTION -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_squarecard_collection, parent, false)
            return SquareCardItemHolder(view)
        }

        Constant.ITEM_TYPE_TEXT_CARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_textcard, parent, false)
            return TextCardItemHolder(view)
        }

        Constant.ITEM_TYPE_FOLLOW_CARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_followcard, parent, false)
            return FollowCardItemHolder(view)
        }

        Constant.ITEM_TYPE_VIDEO_SMALL_CARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_videosmallcard, parent, false)
            return VideoSmallCardItemHolder(view)
        }

        Constant.ITEM_TYPE_PICTURE_FOLLOW_CARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_picturecard, parent, false)
            return PictureFollowCardItemHolder(view)
        }

        Constant.ITEM_TYPE_AUTO_PLAY_FOLLOW_CARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_auto_followcard, parent, false)
            return AutoPlayFollowCardItemHolder(view)
        }

        Constant.ITEM_TYPE_BANNER -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_banner, parent, false)
            return BannerItemHolder(view)
        }

        Constant.ITEM_TYPE_HORIZONTAL_SCROLL_CARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_horizontalscrollcard, parent, false)
            return HorizontalScrollCardItemHolder(view)
        }

        Constant.ITEM_TYPE_BRIEF_CARD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_briefcard, parent, false)
            return BriefCardItemHolder(view)
        }

        Constant.ITEM_TYPE_VIDEO_COLLECTION_WITH_BRIEF -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_videocollectionwithbrief, parent, false)
            return VideoCollectionWithBriefItemHolder(view)
        }

        Constant.ITEM_TYPE_VIDEO_DETAIL -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_video_detail_info, parent, false)
            return VideoDetailInfoViewHolder(view)
        }

        Constant.ITEM_TYPE_AUTO_PLAY_VIDEO_AD -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_auto_play_video_ad, parent, false)
            return AutoPlayAdViewHolder(view)
        }

        else -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_empty, parent, false)
            return EmptyViewHolder(view)
        }
    }
}

class SquareCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var squareSubTitle: TextView = view.squareSubTitle
    var squareTitle: TextView = view.squareTitle
    var squareCardRecyclerView: RecyclerView = view.squareCardRecyclerView
}

class TextCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textCardHeaderLl: RelativeLayout = view.textCardHeaderLl
    var headerTv: TextView = view.headerTv
    var headerArrow: ImageView = view.headerArrow
    var footerTv: TextView = view.footerTv
}

class FollowCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var followCardCoverIv: ImageView = view.followCardCoverIv
    var followCardTimeTv: TextView = view.followCardTimeTv
    var followCardIconIv: ImageView = view.followCardIconIv
    var followCardTitle: TextView = view.followCardTitle
    var followCardSubTitle: TextView = view.followCardSubTitle
    var followCardDividerView: View = view.followCardDividerView
}

class VideoSmallCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var videoSmallCardLl: LinearLayout = view.videoSmallCardLl
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

class AutoPlayFollowCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var autoPlayCardLl: LinearLayout = view.autoPlayCardLl
    var autoPlayCardIconIv: ImageView = view.autoPlayCardIconIv
    var autoPlayCardOwnerTv: TextView = view.autoPlayCardOwnerTv
    var titlePgcTv: TextView = view.titlePgcTv
    var descriptionTv: TextView = view.playDescriptionTv
    var autoPlayCardCoverIv: ImageView = view.autoPlayCardCoverIv
    var collectionCountTv: TextView = view.playCollectionCountTv
    var replyCountTv: TextView = view.playReplyCountTv
}

class BannerItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var bannerIv: ImageView = view.bannerIv
}

class HorizontalScrollCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var bannerRecyclerView: RecyclerView = view.bannerRecyclerView
}

class BriefCardItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var briefCardLl: LinearLayout = view.briefCardLl
    var briefCardIconIv: ImageView = view.briefCardIconIv
    var briefCardTitleTv: TextView = view.briefCardTitleTv
    var briefCardDescriptionTv: TextView = view.briefCardDescriptionTv
}

class VideoCollectionWithBriefItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    var videoCollectionIconIv: ImageView = view.videoCollectionIconIv
    var videoCollectionTitleTv: TextView = view.videoCollectionTitleTv
    var videoCollectionDescriptionTv: TextView = view.videoCollectionDescriptionTv
    var videoCollectionRecyclerView: RecyclerView = view.videoCollectionRecyclerView
}

class VideoDetailInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var videoDetailTitle: TextView = view.videoDetailTitle
    var videoDetailCategory: TextView = view.videoDetailCategory
    var videoDetailDescription: TextView = view.videoDetailDescription
    var favoritesActionTv: TextView = view.favoritesActionTv
    var shareActionTv: TextView = view.shareActionTv
    var replyActionTv: TextView = view.replyActionTv
    var offlineActionTv: TextView = view.offlineActionTv
    var firstTagIv: ImageView = view.firstTagIv
    var firstTagTv: TextView = view.firstTagTv
    var secondTagIv: ImageView = view.secondTagIv
    var secondTagTv: TextView = view.secondTagTv
    var thirdTagIv: ImageView = view.thirdTagIv
    var thirdTagTv: TextView = view.thirdTagTv
    var authorIconIv: ImageView = view.authorIconIv
    var authorName: TextView = view.authorName
    var authorDescription: TextView = view.authorDescription
}

class AutoPlayAdViewHolder(view: View) : RecyclerView.ViewHolder(view)

class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)


