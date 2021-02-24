package com.vcosqui.cucumber.pages.login

import com.vcosqui.cucumber.configs.Credentials
import com.vcosqui.cucumber.pages.AbstractPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory.initElements

class LoginPage(driver: WebDriver) : AbstractPage(driver) {

    @FindBy(id = "username")
    lateinit var email: WebElement

    @FindBy(id = "password")
    lateinit var password: WebElement

    @FindBy(css = "button[type=submit]")
    lateinit var submit: WebElement

    init {
        initElements(driver, this)
    }

    override fun waitForPresent() {
        email.waitForPresent()
        password.waitForPresent()
        submit.waitForPresent()
    }

    fun login(creds: Credentials) {
        waitForPresent()
        email.sendKeys(creds.username)
        password.sendKeys(creds.pass)
        submit.click()
    }

}