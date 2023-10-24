package tests.checks_page

import com.codeborne.selenide.Condition
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.main_page.CHECKS_PAGE
import test_settings.TestBase

@Epic("Тесты на страницу 'Проверки производственно продукции'")
@DisplayName("тест") //TODO
class Test : TestBase() {

    @Test
    @DisplayName("тест") //TODO
    fun test() {
        with(CHECKS_PAGE) {
            step("Переход на страницу 'Проверки производственной продукции'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(checksPageTitle))
            })
        }
    }

}