package com.lisuperhong.openeye.mvp.model.bean


data class VideoBeanForClient(
    var type: String,
    var data: VideoSmallCard,
    var tag: Any?,
    var id: Int,
    var adIndex: Int
)