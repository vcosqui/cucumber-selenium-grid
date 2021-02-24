package com.vcosqui.cucumber.steps

import com.typesafe.config.Config
import com.vcosqui.cucumber.model.actors.Role.valueOf
import com.vcosqui.cucumber.pages.login.LandingPage
import com.vcosqui.cucumber.pages.login.LoginPage
import com.vcosqui.cucumber.support.extensions.WithConfig
import com.vcosqui.cucumber.support.extensions.WithScreenshots
import com.vcosqui.cucumber.support.extensions.WithWebDriver
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.openqa.selenium.WebDriver

class LoginSteps : WithConfig, WithWebDriver, WithScreenshots {

    override val config: Config = config()
    override val driver: WebDriver = driver()

    private val loginPage = LoginPage(driver)
    private val landingPage = LandingPage(driver)

    @Given("^(\\w+) accesses application$")
    fun userNavigateLoginPage(username: String) {
        landingPage.open()
    }

    @When("^(\\w+) provides its credentials$")
    fun logsIn(username: String) {
        loginPage.login(valueOf(username).creds)
        pageScreenshot("login")
    }

    @Then("^(\\w+) is granted to access application$")
    fun landingPageIsOpened(username: String) {
        landingPage.waitForPresent()
        pageScreenshot("login_success")
    }

}
