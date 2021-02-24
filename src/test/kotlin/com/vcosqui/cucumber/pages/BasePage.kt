package com.vcosqui.cucumber.pages

import com.typesafe.config.Config
import com.vcosqui.cucumber.support.extensions.WithConfig
import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class BasePage(val driver: WebDriver) : WithConfig {

    val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    override val config: Config = config()

    abstract fun waitForPresent()

}