package test_settings

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import support.Namespaces.DEV_02

abstract class TestBase {

    @BeforeEach
    fun setup() {
        Configuration.baseUrl = DEV_02.url
        Configuration.browser = "chrome"
        Configuration.browserSize = "1920x1080"
        Configuration.browserPosition = "0x0"
        Configuration.headless = false //фоновый режим
        Configuration.pageLoadTimeout = 50000
        Configuration.proxyEnabled = true

        SelenideLogger.addListener(
            "AllureSelenide",
            AllureSelenide().screenshots(true).savePageSource(false)
        )
    }

    @AfterEach
    fun teardown() {
        WebDriverRunner.getSelenideProxy().responseMocker().resetAll()
        webdriver().driver().close()
    }

}