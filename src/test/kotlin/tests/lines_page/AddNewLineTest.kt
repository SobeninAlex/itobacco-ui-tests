package tests.lines_page

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.sleep
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import mocks.po_manager.MockLineSettings
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.main_page.LINES_PAGE
import test_settings.TestBase

@Epic("Тесты на страницу 'Линии'")
@Story("Тесты на функциональность")
@DisplayName("Добавление новой линии")
class AddNewLineTest : TestBase(){

    @Test
    @DisplayName("Добавление новой линии")
    fun addNewLine() {
        MockLineSettings.mock()

        LINES_PAGE.apply {
            step("Переход на страницу 'Продукция'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(linesPageTitle))
                sleep(1)
            })

            step("Клик по [Создать]", ThrowableRunnableVoid {
                create_button().click()
                sidebarTitle().shouldBe(Condition.visible).shouldHave(Condition.text(linesPageSidebarTitle))
                submit_button().shouldBe(Condition.disabled)
            })
        }
        sleep(1)
    }

}