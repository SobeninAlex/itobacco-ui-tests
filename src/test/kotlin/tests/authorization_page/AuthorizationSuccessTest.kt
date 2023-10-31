package tests.authorization_page

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.url
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.main_page.AUTHORIZATION_PAGE
import pages.main_page.ORDER_PAGE
import test_settings.TestBase

@Epic("Тесты на авторизацию в системе iTobacco")
@Story("Функциональные тесты")
@DisplayName("Успешная авторизации")
class AuthorizationSuccessTest : TestBase() {

    @Test
    @DisplayName("Успешная авторизация")
    fun loginSuccess() {
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
        with(ORDER_PAGE) {
            step("Проверка: открылась страница 'Реестр заказов'", ThrowableRunnableVoid {
                pageTitle().shouldBe(visible)
                pageTitle().shouldHave(text(orderPageTitle))
                webdriver().shouldHave(url(orderPageURL))
            })
        }
    }

}