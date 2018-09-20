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
        var description: Any?,
        var headerImage: Any?,
        var bgPicture: Any?,
        var actionUrl: Any?,
        var recType: Int,
        var follow: Any?,
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
    ) {

        data class Follow(
            var itemType: String,
            var itemId: Int,
            var followed: Boolean
        )
    }


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
}