package com.lisuperhong.openeye.mvp.model.bean


data class SquareCard(
    var dataType: String,
    var id: Int,
    var title: String,
    var image: String,
    var actionUrl: String,
    var shade: Boolean,
    var adTrack: Any?
)