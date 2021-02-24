package com.vcosqui.cucumber.support.extensions

import com.vcosqui.cucumber.support.drivers.WebDriverProvider
import org.openqa.selenium.WebDriver


interface WithWebDriver : WithConfig {

    val driver: WebDriver

    fun driver(): WebDriver {
        return WebDriverProvider.getWebDriverInstance(config)
    }
}