package com.lisuperhong.openeye.mvp.model.bean


data class Banner(
    var dataType: String,
    var id: Int,
    var title: String,
    var description: String,
    var image: String,
    var actionUrl: String,
    var adTrack: Any?,
    var shade: Boolean,
    var label: Label?,
    var labelList: List<Item>,
    var header: Header?
) {
    data class Header(
        var id: Int,
        var title: String?,
        var font: String?,
        var subTitle: String?,
        var subTitleFont: String?,
        var textAlign: String,
        var cover: Any?,
        var label: Any?,
        var actionUrl: String?,
        var labelList: Any?,
        var icon: Any?,
        var description: String?
    )

    data class Label(
        var text: String,
        var card: String,
        var detail: Any?
    )

    data class Item(
        var text: String,
        var actionUrl: String?
    )
}