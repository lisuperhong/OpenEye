package com.lisuperhong.openeye.ui.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.*
import com.lisuperhong.openeye.utils.*

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
    if (header.font == "bigBold") {
        viewHolder.squareTitle.typeface =
            TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        viewHolder.squareTitle.text = header.title
    }
    if (header.subTitleFont == "lobster") {
        viewHolder.squareSubTitle.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.DCB)
        viewHolder.squareSubTitle.text = header.subTitle
    }

    viewHolder.squareTitle.setOnClickListener {
        JumpActivityUtil.parseActionUrl(context, header.actionUrl)
    }

    val followCardList: ArrayList<FollowCard> = ArrayList<FollowCard>()
    val itemList = squareCardCollection.itemList
    for (item in itemList) {
        followCardList.add(item.data)
    }

    val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    viewHolder.squareCardRecyclerView.layoutManager = linearLayoutManager
    viewHolder.squareCardRecyclerView.onFlingListener = null
    LinearSnapHelper().attachToRecyclerView(viewHolder.squareCardRecyclerView)

    val adapter = FollowCardAdapter(context, followCardList)
    viewHolder.squareCardRecyclerView.adapter = adapter
}

fun bindSquareCardItemHolder(
    context: Context,
    squareCardCollection2: SquareCardCollection2,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: SquareCardItemHolder = holder as SquareCardItemHolder
    val header = squareCardCollection2.header
    if (header.font == "bold") {
        viewHolder.squareTitle.typeface =
            TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
        viewHolder.squareTitle.textSize = 22f
        viewHolder.squareTitle.text = header.title
    }
    viewHolder.squareSubTitle.visibility = View.GONE
    viewHolder.squareTitle.setOnClickListener {
        JumpActivityUtil.parseActionUrl(context, header.actionUrl)
    }

    val banners: ArrayList<Banner> = ArrayList<Banner>()
    val itemList = squareCardCollection2.itemList
    for (item in itemList) {
        banners.add(item.data)
    }
    val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    viewHolder.squareCardRecyclerView.layoutManager = linearLayoutManager
    viewHolder.squareCardRecyclerView.onFlingListener = null
    LinearSnapHelper().attachToRecyclerView(viewHolder.squareCardRecyclerView)

    val adapter = HorizontalBannerAdapter(context, banners)
    viewHolder.squareCardRecyclerView.adapter = adapter
}

fun bindTextCardItemHolder(
    context: Context,
    textCard: TextCard,
    holder: RecyclerView.ViewHolder,
    isDetail: Boolean
) {
    val viewHolder: TextCardItemHolder = holder as TextCardItemHolder
    viewHolder.headerTv.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.footerTv.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    if (isDetail) {
        viewHolder.headerTv.textSize = 14f
        viewHolder.headerTv.setTextColor(context.resources.getColor(R.color.white))
    }

    when (textCard.type) {
        "header4" -> {
            viewHolder.textCardHeaderLl.visibility = View.VISIBLE
            viewHolder.footerTv.visibility = View.GONE
            viewHolder.headerTv.text = textCard.text
        }

        "header5" -> {
            viewHolder.textCardHeaderLl.visibility = View.VISIBLE
            viewHolder.footerTv.visibility = View.GONE
            viewHolder.headerTv.text = textCard.text
        }

        "footer2" -> {
            viewHolder.textCardHeaderLl.visibility = View.GONE
            viewHolder.footerTv.visibility = View.VISIBLE
            viewHolder.footerTv.text = textCard.text
        }

        "footer3" -> {
            viewHolder.textCardHeaderLl.visibility = View.GONE
            viewHolder.footerTv.visibility = View.VISIBLE
            viewHolder.footerTv.text = textCard.text
        }
    }

    if (textCard.actionUrl == null || isDetail) {
        viewHolder.headerArrow.visibility = View.GONE
    } else {
        viewHolder.textCardHeaderLl.setOnClickListener {
            JumpActivityUtil.parseActionUrl(context, textCard.actionUrl!!)
        }

        viewHolder.footerTv.setOnClickListener {
            JumpActivityUtil.parseActionUrl(context, textCard.actionUrl!!)
        }
    }
}

fun bindFollowCardItemHolder(
    context: Context,
    followCard: FollowCard,
    holder: RecyclerView.ViewHolder,
    show: Boolean
) {
    val viewHolder: FollowCardItemHolder = holder as FollowCardItemHolder
    val header = followCard.header
    viewHolder.followCardTitle.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.followCardTitle.text = header.title
    viewHolder.followCardSubTitle.text = header.description
    when (header.iconType) {
        "round" -> ImageLoad.loadCircleImage(viewHolder.followCardIconIv, header.icon)
        "square" -> ImageLoad.loadImage(viewHolder.followCardIconIv, header.icon, 5)
        else -> ImageLoad.loadImage(viewHolder.followCardIconIv, header.icon)
    }

    val content = followCard.content
    val data = content.data
    val cover = data.cover
    ImageLoad.loadImage(viewHolder.followCardCoverIv, cover.feed, 5)
    viewHolder.followCardTimeTv.text = TimeUtil.secToTime(data.duration)
    if (show) {
        viewHolder.followCardDividerView.visibility = View.VISIBLE
    }

    viewHolder.followCardCoverIv.setOnClickListener {
        JumpActivityUtil.startVideoDetail(
            context as Activity,
            viewHolder.followCardCoverIv,
            content.data
        )
    }
}

fun bindVideoSmallCardItemHolder(
    context: Context,
    videoSmallCard: VideoSmallCard,
    holder: RecyclerView.ViewHolder,
    hide: Boolean
) {
    val viewHolder: VideoSmallCardItemHolder = holder as VideoSmallCardItemHolder
    viewHolder.videoSmallCardTitle.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.videoSmallCardTitle.text = videoSmallCard.title
    viewHolder.videoSmallCardSubTitle.text = "#" + videoSmallCard.category
    val cover = videoSmallCard.cover
    ImageLoad.loadImage(viewHolder.videoSmallCardIv, cover.feed, 5)
    viewHolder.videoSmallCardTimeTv.text = TimeUtil.secToTime(videoSmallCard.duration)
    if (hide) {
        viewHolder.dividerView.visibility = View.GONE
    }

    viewHolder.videoSmallCardLl.setOnClickListener {
        JumpActivityUtil.startVideoDetail(
            context as Activity,
            viewHolder.videoSmallCardIv,
            videoSmallCard
        )
    }
}

fun bindPictureFollowCardItemHolder(
    context: Context,
    pictureFollowCard: PictureFollowCard,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: PictureFollowCardItemHolder = holder as PictureFollowCardItemHolder
    val header = pictureFollowCard.header
    viewHolder.pictureCardOwner.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.pictureCardOwner.text = header.issuerName
    when (header.iconType) {
        "round" -> ImageLoad.loadCircleImage(viewHolder.pictureCardIconTv, header.icon)
        "square" -> ImageLoad.loadImage(viewHolder.pictureCardIconTv, header.icon, 5)
        else -> ImageLoad.loadImage(viewHolder.pictureCardIconTv, header.icon)
    }

    val content = pictureFollowCard.content
    val data = content.data
    viewHolder.descriptionTv.text = data.description
    val cover = data.cover
    ImageLoad.loadImage(viewHolder.pictureCardCoverIv, cover.feed, 5)
    val consumption = data.consumption
    viewHolder.collectionCountTv.text = consumption.collectionCount.toString()
    viewHolder.replyCountTv.text = consumption.replyCount.toString()
}

fun bindAutoPlayFollowCardItemHolder(
    context: Context,
    autoPlayFollowCard: AutoPlayFollowCard,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: AutoPlayFollowCardItemHolder = holder as AutoPlayFollowCardItemHolder
    val header = autoPlayFollowCard.header
    viewHolder.autoPlayCardOwnerTv.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.titlePgcTv.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.autoPlayCardOwnerTv.text = header.issuerName
    when (header.iconType) {
        "round" -> ImageLoad.loadCircleImage(viewHolder.autoPlayCardIconIv, header.icon)
        "square" -> ImageLoad.loadImage(viewHolder.autoPlayCardIconIv, header.icon, 5)
        else -> ImageLoad.loadImage(viewHolder.autoPlayCardIconIv, header.icon)
    }

    val content = autoPlayFollowCard.content
    val data = content.data
    viewHolder.titlePgcTv.text = data.title
    viewHolder.descriptionTv.text = data.description
    val cover = data.cover
    val consumption = data.consumption
    viewHolder.collectionCountTv.text = consumption.collectionCount.toString()
    viewHolder.replyCountTv.text = consumption.replyCount.toString()
    ImageLoad.loadImage(viewHolder.autoPlayCardCoverIv, cover.feed, 5)

    viewHolder.autoPlayCardLl.setOnClickListener {
        JumpActivityUtil.startVideoDetail(context as Activity, viewHolder.autoPlayCardCoverIv, data)
    }
}

fun bindBannerItemHolder(
    context: Context,
    banner: Banner,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: BannerItemHolder = holder as BannerItemHolder
    ImageLoad.loadImage(viewHolder.bannerIv, banner.image, 5)
    viewHolder.bannerIv.setOnClickListener {
        JumpActivityUtil.parseActionUrl(context, banner.actionUrl)
    }
}

fun bindHorizontalScrollCardItemHolder(
    context: Context,
    horizontalScrollCard: HorizontalScrollCard,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: HorizontalScrollCardItemHolder = holder as HorizontalScrollCardItemHolder
    val banners: ArrayList<Banner> = ArrayList<Banner>()
    val itemList = horizontalScrollCard.itemList
    for (item in itemList) {
        banners.add(item.data)
    }
    val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    viewHolder.bannerRecyclerView.layoutManager = linearLayoutManager
    viewHolder.bannerRecyclerView.onFlingListener = null
    LinearSnapHelper().attachToRecyclerView(viewHolder.bannerRecyclerView)

    val adapter = HorizontalBannerAdapter(context, banners)
    viewHolder.bannerRecyclerView.adapter = adapter
}

fun bindBriefCardItemHolder(
    context: Context,
    briefCard: BriefCard,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: BriefCardItemHolder = holder as BriefCardItemHolder
    viewHolder.briefCardTitleTv.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.briefCardTitleTv.text = briefCard.title
    viewHolder.briefCardDescriptionTv.text = briefCard.description
    when (briefCard.iconType) {
        "round" -> ImageLoad.loadCircleImage(viewHolder.briefCardIconIv, briefCard.icon)
        "square" -> ImageLoad.loadImage(viewHolder.briefCardIconIv, briefCard.icon, 5)
        else -> ImageLoad.loadImage(viewHolder.briefCardIconIv, briefCard.icon)
    }

    viewHolder.briefCardLl.setOnClickListener {
        JumpActivityUtil.parseActionUrl(context, briefCard.actionUrl)
    }
}

fun bindVideoCollectionWithBriefItemHolder(
    context: Context,
    videoCollectionWithBrief: VideoCollectionWithBrief,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: VideoCollectionWithBriefItemHolder =
        holder as VideoCollectionWithBriefItemHolder
    val header = videoCollectionWithBrief.header
    viewHolder.videoCollectionTitleTv.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.videoCollectionTitleTv.text = header.title
    viewHolder.videoCollectionDescriptionTv.text = header.description
    when (header.iconType) {
        "round" -> ImageLoad.loadCircleImage(viewHolder.videoCollectionIconIv, header.icon)
        "square" -> ImageLoad.loadImage(viewHolder.videoCollectionIconIv, header.icon, 5)
        else -> ImageLoad.loadImage(viewHolder.videoCollectionIconIv, header.icon)
    }

    val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    viewHolder.videoCollectionRecyclerView.layoutManager = linearLayoutManager
    viewHolder.videoCollectionRecyclerView.onFlingListener = null
    LinearSnapHelper().attachToRecyclerView(viewHolder.videoCollectionRecyclerView)

    val adapter = VideoCollectionAdapter(context, videoCollectionWithBrief.itemList)
    viewHolder.videoCollectionRecyclerView.adapter = adapter
}

fun bindVideoDetailInfoHolder(
    context: Context,
    videoSmallCard: VideoSmallCard,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: VideoDetailInfoViewHolder = holder as VideoDetailInfoViewHolder

    viewHolder.videoDetailTitle.typeface =
        TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.videoDetailTitle.text = videoSmallCard.title
    viewHolder.videoDetailCategory.text = "#${videoSmallCard.category}"
    viewHolder.videoDetailDescription.text = videoSmallCard.description

    val consumption = videoSmallCard.consumption
    viewHolder.favoritesActionTv.text = consumption.collectionCount.toString()
    viewHolder.shareActionTv.text = consumption.shareCount.toString()
    viewHolder.replyActionTv.text = consumption.replyCount.toString()

    val tags = videoSmallCard.tags
    viewHolder.firstTagTv.text = "#${tags[0].name}#"
    ImageLoad.loadImage(viewHolder.firstTagIv, tags[0].headerImage, 5)
    viewHolder.secondTagTv.text = "#${tags[1].name}#"
    ImageLoad.loadImage(viewHolder.secondTagIv, tags[1].headerImage, 5)
    viewHolder.thirdTagTv.text = "#${tags[2].name}#"
    ImageLoad.loadImage(viewHolder.thirdTagIv, tags[2].headerImage, 5)

    viewHolder.firstTagIv.setOnClickListener {
        JumpActivityUtil.parseActionUrl(context, tags[0].actionUrl)
    }
    viewHolder.secondTagIv.setOnClickListener {
        JumpActivityUtil.parseActionUrl(context, tags[1].actionUrl)
    }
    viewHolder.thirdTagIv.setOnClickListener {
        JumpActivityUtil.parseActionUrl(context, tags[2].actionUrl)
    }

    val author = videoSmallCard.author
    viewHolder.authorName.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.authorName.text = author.name
    viewHolder.authorDescription.text = author.description
    ImageLoad.loadCircleImage(viewHolder.authorIconIv, author.icon)
}
