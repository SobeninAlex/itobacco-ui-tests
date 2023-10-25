package tests.checks_page

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import pages.main_page.CHECKS_PAGE
import test_settings.TestBase

@Epic("Тесты на страницу 'Проверки производственно продукции'")
@DisplayName("Названия/описания проверок")
class NameCheck : TestBase() {

    @Test
    @DisplayName("Названия/описания проверок")
    fun nameCheck() {
        CHECKS_PAGE.apply {
            step("Переход на страницу 'Проверки производственной продукции'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(checksPageTitle))

                //TODO -> костыль для того что бы Selenide дождался полной загрузки страницы
                element(By.xpath("//td[text()='Проверка времени между нанесением и эмиссией КМ']"))
                    .shouldBe(Condition.visible)
            })

            step("Проверка: названия проверок совпадают с эталонными", ThrowableRunnableVoid {
                for ((index, element) in listItems().withIndex()) {
                    element.find(nameCheckedLocator).shouldHave(Condition.text(nameChecksList[index]))
                }
            })

            step("Проверка: описания проверок совпадают с эталонными", ThrowableRunnableVoid {
                for ((index, element) in listItems().withIndex()) {
                    element.find(descriptionChecksLocator).shouldHave(Condition.text(descriptionChecksList[index]))
                }
            })
        }

    }

}