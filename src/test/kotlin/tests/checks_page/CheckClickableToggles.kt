package tests.checks_page

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import pages.main_page.CHECKS_PAGE
import test_settings.TestBase

@Epic("Тесты на страницу 'Проверки производственно продукции'")
@DisplayName("Кликабельность тогглов")
class CheckClickableToggles : TestBase() {

    @Test
    @DisplayName("Кликабельность тогглов")
    fun checkClickableToggles() {
        with(CHECKS_PAGE) {
            step("Переход на страницу 'Проверки производственной продукции'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(checksPageTitle))

                //TODO -> костыль для того что бы Selenide дождался полной загрузки страницы
                element(By.xpath("//td[text()='Проверка времени между нанесением и эмиссией КМ']"))
                    .shouldBe(Condition.visible)
            })

            step("Клик по каждому тогглу -> проверка: тоггл действительно выключился/включился", ThrowableRunnableVoid {
                checkToggles()
            })
        }

    }

}