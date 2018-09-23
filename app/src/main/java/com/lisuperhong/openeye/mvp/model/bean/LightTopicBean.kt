package com.lisuperhong.openeye.mvp.model.bean

import java.io.Serializable

/**
 * Author: lisuperhong
 * Time: Create on 2018/9/23 17:05
 * Github: https://github.com/lisuperhong
 * Desc:
 */
data class LightTopicBean(
    var id: Int,
    var headerImage: String,
    var brief: String,
    var text: String,
    var shareLink: String,
    var itemList: ArrayList<BaseBean.Item>,
    var count: Int,
    var adTrack: Any?
) : Serializable