package com.vcosqui.cucumber.support.drivers

import com.vcosqui.cucumber.configs.WebDriverConfig
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class WebDriverProviderChrome(wdConfig: WebDriverConfig) : WebDriverProvider(wdConfig) {

    override val driverPath: String get() = wdConfig.chromedriverPath

    override val webDriver: WebDriver
        get() {
            log.info("CHROME")
            val webDriver: WebDriver = if (wdConfig.useSeleniumGrid) {
                RemoteWebDriver(URL(wdConfig.seleniumGridUrl), ChromeOptions())
            } else {
                val chromeDriverPath = resolveDriverPath(wdConfig.browser)
                setExecPermissions(chromeDriverPath)
                System.setProperty("webdriver.chrome.driver", chromeDriverPath)
                ChromeDriver(ChromeOptions())
            }
            setWebDriverTimeouts(webDriver)
            setWebDriverWindowSize(webDriver)
            return webDriver
        }
}