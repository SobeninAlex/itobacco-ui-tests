package pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import pages.main_page.MainPage

class AuthorizationPage(subUrl: String) : MainPage(subUrl) {

    val loginOrPassword = "admin"

    fun passwordField() = element("[name='password']").`as`("Поле 'Пароль'")
    fun loginField() = element("[name='username']").`as`("Поле 'Логин'")
    fun enterButton() = element("button").`as`("[Войти]")
    fun errorMessage() = element("p.Mui-error").`as`("Сообщение об ошибке")

    override fun openPage() {
        open(subURL)
    }

}