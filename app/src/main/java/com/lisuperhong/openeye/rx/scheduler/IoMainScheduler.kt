package com.lisuperhong.openeye.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/1 14:59
 * Desc: IO线程到UI主线程调度器
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())
