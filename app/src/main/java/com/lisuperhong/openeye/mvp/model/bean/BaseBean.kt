package com.lisuperhong.openeye.mvp.model.bean

import java.io.Serializable

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/1 19:23
 * Desc:
 */
data class BaseBean(
    var count: Int,
    var total: Int,
    var nextPageUrl: String,
    var adExist: Boolean,
    var itemList: List<Item>
) : Serializable {
    data class Item(
        var type: String,
        var tag: String?,
        var id: Int,
        var adIndex: Int,
        var data: Any
    ) : Serializable {
        override fun toString(): String {
            return "Item{type:$type, tag:$tag, id:$id, adIndex:$adIndex, data:$data)"
        }
    }

    override fun toString(): String {
        return "BaseBean{itemList:${itemList.forEach { it -> it.toString() }}, nextPageUrl:$nextPageUrl, count:$count, total:$total, adExist:$adExist}"
    }
}