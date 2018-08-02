package com.lisuperhong.openeye.mvp.model.bean


data class TextCard(
    var dataType: String,
    var id: Int,
    var type: String,
    var text: String,
    var subTitle: Any?,
    var actionUrl: String,
    var adTrack: Any?,
    var follow: Follow?
) {
    data class Follow(
        var itemType: String,
        var itemId: Int,
        var followed: Boolean
    )
}