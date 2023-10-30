package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
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
@DisplayName("Отображение календаря ипнута 'Дата от'/'Дата до' в сдйдбаре формирования отчета")
class CalendarDisplayedTestLayout : TestBase() {

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
    @DisplayName("Отображение календаря ипнута 'Дата от' в сдйдбаре формирования отчета")
    fun calendarToDateDisplayed(testInfo: TestInfo) {
        mock(RequestMatcher.HttpMethod.GET, methodUrl, mockResponse)

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                Selenide.sleep(1000)
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
                sidebar().findAll(dateInputsSelector)[0].`as`("Дата 'ОТ'").editField("01052023")
                sidebar().findAll(dateInputsSelector)[1].`as`("Дата 'ДО'").editField("30052023")
            })

            step("Клик по иконке календаря инпута 'Дата от'", ThrowableRunnableVoid {
                sidebar().findAll(calendarIcon).first().click()
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                Selenide.sleep(500)
                assertScreen(testInfo)
            })
        }
    }

    @Test
    @DisplayName("Отображение календаря ипнута 'Дата до' в сдйдбаре формирования отчета")
    fun calendarFromDateDisplayed(testInfo: TestInfo) {
        mock(RequestMatcher.HttpMethod.GET, methodUrl, mockResponse)

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                Selenide.sleep(1000)
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
                sidebar().findAll(dateInputsSelector)[0].`as`("Дата 'ОТ'").editField("01052023")
                sidebar().findAll(dateInputsSelector)[1].`as`("Дата 'ДО'").editField("30052023")
            })

            step("Клик по иконке календаря инпута 'Дата до'", ThrowableRunnableVoid {
                sidebar().findAll(calendarIcon).last().click()
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                Selenide.sleep(500)
                assertScreen(testInfo)
            })
        }
    }

}
