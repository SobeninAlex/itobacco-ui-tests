package pages

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import models.User
import pages.main_page.MainPage

class AuthorizationPage(subUrl: String) : MainPage(subUrl) {

    val loginOrPassword = User.ADMIN.usernameOrPassword

    fun password_field() = element("[name='password']").`as`("Поле 'Пароль'")
    fun login_field() = element("[name='username']").`as`("Поле 'Логин'")
    fun enter_button() = element("button").`as`("[Войти]")
    fun errorMessage() = element("p.Mui-error").`as`("Сообщение об ошибке")

    override fun openPage() {
        open(subURL)
    }

}