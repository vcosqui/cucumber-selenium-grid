package com.vcosqui.cucumber.support.extensions

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.io.File

interface WithScreenshots : WithWebDriver {

    fun pageScreenshot(name: String) {
        val tempFile = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
        val dir = "build/screenshots"
        File(dir).apply { mkdirs() }
        val fullpath = "$dir/${System.currentTimeMillis()}_$name.png"
        tempFile.renameTo(File((fullpath)))
    }
}