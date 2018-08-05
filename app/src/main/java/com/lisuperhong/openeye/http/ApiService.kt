package com.lisuperhong.openeye.http

import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

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
}