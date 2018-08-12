package pl.karollisiewicz.common.extension

import io.reactivex.Observable
import pl.karollisiewicz.common.reactive.Schedulers

fun <T> Observable<T>.applySchedulers(schedulers: Schedulers): Observable<T> =
    observeOn(schedulers.observer).subscribeOn(schedulers.subscriber)