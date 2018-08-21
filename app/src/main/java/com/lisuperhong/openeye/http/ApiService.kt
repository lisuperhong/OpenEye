package com.lisuperhong.openeye.http

import com.lisuperhong.openeye.mvp.model.bean.BaseBean
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

    // 首页日报加载更多
    // nextPageUrl: "http://baobab.kaiyanapp.com/api/v5/index/tab/feed?date=1533862800000&num=2"
    @GET
    fun feedLoadMore(@Url url: String): Observable<BaseBean>

    // 视频详情相关
    @GET("api/v4/video/related")
    fun videoRelated(@Query("id") id: Long): Observable<BaseBean>
}