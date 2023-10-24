package pages

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open

class AuthorizationPage(private val subUrl: String) {

    val loginOrPassword = "admin"

    fun passwordField() = element("[name='password']").`as`("Поле 'Пароль'")
    fun loginField() = element("[name='username']").`as`("Поле 'Логин'")
    fun enterButton() = element("button").`as`("[Войти]")
    fun errorMessage() = element("p.Mui-error").`as`("Сообщение об ошибке")

    fun openPage() {
        open(subUrl)
    }
}