package com.lisuperhong.openeye.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/1 15:09
 * Desc: 大数据量计算线程调度器
 */
class ComputationMainScheduler<T> :
    BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread())