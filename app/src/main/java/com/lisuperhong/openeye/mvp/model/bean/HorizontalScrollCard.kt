package com.lisuperhong.openeye.mvp.model.bean


data class HorizontalScrollCard(
    var dataType: String,
    var itemList: ArrayList<Item>,
    var count: Int
) {

    data class Item(
        var type: String,
        var data: Banner,
        var tag: Any?,
        var id: Int,
        var adIndex: Int
    )
}