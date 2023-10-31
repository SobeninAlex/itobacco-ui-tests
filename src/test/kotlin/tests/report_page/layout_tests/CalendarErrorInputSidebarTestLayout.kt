package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.executeJavaScript
import com.codeborne.selenide.Selenide.sleep
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.*
import pages.main_page.REPORT_PAGE
import support.*
import mocks.operational.MockReports
import mocks.operational.MockReportsType
import test_settings.TestBase

@Epic("Тесты на страницу 'Отчеты'")
@Story("Тесты на верстку")
@DisplayName("Отображение ошибки инпутов календаря в сдйдбаре формирования отчета")
class CalendarErrorInputSidebarTestLayout : TestBase() {

    @Test
    @DisplayName("Отображение ошибки инпутов календаря в сдйдбаре формирования отчета")
    fun calendarErrorInputInSidebarReports(testInfo: TestInfo) {
        MockReports.mock()
        MockReportsType.mock()

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                sleep(1000)
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageTitle))
            })

            step("Клик по [Сформировать]", ThrowableRunnableVoid {
                create_button().click()
                sidebarTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageSidebarTitle))
                sidebar().findAll(dateInputsSelector).forEach { it.shouldBe(Condition.visible) }
            })

            step("Выбираем тип отчета", ThrowableRunnableVoid {
                typeReport_sidebarDropdown().find(Condition.text(typeReport_OrderReport)).click()
            })

            step("Устанавливаем дату 'ОТ' больше даты 'ДО'", ThrowableRunnableVoid {
                sidebar().findAll(dateInputsSelector)[0].`as`("Дата 'ОТ'").editField(dateNow.format(formatter))
                sidebar().findAll(dateInputsSelector)[1].`as`("Дата 'ДО'").editField(dateNow.minusDays(1).format(formatter))
            })

            step("Клик по [Cкачать отчет]", ThrowableRunnableVoid {
                submit_button().click()
                executeJavaScript<Any>("document.querySelectorAll(\"form input[type]\").forEach(el => el.style.visibility='hidden')")
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                sleep(500)
                assertScreen(testInfo)
            })
        }
    }

}