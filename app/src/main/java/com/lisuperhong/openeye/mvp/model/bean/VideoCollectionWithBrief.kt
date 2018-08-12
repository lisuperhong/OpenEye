package com.lisuperhong.openeye.mvp.model.bean


data class VideoCollectionWithBrief(
    var dataType: String,
    var header: Header,
    var itemList: ArrayList<VideoBeanForClient>,
    var count: Int,
    var adTrack: Any?
) {
    data class Header(
        var id: Int,
        var icon: String,
        var iconType: String?,
        var title: String?,
        var subTitle: String?,
        var description: String,
        var actionUrl: String?,
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
}