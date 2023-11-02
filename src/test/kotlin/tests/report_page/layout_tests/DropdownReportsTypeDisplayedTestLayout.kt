package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
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
@DisplayName("Отображение выпадающего списка 'Тип отчета'")
class DropdownReportsTypeDisplayedTestLayout : TestBase() {

    @Test
    @DisplayName("Отображение выпадающего списка 'Тип отчета' в сайдбаре формирования отчета")
    fun dropdownReportsType(testInfo: TestInfo) {
        MockReports.mock()
        MockReportsType.mock()

        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                Selenide.sleep(1000)
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageTitle))
            })

            step("Клик по выпадающему списку 'Тип отчета'", ThrowableRunnableVoid {
                typeReport_dropdown()
            })

            step("Съемка скрина. Сравнение ожидаемого и фактического", ThrowableRunnableVoid {
                assertScreen(testInfo)
            })
        }
    }

}