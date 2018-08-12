package com.lisuperhong.openeye.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.lisuperhong.openeye.BaseApplication
import com.lisuperhong.openeye.R

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 18:10
 * Desc:
 */
object ImageLoad {

    fun loadImage(imageView: ImageView, url: String) {
        val requestOptions = RequestOptions().centerCrop()
            .placeholder(R.drawable.eye_loading_icon)
            .error(R.drawable.eye_loading_icon)
            .transform(CenterCrop())
            .format(DecodeFormat.PREFER_RGB_565)
            .priority(Priority.NORMAL)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .dontAnimate()

        load(imageView, url, requestOptions)
    }

    fun loadImage(imageView: ImageView, url: String, round: Int) {
        val requestOptions = RequestOptions().centerCrop()
            .placeholder(R.drawable.eye_loading_icon)
            .error(R.drawable.eye_loading_icon)
            .transforms(CenterCrop(), RoundedCorners(DensityUtil.dip2px(BaseApplication.context, round.toFloat())))
            .format(DecodeFormat.PREFER_RGB_565)
            .priority(Priority.NORMAL)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        load(imageView, url, requestOptions)
    }

    fun loadCircleImage(imageView: ImageView, url: String) {
        val lp = imageView.layoutParams
        lp.width = DensityUtil.dip2px(BaseApplication.context, 36f)
        lp.height = DensityUtil.dip2px(BaseApplication.context, 36f)
        imageView.layoutParams = lp
        val requestOptions = RequestOptions().centerCrop()
            .placeholder(R.drawable.eye_loading_icon)
            .error(R.drawable.eye_loading_icon)
            .format(DecodeFormat.PREFER_RGB_565)
            .transform(CenterCrop())
            .dontAnimate()
            .override(lp.width)
            .priority(Priority.NORMAL)
            .transform(CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        load(imageView, url, requestOptions)
    }

    fun loadImage(imageView: ImageView, url: String, width: Int, height: Int) {
        val lp = imageView.layoutParams
        lp.width = width
        lp.height = height
        imageView.layoutParams = lp
        val requestOptions = RequestOptions().centerCrop()
            .placeholder(R.drawable.eye_loading_icon)
            .error(R.drawable.eye_loading_icon)
            .transforms(CenterCrop())
            .override(width, height)
            .format(DecodeFormat.PREFER_RGB_565)
            .priority(Priority.NORMAL)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        load(imageView, url, requestOptions)
    }

    fun loadImage(imageView: ImageView, url: String, width: Int, height: Int, round: Int) {
        val lp = imageView.layoutParams
        lp.width = width
        lp.height = height
        imageView.layoutParams = lp
        val requestOptions = RequestOptions().centerCrop()
            .placeholder(R.drawable.eye_loading_icon)
            .error(R.drawable.eye_loading_icon)
            .transforms(CenterCrop(), RoundedCorners(DensityUtil.dip2px(BaseApplication.context, round.toFloat())))
            .override(width, height)
            .format(DecodeFormat.PREFER_RGB_565)
            .priority(Priority.NORMAL)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        load(imageView, url, requestOptions)
    }

    private fun load(imageView: ImageView, url: String, options: RequestOptions) {
        Glide.with(imageView)
            .load(url)
            .apply(options)
            .into(object : DrawableImageViewTarget(imageView) {})
    }
}