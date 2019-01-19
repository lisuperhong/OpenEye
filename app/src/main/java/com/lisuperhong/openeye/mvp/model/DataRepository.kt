package com.lisuperhong.openeye.mvp.model

import com.lisuperhong.openeye.http.ApiService
import com.lisuperhong.openeye.http.RetrofitManager
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.mvp.model.bean.CategoryTabInfo
import com.lisuperhong.openeye.mvp.model.bean.LightTopicBean
import com.lisuperhong.openeye.mvp.model.bean.TabInfoBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver
import com.lisuperhong.openeye.rx.scheduler.IoMainScheduler
import com.lisuperhong.openeye.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: lisuperhong
 * Time: Create on 2018/8/11 17:45
 * Github: https://github.com/lisuperhong
 * Desc:
 */
class DataRepository private constructor() {

    companion object {
        private var instance: DataRepository? = null

        fun getInstance(): DataRepository {
            if (instance == null) {
                synchronized(DataRepository::class.java) {
                    if (instance == null) {
                        instance = DataRepository()
                    }
                }
            }

            return instance!!
        }
    }

    fun allRec(page: Int, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .allRec(page)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun discovery(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .discovery()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun feed(date: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .feed(date)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun loadMoreData(url: String, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .loadMoreData(url)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun videoRelated(id: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .videoRelated(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getRankList(baseObserver: BaseObserver<TabInfoBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getRankList()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCommunityFollow(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getCommunityFollow()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCategories(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getCategories()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCategoryInfo(id: Long, baseObserver: BaseObserver<CategoryTabInfo>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getCategoryTabInfo(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCategoryVideoList(id: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getCategoryVideoList(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getTagInfo(id: Long, baseObserver: BaseObserver<CategoryTabInfo>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getTagTabInfo(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getTagVideos(id: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getTagVideos(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getSpecialTopics(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getSpecialTopics()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getSpecialTopicDetail(url: String, baseObserver: BaseObserver<LightTopicBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getSpecialTopicDetail(url)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getPopularTabInfo(url: String, baseObserver: BaseObserver<TabInfoBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java)
            .getPopularTabInfo(url)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getAllAuthors(baseObserver: BaseObserver<BaseBean>) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        val builder: Retrofit.Builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constant.HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        val apiService = builder.build().create(ApiService::class.java)
        apiService.getAllAuthors()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun loadMoreAuthors(url: String, baseObserver: BaseObserver<BaseBean>) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        val builder: Retrofit.Builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constant.HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

        val apiService = builder.build().create(ApiService::class.java)
        apiService.loadMoreAuthors(url)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }
}
