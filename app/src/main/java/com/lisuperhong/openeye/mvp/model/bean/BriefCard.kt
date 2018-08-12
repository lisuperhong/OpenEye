package com.lisuperhong.openeye.mvp.model.bean


data class BriefCard(
    var dataType: String,
    var id: Int,
    var icon: String,
    var iconType: String,
    var title: String,
    var subTitle: Any?,
    var description: String,
    var actionUrl: String,
    var adTrack: Any?,
    var follow: Follow,
    var ifPgc: Boolean
) {

    data class Follow(
        var itemType: String,
        var itemId: Int,
        var followed: Boolean
    )
}