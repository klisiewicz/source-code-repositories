package pl.karollisiewicz.common.reactive

import io.reactivex.Scheduler

class Schedulers(
    val subscriber: Scheduler,
    val observer: Scheduler
)