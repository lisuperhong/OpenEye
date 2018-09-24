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
            .initService(ApiService::class.java, Constant.HOST)
            .allRec(page)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun discovery(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .discovery()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun feed(date: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .feed(date)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun loadMoreData(url: String, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .loadMoreData(url)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun videoRelated(id: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .videoRelated(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getRankList(baseObserver: BaseObserver<TabInfoBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getRankList()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCommunityFollow(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getCommunityFollow()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCategories(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getCategories()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCategoryInfo(id: Long, baseObserver: BaseObserver<CategoryTabInfo>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getCategoryTabInfo(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getCategoryVideoList(id: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getCategoryVideoList(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getTagInfo(id: Long, baseObserver: BaseObserver<CategoryTabInfo>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getTagTabInfo(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getTagVideos(id: Long, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getTagVideos(id)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getSpecialTopics(baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getSpecialTopics()
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

    fun getSpecialTopicDetail(url: String, baseObserver: BaseObserver<LightTopicBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .getSpecialTopicDetail(url)
            .compose(IoMainScheduler())
            .subscribe(baseObserver)
    }

}
