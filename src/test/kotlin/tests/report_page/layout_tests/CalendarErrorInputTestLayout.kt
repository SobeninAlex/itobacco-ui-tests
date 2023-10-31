package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
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
import support.dateNow
import support.editField
import support.formatter
import mocks.operational.MockReports
import mocks.operational.MockReportsType
import test_settings.TestBase

@Epic("Тесты на страницу 'Отчеты'")
@Story("Тесты на верстку")
@DisplayName("Отображение ошибки инпутов календаря при фильтрации по дате")
class CalendarErrorInputTestLayout : TestBase() {

    @Test
    @DisplayName("Отображение ошибки инпутов календаря при фильтрации по дате")
    fun calendarErrorInputReports(testInfo: TestInfo) {
        MockReports.mock()
        MockReportsType.mock()

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                sleep(1000)
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageTitle))
            })

            step("Устанавливаем дату 'ОТ' больше даты 'ДО'", ThrowableRunnableVoid {
                elements(dateInputsSelector).first().`as`("Период от").editField(dateNow.format(formatter))
                elements(dateInputsSelector).last().`as`("до").editField(dateNow.minusDays(1).format(formatter))

                //скрываем значения в инпутах
                Selenide.executeJavaScript<Any>("document.querySelectorAll(\"input[type='tel']\").forEach(el => el.style.visibility='hidden')")
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                sleep(500)
                assertScreen(testInfo)
            })
        }
    }

}