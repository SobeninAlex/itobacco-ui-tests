package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.Selenide.sleep
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import pages.main_page.REPORT_PAGE
import support.assertScreen
import mocks.operational.MockReports
import mocks.operational.MockReportsType
import test_settings.TestBase

@Epic("Тесты на страницу 'Отчеты'")
@Story("Тесты на верстку")
@DisplayName("Отображение календаря ипнута 'Дата от'/'Дата до' при фильтрации по дате")
class CalendarDisplayedTestLayout : TestBase() {

    @Test
    @DisplayName("Отображение календаря ипнута 'Дата от' при фильтрации по дате")
    fun calendarToDateDisplayedReports(testInfo: TestInfo) {
        MockReports.mock()
        MockReportsType.mock()

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                sleep(1000)
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageTitle))
            })

            step("Устанавливаем дату 'ОТ' и дату 'ДО'", ThrowableRunnableVoid {
                elements(dateInputsSelector).first().`as`("Дата ОТ").setValue("01052023")
                elements(dateInputsSelector).last().`as`("Дата ДО").setValue("30052023")
            })

            step("Клик по иконке календаря инпута 'Дата от'", ThrowableRunnableVoid {
                elements(calendarIconSelector).first().`as`("Иконка календаря в инпуте 'Дата от'").click()
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                assertScreen(testInfo)
            })
        }
    }

    @Test
    @DisplayName("Отображение календаря ипнута 'Дата до' при фильтрации по дате")
    fun calendarFromDateDisplayedReports(testInfo: TestInfo) {
        MockReports.mock()
        MockReportsType.mock()

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                sleep(1000)
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageTitle))
            })

            step("Устанавливаем дату 'ОТ' и дату 'ДО'", ThrowableRunnableVoid {
                elements(dateInputsSelector).first().`as`("Дата ОТ").setValue("01052023")
                elements(dateInputsSelector).last().`as`("Дата ДО").setValue("30052023")
            })

            step("Клик по иконке календаря инпута 'Дата от'", ThrowableRunnableVoid {
                elements(calendarIconSelector).last().`as`("Иконка календаря в инпуте 'Дата до'").click()
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                sleep(500)
                assertScreen(testInfo)
            })
        }
    }

}