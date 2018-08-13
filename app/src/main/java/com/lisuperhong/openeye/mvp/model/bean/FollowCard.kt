package com.lisuperhong.openeye.mvp.model.bean


data class FollowCard(
    var dataType: String,
    var header: Header,
    var content: Content,
    var adTrack: Any?
) {
    data class Header(
        var id: Int,
        var title: String,
        var font: Any?,
        var subTitle: Any?,
        var subTitleFont: Any?,
        var textAlign: String,
        var cover: Any?,
        var label: Any?,
        var actionUrl: String,
        var labelList: Any?,
        var icon: String,
        var iconType: String,
        var description: String,
        var time: Long,
        var showHateVideo: Boolean
    )

    data class Content(
        var type: String,
        var data: VideoSmallCard,
        var tag: Any?,
        var id: Int,
        var adIndex: Int
    )
}