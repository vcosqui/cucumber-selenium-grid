package com.vcosqui.cucumber.support.extensions

import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebElement
import kotlin.test.fail


interface WithExtensions : WithConfig, WithRetry, WithWebDriver {

    fun WebElement.isNotDisplayed(): Boolean {
        return try {
            !this.isDisplayed
        } catch (e: NoSuchElementException) {
            true
        }
    }

    fun WebElement.waitForPresent(timeoutSeconds: Long = config.getLong("defaultWaitTimeoutSeconds")): WebElement {
        return retry(timeoutSeconds) {
            this.isDisplayed || fail("still waiting for element to be displayed: $this")
            this
        }
    }

    fun WebElement.waitForAbsent(timeoutSeconds: Long = config.getLong("defaultWaitTimeoutSeconds")) {
        retry(timeoutSeconds) {
            this.isNotDisplayed() || fail("still waiting for element to disappear: $this")
        }
    }
}