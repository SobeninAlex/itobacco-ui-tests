package tests.authorization_page

import com.codeborne.selenide.Condition
import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.main_page.AUTHORIZATION_PAGE
import test_settings.TestBase

@Epic("Тесты на авторизацию в системе iTobacco")
@Story("Функциональные тесты")
@DisplayName("Ошибка авторизации")
class AuthorizationErrorTest : TestBase() {

    @Test
    @DisplayName("Ошибка авторизации")
    fun loginError() {
        with(AUTHORIZATION_PAGE) {
            step("Переход на страницу авторизации", ThrowableRunnableVoid {
                openPage()
            })

            step("Заполнение формы авторизации не валидными значениями", ThrowableRunnableVoid {
                login_field().value = "$loginOrPassword error"
                password_field().value = "$loginOrPassword error"
            })

            step("Клик [Войти]", ThrowableRunnableVoid {
                enter_button().click()
            })

            step("Проверка: отобразилось сообщение об ошибке", ThrowableRunnableVoid {
                errorMessage().shouldBe(Condition.visible)
            })

            step("Проверка: поля 'Логин' и 'Пароль' выделены красной рамкой", ThrowableRunnableVoid {
                login_field().shouldHave(Condition.attribute("aria-invalid", "true"))
                password_field().shouldHave(Condition.attribute("aria-invalid", "true"))
            })
        }
    }

}