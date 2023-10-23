package tests.authorization_page

import com.codeborne.selenide.Condition
import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.AUTHORIZATION_PAGE
import test_settings.TestBase

@Epic("Тесты на авторизацию в системе iTobacco")
@DisplayName("Ошибка авторизации")
class AuthorizationError : TestBase() {

    @Test
    @DisplayName("Ошибка авторизации")
    fun loginError() {
        with(AUTHORIZATION_PAGE) {
            Allure.step("Открываем страницу авторизации", ThrowableRunnableVoid {
                openPage()
            })

            Allure.step("Заполняем форму авторизации не валидными значениями", ThrowableRunnableVoid {
                loginField().value = "$loginOrPassword error"
                passwordField().value = "$loginOrPassword error"
            })

            Allure.step("Кликаем 'Войти'", ThrowableRunnableVoid {
                enterButton().click()
            })

            Allure.step("Проверяем что отобразилось сообщение об ошибке", ThrowableRunnableVoid {
                errorMessage().shouldBe(Condition.visible)
            })

            Allure.step("Проверяем что поля 'Логин' и 'Пароль' выделены красной рамкой", ThrowableRunnableVoid {
                loginField().shouldHave(Condition.attribute("aria-invalid", "true"))
                passwordField().shouldHave(Condition.attribute("aria-invalid", "true"))
            })
        }
    }

}