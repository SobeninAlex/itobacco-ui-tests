package pages

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open

class AuthorizationPage {

    fun passwordField() = element("[name='password']").`as`("Поле 'Пароль'")
    fun loginField() = element("[name='username']").`as`("Поле 'Логин'")
    fun enterButton() = element("button").`as`("Кнопка 'Войти'")
    fun errorMessage() = element("p.Mui-error").`as`("Сообщение об ошибке")

    fun openPage() {
        open("/")
    }
}