package com.lisuperhong.openeye.mvp.model.bean


data class AutoPlayFollowCard(
    var dataType: String,
    var header: Header,
    var content: Content,
    var adTrack: Any?
) {

    data class Content(
        var type: String,
        var data: Data,
        var tag: Any?,
        var id: Int,
        var adIndex: Int
    ) {

        data class Data(
            var dataType: String,
            var id: Int,
            var title: String,
            var description: String,
            var library: String,
            var tags: List<Tag>,
            var consumption: Consumption,
            var resourceType: String,
            var uid: Int,
            var createTime: Long,
            var updateTime: Long,
            var collected: Boolean,
            var owner: Owner,
            var selectedTime: Long,
            var checkStatus: String,
            var validateStatus: String,
            var validateResult: String,
            var playUrl: String,
            var cover: Cover,
            var status: String,
            var releaseTime: Long,
            var duration: Int,
            var transId: Any?,
            var type: String,
            var validateTaskId: String
        ) {

            data class Tag(
                var id: Int,
                var name: String,
                var actionUrl: String,
                var adTrack: Any?,
                var desc: Any?,
                var bgPicture: String,
                var headerImage: String,
                var tagRecType: String
            )


            data class Cover(
                var feed: String,
                var detail: String,
                var blurred: Any?,
                var sharing: Any?,
                var homepage: Any?
            )


            data class Consumption(
                var collectionCount: Int,
                var shareCount: Int,
                var replyCount: Int
            )


            data class Owner(
                var uid: Int,
                var nickname: String,
                var avatar: String,
                var userType: String,
                var ifPgc: Boolean,
                var description: Any?,
                var area: Any?,
                var gender: Any?,
                var registDate: Long,
                var releaseDate: Long,
                var cover: Any?,
                var actionUrl: String,
                var followed: Boolean,
                var limitVideoOpen: Boolean,
                var library: String,
                var uploadStatus: String,
                var bannedDate: Any?,
                var bannedDays: Any?
            )
        }
    }


    data class Header(
        var id: Int,
        var actionUrl: String,
        var labelList: Any?,
        var icon: String,
        var iconType: String,
        var time: Long,
        var showHateVideo: Boolean,
        var followType: String,
        var tagId: Int,
        var tagName: Any?,
        var issuerName: String
    )
}