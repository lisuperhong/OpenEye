package com.lisuperhong.openeye.mvp.model.bean


data class AutoPlayFollowCard(
    var dataType: String,
    var header: Header,
    var content: VideoBeanForClient,
    var adTrack: Any?
) {
    data class Header(
        var id: Int,
        var actionUrl: String,
        var labelList: Any?,
        var icon: String,
        var iconType: String,
        var time: Long,
        var showHateVideo: Boolean,
        var followType: String,
        var tagId: Int,
        var tagName: Any?,
        var issuerName: String
    )
}