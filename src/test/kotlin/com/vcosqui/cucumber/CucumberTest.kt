package com.vcosqui.cucumber

import cucumber.api.CucumberOptions
import cucumber.api.testng.AbstractTestNGCucumberTests
import org.testng.annotations.Test

@Test
@CucumberOptions(plugin = ["pretty"])
class CucumberTest : AbstractTestNGCucumberTests()
