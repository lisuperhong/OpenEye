package com.lisuperhong.openeye.mvp.model

import com.lisuperhong.openeye.http.ApiService
import com.lisuperhong.openeye.http.RetrofitManager
import com.lisuperhong.openeye.mvp.model.bean.BaseBean
import com.lisuperhong.openeye.rx.scheduler.BaseObserver
import com.lisuperhong.openeye.rx.scheduler.IoMainScheduler
import com.lisuperhong.openeye.utils.Constant
import io.reactivex.disposables.Disposable

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

    fun feedLoadMore(url: String, baseObserver: BaseObserver<BaseBean>) {
        RetrofitManager.getInstance()
            .initService(ApiService::class.java, Constant.HOST)
            .feedLoadMore(url)
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
}
