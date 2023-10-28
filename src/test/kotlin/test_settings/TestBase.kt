package test_settings

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
//import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import services.Namespace
import services.Namespace.DEV_03

abstract class TestBase {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            Configuration.baseUrl = DEV_03.url
            Configuration.browser = "firefox"
            Configuration.browserSize = "1920x1080"
            Configuration.browserPosition = "0x0"
            Configuration.headless = false //фоновый режим
            Configuration.pageLoadTimeout = 50000

//            Configuration.screenshots = true
//            Configuration.savePageSource = false

            SelenideLogger.addListener(
                "AllureSelenide",
                AllureSelenide().screenshots(true).savePageSource(false)
            )

        }
    }

    @AfterEach
    fun teardown() {
        webdriver().driver().close()
    }

}