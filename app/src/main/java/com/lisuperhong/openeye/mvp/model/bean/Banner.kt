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
    var labelList: List<Any>?,
    var header: Header?
) {
    data class Header(
        var id: Int,
        var title: Any?,
        var font: Any?,
        var subTitle: Any?,
        var subTitleFont: Any?,
        var textAlign: String,
        var cover: Any?,
        var label: Any?,
        var actionUrl: Any?,
        var labelList: Any?,
        var icon: Any?,
        var description: Any?
    )

    data class Label(
        var text: String,
        var card: String,
        var detail: Any?
    )
}