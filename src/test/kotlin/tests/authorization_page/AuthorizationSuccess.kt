package tests.authorization_page

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.url
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.LabelAnnotation
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestClassOrder
import pages.AUTHORIZATION_PAGE
import pages.ORDER_PAGE
import test_settings.TestBase

@Epic("Тесты на авторизацию в системе iTobacco")
@DisplayName("Успешная авторизации")
class AuthorizationSuccess : TestBase() {

    @Test
    @DisplayName("Успешная авторизация")
    fun loginSuccess() {
        with(AUTHORIZATION_PAGE) {
            step("Открываем страницу авторизации", ThrowableRunnableVoid {
                openPage()
            })

            step("Заполняем форму авторизации", ThrowableRunnableVoid {
                loginField().value = loginOrPassword
                passwordField().value = loginOrPassword
            })

            step("Кликаем 'Войти'", ThrowableRunnableVoid {
                enterButton().click()
            })
        }
        with(ORDER_PAGE) {
            step("Проверяем что открылась страница 'Реестр заказов'", ThrowableRunnableVoid {
                titlePage().shouldBe(visible)
                titlePage().shouldHave(text(expectedOrderPageTitle))
                webdriver().shouldHave(url(expectedOrderPageURL))
            })
        }
    }

}