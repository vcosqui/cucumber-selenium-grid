package com.vcosqui.cucumber.support.drivers

import com.vcosqui.cucumber.configs.WebDriverConfig
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities.firefox
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

internal class WebDriverProviderFirefox(wdConfig: WebDriverConfig) : WebDriverProvider(wdConfig) {

    override val driverPath: String get() = wdConfig.geckodriverPath

    override val webDriver: WebDriver
        get() {
            log.info("FIREFOX")
            val webDriver: WebDriver
            if (wdConfig.useSeleniumGrid) {
                webDriver = RemoteWebDriver(URL(wdConfig.seleniumGridUrl), firefox())
            } else {
                val firefoxDriverPath = resolveDriverPath(wdConfig.browser)
                setExecPermissions(firefoxDriverPath)
                System.setProperty("webdriver.gecko.driver", firefoxDriverPath)
                val firefoxOptions = FirefoxOptions()
                firefoxOptions.addPreference("app.update.enabled", false)
                webDriver = FirefoxDriver(firefoxOptions)
            }
            setWebDriverTimeouts(webDriver)
            setWebDriverWindowSize(webDriver)
            return webDriver
        }
}