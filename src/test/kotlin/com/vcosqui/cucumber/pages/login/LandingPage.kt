package com.vcosqui.cucumber.pages.login

import com.vcosqui.cucumber.pages.AbstractPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory.initElements

class LandingPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(id = "message")
    lateinit var message: WebElement

    init {
        initElements(driver, this)
    }

    override fun waitForPresent() {
        message.waitForPresent()
    }
}