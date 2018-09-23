package com.lisuperhong.openeye.mvp.model.bean


data class CategoryTabInfo(
    var categoryInfo: CategoryInfo,
    var tabInfo: TabInfo,
    var tagInfo: TagInfo
) {

    data class TagInfo(
        var dataType: String,
        var id: Int,
        var name: String,
        var description: String,
        var headerImage: String,
        var bgPicture: String,
        var actionUrl: String?,
        var recType: Int,
        var follow: Follow?,
        var tagFollowCount: Int,
        var tagVideoCount: Int,
        var tagDynamicCount: Int
    )


    data class CategoryInfo(
        var dataType: String,
        var id: Int,
        var name: String,
        var description: String,
        var headerImage: String,
        var actionUrl: String,
        var follow: Follow
    )

    data class TabInfo(
        var tabList: List<Tab>,
        var defaultIdx: Int
    ) {

        data class Tab(
            var id: Int,
            var name: String,
            var apiUrl: String
        )
    }

    data class Follow(
        var itemType: String,
        var itemId: Int,
        var followed: Boolean
    )
}