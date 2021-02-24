package com.vcosqui.cucumber.support.extensions

interface WithRetry : WithConfig {

    fun <V> retry(timeoutSeconds: Long = config.getLong("defaultWaitTimeoutSeconds"), block: () -> V): V {
        return WaitHelper.retry(timeoutSeconds) { block() }
    }

    fun <V> waitForResult(timeoutSeconds: Long = config.getLong("defaultWaitTimeoutSeconds"), expected: V, block: () -> V): V {
        return WaitHelper.retry(timeoutSeconds) {
            val result = block()
            assert(expected == result) { "nay, $expected != $result" }
            result
        }
    }
}