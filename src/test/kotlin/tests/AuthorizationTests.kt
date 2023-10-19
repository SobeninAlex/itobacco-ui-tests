package tests

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.webdriver
import com.codeborne.selenide.WebDriverConditions.url
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.Pages.Companion.AUTHORIZATION_PAGE
import pages.Pages.Companion.ORDER_PAGE
import test_settings.TestBase

@DisplayName("Тесты на авторизацию в системе iTobacco")
class AuthorizationTests : TestBase() {

    companion object {
        const val LOGIN_OR_PASSWORD = "admin"
        const val EXPECTED_ORDER_PAGE_TITLE = "Реестр заказов"
        const val EXPECTED_ORDER_PAGE_URL = "http://itobacco-dev-03.k8s.renue.yc/orders/main"
    }

    @Test
    @DisplayName("Успешная авторизация в системе iTobacco")
    fun login_success() {
        with(AUTHORIZATION_PAGE) {
            step("Открываем страницу авторизации", ThrowableRunnableVoid {
                openPage()
            })

            step("Заполняем форму авторизации", ThrowableRunnableVoid {
                loginField().value = LOGIN_OR_PASSWORD
                passwordField().value = LOGIN_OR_PASSWORD
            })

            step("Кликаем 'Войти'", ThrowableRunnableVoid {
                enterButton().click()
            })
        }
        with(ORDER_PAGE) {
            step("Проверяем что открылась страница 'Реестр заказов'", ThrowableRunnableVoid {
                titlePage().shouldBe(visible)
                titlePage().shouldHave(text(EXPECTED_ORDER_PAGE_TITLE)) //TODO
                webdriver().shouldHave(url(EXPECTED_ORDER_PAGE_URL))
            })
        }
    }

    @Test
    @DisplayName("Ошибка авторизации")
    fun login_error() {
        with(AUTHORIZATION_PAGE) {
            step("Открываем страницу авторизации", ThrowableRunnableVoid {
                openPage()
            })

            step("Заполняем форму авторизации не валидными значениями", ThrowableRunnableVoid {
                loginField().value = "$LOGIN_OR_PASSWORD error"
                passwordField().value = "$LOGIN_OR_PASSWORD error"
            })

            step("Кликаем 'Войти'", ThrowableRunnableVoid {
                enterButton().click()
            })

            step("Проверяем что отобразилось сообщение об ошибке", ThrowableRunnableVoid {
                errorMessage().shouldBe(visible)
            })

            step("Проверяем что поля 'Логин' и 'Пароль' выделены красной рамкой", ThrowableRunnableVoid {
                loginField().shouldHave(attribute("aria-invalid", "true"))
                passwordField().shouldHave(attribute("aria-invalid", "true"))
            })
        }
    }

}