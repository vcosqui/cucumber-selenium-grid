package com.vcosqui.cucumber.pages

import com.vcosqui.cucumber.support.extensions.WithExtensions
import com.vcosqui.cucumber.support.extensions.WithPageUrl
import com.vcosqui.cucumber.support.extensions.WithWebDriver
import org.openqa.selenium.WebDriver

abstract class AbstractPage(driver: WebDriver) : BasePage(driver), WithPageUrl, WithWebDriver, WithExtensions {

    override fun baseUrl(): String = config.getString("application.url")

    fun open() {
        driver.get(pageUrl())
    }

}