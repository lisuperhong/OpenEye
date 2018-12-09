package com.lisuperhong.openeye.http

import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo
import com.lisuperhong.openeye.mvp.model.bean.LightTopicBean
import com.lisuperhong.openeye.mvp.model.bean.TabInfoBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/5 00:12
 * Github: https://github.com/lisuperhong
 * Desc:
 */
interface ApiService {

    // 首页-推荐
    @GET("api/v5/index/tab/allRec")
    fun allRec(@Query("page") page: Int): Observable<BaseBean>

    // 首页-发现
    @GET("api/v5/index/tab/discovery")
    fun discovery(): Observable<BaseBean>

    // 首页-日报
    @GET("api/v5/index/tab/feed")
    fun feed(@Query("date") date: Long, @Query("num") num: Int = 2): Observable<BaseBean>

    // 加载更多
    // nextPageUrl: "http://baobab.kaiyanapp.com/api/v5/index/tab/feed?date=1533862800000&num=2"
    @GET
    fun loadMoreData(@Url url: String): Observable<BaseBean>

    // 视频详情相关
    @GET("api/v4/video/related")
    fun videoRelated(@Query("id") id: Long): Observable<BaseBean>

    // 排行榜tab
    @GET("api/v4/rankList")
    fun getRankList(): Observable<TabInfoBean>

    // 获取全部分类
    @GET("api/v4/categories/all")
    fun getCategories(): Observable<BaseBean>

    // 获取分类tab信息
    @GET("api/v4/categories/detail/tab")
    fun getCategoryTabInfo(@Query("id") id: Long): Observable<CategoryTabInfo>

    // 根据分类id获取全部视频列表
    @GET("api/v4/categories/videoList")
    fun getCategoryVideoList(@Query("id") id: Long): Observable<BaseBean>

    // 获取作品
    @GET("api/v6/community/tab/follow")
    fun getCommunityFollow(): Observable<BaseBean>

    // 获取标签tab信息
    @GET("api/v6/tag/index")
    fun getTagTabInfo(@Query("id") id: Long): Observable<CategoryTabInfo>

    // 根据标签id获取全部视频列表
    @GET("api/v1/tag/videos")
    fun getTagVideos(@Query("id") id: Long): Observable<BaseBean>

    // 获取专题列表
    @GET("api/v3/specialTopics")
    fun getSpecialTopics(): Observable<BaseBean>

    // 获取专题详情   http://baobab.kaiyanapp.com/api/v3/lightTopics/internal/356
    @GET
    fun getSpecialTopicDetail(@Url url: String): Observable<LightTopicBean>

    // 获取tag热门内容的tab信息
    @GET
    fun getPopularTabInfo(@Url url: String): Observable<TabInfoBean>

    // 获取全部作者
    @GET("api/v3/tabs/follow")
    fun getAllAuthors(): Observable<BaseBean>
}