package com.bekmnsrw.elmslie.data

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

object ValueRepository {

    private val random = Random

    private const val TIMER_DELAY = 2L
    private const val RANDOM_FROM = 0
    private const val RANDOM_UNTIL = 100
    private const val ERROR_MESSAGE = "Simulate unexpected error"

    fun loadValue() = Single
        .timer(TIMER_DELAY, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { random.nextInt(RANDOM_FROM, RANDOM_UNTIL) }
        .doOnSuccess { if (it % 3 == 1) error(ERROR_MESSAGE) }
}
