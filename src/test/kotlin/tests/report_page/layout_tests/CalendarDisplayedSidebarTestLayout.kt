package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
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
@DisplayName("Отображение календаря ипнута 'Дата от'/'Дата до' в сдйдбаре формирования отчета")
class CalendarDisplayedSidebarTestLayout : TestBase() {

    @Test
    @DisplayName("Отображение календаря ипнута 'Дата от' в сдйдбаре формирования отчета")
    fun calendarToDateDisplayedSidebarReports(testInfo: TestInfo) {
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

            step("Устанавливаем дату 'ОТ' и дату 'ДО'", ThrowableRunnableVoid {
                sidebar().findAll(dateInputsSelector)[0].`as`("Дата 'ОТ'").editField("01052023")
                sidebar().findAll(dateInputsSelector)[1].`as`("Дата 'ДО'").editField("30052023")
            })

            step("Клик по иконке календаря инпута 'Дата от'", ThrowableRunnableVoid {
                sidebar().findAll(calendarIconSelector).first().`as`("Иконка календаря в инпуте 'Дата от'").click()
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                sleep(500)
                assertScreen(testInfo)
            })
        }
    }

    @Test
    @DisplayName("Отображение календаря ипнута 'Дата до' в сдйдбаре формирования отчета")
    fun calendarFromDateDisplayedSidebarReports(testInfo: TestInfo) {
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

            step("Устанавливаем дату 'ОТ' и дату 'ДО'", ThrowableRunnableVoid {
                sidebar().findAll(dateInputsSelector)[0].`as`("Дата 'ОТ'").editField("01052023")
                sidebar().findAll(dateInputsSelector)[1].`as`("Дата 'ДО'").editField("30052023")
            })

            step("Клик по иконке календаря инпута 'Дата до'", ThrowableRunnableVoid {
                sidebar().findAll(calendarIconSelector).last().`as`("Иконка календаря в инпуте 'Дата до'").click()
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                sleep(500)
                assertScreen(testInfo)
            })
        }
    }

}
