package com.lisuperhong.openeye.mvp.model.bean


data class TabInfoBean(
    var tabInfo: TabInfo
) {

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