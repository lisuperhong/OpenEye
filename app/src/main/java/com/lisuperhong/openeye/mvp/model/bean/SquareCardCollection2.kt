package com.lisuperhong.openeye.mvp.model.bean

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/12 11:54
 * Github: https://github.com/lisuperhong
 * Desc:
 */

data class SquareCardCollection2(
    var dataType: String,
    var header: Header,
    var itemList: List<Item>,
    var count: Int,
    var adTrack: Any?
) {
    data class Item(
        var type: String,
        var data: Banner,
        var tag: Any?,
        var id: Int,
        var adIndex: Int
    )

    data class Header(
        var id: Int,
        var title: String,
        var font: String,
        var subTitle: String,
        var subTitleFont: String,
        var textAlign: String,
        var cover: Any?,
        var label: Any?,
        var actionUrl: String,
        var labelList: Any?
    )
}