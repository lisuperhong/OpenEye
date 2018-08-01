package com.lisuperhong.openeye.rx.scheduler

import io.reactivex.*
import org.reactivestreams.Publisher

/**
 * Author: lizhaohong
 * Time: Create on 2018/8/1 14:24
 * Desc:
 */
open class BaseScheduler<T> constructor(
    private val subscribeOnScheduler: Scheduler,
    private val observerOnScheduler: Scheduler
) : ObservableTransformer<T, T>,
    SingleTransformer<T, T>,
    MaybeTransformer<T, T>,
    CompletableTransformer,
    FlowableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
            .unsubscribeOn(subscribeOnScheduler)
            .observeOn(observerOnScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
            .unsubscribeOn(subscribeOnScheduler)
            .observeOn(observerOnScheduler)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
            .unsubscribeOn(subscribeOnScheduler)
            .observeOn(observerOnScheduler)
    }

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(subscribeOnScheduler)
            .unsubscribeOn(subscribeOnScheduler)
            .observeOn(observerOnScheduler)
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
            .unsubscribeOn(subscribeOnScheduler)
            .observeOn(observerOnScheduler)
    }
}