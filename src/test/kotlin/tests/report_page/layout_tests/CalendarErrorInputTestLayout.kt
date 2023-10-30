package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.executeJavaScript
import com.codeborne.selenide.Selenide.sleep
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.proxy.RequestMatcher
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.*
import pages.main_page.REPORT_PAGE
import support.*
import test_settings.TestBase
import java.io.File

@Epic("Тесты на страницу 'Отчеты'")
@Story("Тесты на верстку")
@DisplayName("Отображение ошибки инпутов календаря в сдйдбаре формирования отчета")
class CalendarErrorInputTestLayout : TestBase() {

    @BeforeEach
    fun before() {
        Configuration.proxyEnabled = true
    }

    @AfterEach
    fun after() {
        WebDriverRunner.getSelenideProxy().responseMocker().resetAll()
        WebDriverRunner.getSelenideProxy().shutdown()
    }

    private val mockResponse = File("src/test/resources/mockResponse/operational_api_v1_reports.json").readText()
    private val methodUrl = "http://itobacco-dev-03.k8s.renue.yc/operational/api/v1/reports?page=0&size=10&sort=createdAt,desc"

    @Test
    @DisplayName("Отображение ошибки инпутов календаря в сдйдбаре формирования отчета")
    fun calendarErrorInputInSidebarReport(testInfo: TestInfo) {
        mock(RequestMatcher.HttpMethod.GET, methodUrl, mockResponse)

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                sleep(1000)
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageTitle))
            })

            step("Клик по [Сформировать]", ThrowableRunnableVoid {
                create_Button().click()
                sidebarTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageSidebarTitle))
                sidebar().findAll(dateInputsSelector).forEach { it.shouldBe(Condition.visible) }
            })

            step("Выбираем тип отчета", ThrowableRunnableVoid {
                typeReport_DropDownList().find(Condition.text(typeReport_OrderReport)).click()
            })

            step("Устанавливаем дату 'ОТ' больше даты 'ДО'", ThrowableRunnableVoid {
                sidebar().findAll(dateInputsSelector)[0].`as`("Дата 'ОТ'").editField(dateNow.format(formatter))
                sidebar().findAll(dateInputsSelector)[1].`as`("Дата 'ДО'").editField(dateNow.minusDays(1).format(formatter))
            })

            step("Клик по [Cкачать отчет]", ThrowableRunnableVoid {
                submit_Button().click()
                executeJavaScript<Any>("document.querySelectorAll(\"form input[type]\").forEach(el => el.style.visibility='hidden')")
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                sleep(500)
                assertScreen(testInfo)
            })
        }
    }

}