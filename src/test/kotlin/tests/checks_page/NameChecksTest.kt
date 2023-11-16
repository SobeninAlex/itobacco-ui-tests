package tests.checks_page

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import pages.main_page.AUTHORIZATION_PAGE
import pages.main_page.CHECKS_PAGE
import test_settings.TestBase

@Epic("Тесты на страницу 'Проверки производственно продукции'")
@Story("Функциональные тесты")
@DisplayName("Названия/описания проверок")
class NameChecksTest : TestBase() {

    @Test
    @DisplayName("Названия/описания проверок")
    fun nameCheck() {
        with(AUTHORIZATION_PAGE) {
            step("Переход на страницу авторизации", ThrowableRunnableVoid {
                openPage()
            })

            step("Заполнение формы авторизации", ThrowableRunnableVoid {
                login_field().value = loginOrPassword
                password_field().value = loginOrPassword
            })

            step("Клик [Войти]", ThrowableRunnableVoid {
                enter_button().click()
            })
        }
        Selenide.sleep(1000)
        CHECKS_PAGE.apply {
            step("Переход на страницу 'Проверки производственной продукции'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(checksPageTitle))

                /**
                 * Костыль для того что бы Selenide дождался полной загрузки страницы
                 */
                element(By.xpath("//td[text()='Проверка времени между нанесением и эмиссией КМ']"))
                    .shouldBe(Condition.visible)
            })

            step("Проверка: названия проверок совпадают с эталонными", ThrowableRunnableVoid {
                for ((index, element) in listItems_list().withIndex()) {
                    element.find(nameChecked_locator).shouldHave(Condition.text(nameChecksList[index]))
                }
            })

            step("Проверка: описания проверок совпадают с эталонными", ThrowableRunnableVoid {
                for ((index, element) in listItems_list().withIndex()) {
                    element.find(descriptionChecks_locator).shouldHave(Condition.text(descriptionChecksList[index]))
                }
            })
        }

    }

}