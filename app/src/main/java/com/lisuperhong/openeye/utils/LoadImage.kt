package com.lisuperhong.openeye.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/6 18:10
 * Desc:
 */
object LoadImage {

    fun loadImage() {

    }

    private fun load(context: Context, imageView: ImageView, url: String, options: RequestOptions) {
        Glide.with(context)
            .load(url)
            .apply(options)
            .into(object : DrawableImageViewTarget(imageView) {})
    }
}