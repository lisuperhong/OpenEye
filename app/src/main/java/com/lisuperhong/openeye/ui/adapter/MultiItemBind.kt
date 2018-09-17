package com.lisuperhong.openeye.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.lisuperhong.openeye.R
import com.lisuperhong.openeye.mvp.model.bean.*
import com.lisuperhong.openeye.ui.activity.VideoDetailActivity
import com.lisuperhong.openeye.utils.Constant
import com.lisuperhong.openeye.utils.ImageLoad
import com.lisuperhong.openeye.utils.TimeUtil
import com.lisuperhong.openeye.utils.TypefaceUtil
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import java.text.FieldPosition

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

    viewHolder.squareTitle.setOnClickListener(View.OnClickListener {
    })

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
    if (textCard.actionUrl == null || isDetail) {
        viewHolder.headerArrow.visibility = View.GONE
    }

    if (textCard.type == "footer2") {
        viewHolder.textCardHeaderLl.visibility = View.GONE
        viewHolder.footerTv.visibility = View.VISIBLE
        viewHolder.footerTv.text = textCard.text
    } else {
        viewHolder.textCardHeaderLl.visibility = View.VISIBLE
        viewHolder.footerTv.visibility = View.GONE
        viewHolder.headerTv.text = textCard.text
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
        startVideoDetail(context as Activity, viewHolder.followCardCoverIv, content.data)
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
        startVideoDetail(context as Activity, viewHolder.videoSmallCardIv, videoSmallCard)
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
    holder: RecyclerView.ViewHolder,
    position: Int
) {
    val viewHolder: AutoPlayFollowCardItemHolder = holder as AutoPlayFollowCardItemHolder
    val header = autoPlayFollowCard.header
    viewHolder.autoPlayCardOwnerTv.typeface =
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

    //增加封面
    val imageView = ImageView(context)
    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
    ImageLoad.loadImage(imageView, cover.feed)

    val optionBuilder = GSYVideoOptionBuilder()
    optionBuilder.setIsTouchWiget(false)
        .setThumbImageView(imageView)
        .setUrl(data.playUrl)
        .setSetUpLazy(true)//lazy可以防止滑动卡顿
        .setVideoTitle(data.title)
        .setCacheWithPlay(true)
        .setRotateViewAuto(true)
        .setLockLand(true)
        .setPlayTag(Constant.AUTO_PLAY_TAG)
        .setShowFullAnimation(true)
        .setNeedLockFull(true)
        .setPlayPosition(position)
        .setReleaseWhenLossAudio(false)
        .setVideoAllCallBack(object : GSYSampleCallBack() {

            override fun onPrepared(url: String, vararg objects: Any) {
                super.onPrepared(url, objects)
                if (!viewHolder.autoPlayer.isIfCurrentIsFullscreen) {
                    //静音
                    GSYVideoManager.instance().isNeedMute = true
                }
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, objects)
                //全屏不静音
                GSYVideoManager.instance().isNeedMute = true
            }

            override fun onEnterFullscreen(url: String?, vararg objects: Any) {
                super.onEnterFullscreen(url, *objects)
                GSYVideoManager.instance().isNeedMute = false
                viewHolder.autoPlayer.getCurrentPlayer().getTitleTextView().setText(objects[0] as String)
            }
        }).build(viewHolder.autoPlayer)

    //增加title
    viewHolder.autoPlayer.titleTextView.visibility = View.GONE
    //设置返回键
    viewHolder.autoPlayer.backButton.visibility = View.GONE
    //设置全屏按键功能
    viewHolder.autoPlayer.fullscreenButton.visibility = View.GONE

    viewHolder.autoPlayCardLl.setOnClickListener {
        startVideoDetail(context as Activity, viewHolder.autoPlayer, data)
    }
}

fun bindBannerItemHolder(
    context: Context,
    banner: Banner,
    holder: RecyclerView.ViewHolder
) {
    val viewHolder: BannerItemHolder = holder as BannerItemHolder
    ImageLoad.loadImage(viewHolder.bannerIv, banner.image, 5)
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
    viewHolder.briefcardTitleTv.typeface =
            TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.briefcardTitleTv.text = briefCard.title
    viewHolder.briefcardDescriptionTv.text = briefCard.description
    when (briefCard.iconType) {
        "round" -> ImageLoad.loadCircleImage(viewHolder.briefcardIconIv, briefCard.icon)
        "square" -> ImageLoad.loadImage(viewHolder.briefcardIconIv, briefCard.icon, 5)
        else -> ImageLoad.loadImage(viewHolder.briefcardIconIv, briefCard.icon)
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

fun bindVideoDetailInfoHolder(videoSmallCard: VideoSmallCard, holder: RecyclerView.ViewHolder) {
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

    val author = videoSmallCard.author
    viewHolder.authorName.typeface = TypefaceUtil.getTypefaceFromAsset(TypefaceUtil.FZLanTingCuHei)
    viewHolder.authorName.text = author.name
    viewHolder.authorDescription.text = author.description
    ImageLoad.loadCircleImage(viewHolder.authorIconIv, author.icon)
}

fun startVideoDetail(activity: Activity, view: View, videoSmallCard: VideoSmallCard) {
    val intent = Intent(activity, VideoDetailActivity::class.java)
    intent.putExtra(Constant.INTENT_VIDEO_DETAIL, videoSmallCard)
    intent.putExtra(VideoDetailActivity.FROM_TRANSITION, true)
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        val pair = Pair<View, String>(view, VideoDetailActivity.IMG_TRANSITION)
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            activity, pair
        )
        ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
    } else {
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
    }
}