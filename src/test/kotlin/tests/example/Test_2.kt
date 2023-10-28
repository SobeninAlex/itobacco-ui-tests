package tests.example

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.FileDownloadMode
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.WebDriverRunner.getSelenideProxy
import com.codeborne.selenide.proxy.RequestMatcher.HttpMethod.GET
import com.codeborne.selenide.proxy.RequestMatchers.urlStartsWith
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class Test_2 {

    @BeforeEach
    fun before() {
        Configuration.browser = "firefox"
        Configuration.browserSize = "1920x1080"
        Configuration.browserPosition = "0x0"
        Configuration.headless = false //фоновый режим
        Configuration.pageLoadTimeout = 50000
//        Configuration.fileDownload = FileDownloadMode.PROXY
        Configuration.proxyEnabled = true
    }
    @AfterEach
    fun after() {
        webdriver().driver().close()
    }

    @Test
    fun demoTest() {

        val newBody = File("src/test/kotlin/tests/example/respons.json").readText()

        open()
        getSelenideProxy().responseMocker().mockText(
            "mock",
            urlStartsWith(GET, "https://demo.playwright.dev/api-mocking/api/v1/fruits")
        ) { newBody }

        open("https://demo.playwright.dev/api-mocking/")
        sleep(100)

    }

}