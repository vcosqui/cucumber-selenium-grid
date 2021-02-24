package com.vcosqui.cucumber.support.extensions

import com.github.rholder.retry.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


class WaitHelper<V> {

    val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    companion object {
        fun <V> retry(timeoutSeconds: Long, callable: Callable<V>): V {
            val waitHelper = WaitHelper<V>()
            return waitHelper.buildRetryer(timeoutSeconds).call(callable)
        }
    }

    fun buildRetryer(timeoutSeconds: Long): Retryer<V> {
        return RetryerBuilder.newBuilder<V>()
                .retryIfExceptionOfType(Throwable::class.java)
                .withWaitStrategy(WaitStrategies.fibonacciWait(500, timeoutSeconds, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterDelay(timeoutSeconds, TimeUnit.SECONDS))
                .withRetryListener(getRetryListener())
                .build()
    }

    private fun getRetryListener(): RetryListener {
        return object : RetryListener {
            override fun <V> onRetry(attempt: Attempt<V>) {
                val number = attempt.attemptNumber
                val res = when {
                    attempt.hasResult() -> attempt.result
                    attempt.hasException() -> attempt.exceptionCause
                    else -> throw IllegalStateException("attempt $attempt has neither result nor exception, how come?! ")
                }
                log.debug("attempt #$number : $res")
            }
        }
    }

}