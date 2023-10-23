package test_settings

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll

abstract class TestBase {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            Configuration.baseUrl = "http://itobacco-dev-03.k8s.renue.yc/"
            Configuration.browser = "chrome"
            Configuration.browserSize = "1920x1080"
            Configuration.browserPosition = "0x0"
            Configuration.headless = false //фоновый режим
            Configuration.pageLoadTimeout = 50000
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