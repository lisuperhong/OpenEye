package com.lisuperhong.openeye.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/1 15:03
 * Desc: 新线程调度器
 */
class NewThreadMainScheduler<T> :
    BaseScheduler<T>(Schedulers.newThread(), AndroidSchedulers.mainThread())