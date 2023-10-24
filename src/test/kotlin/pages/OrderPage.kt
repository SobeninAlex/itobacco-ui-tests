package pages

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import pages.main_page.MainPage

class OrderPage(private val subURL: String) : MainPage() {

    val orderPageTitle = "Реестр заказов"
    val orderPageURL: String get() = Configuration.baseUrl + subURL

    fun openPage() {
        open(subURL)
        Selenide.executeJavaScript<Any>("localStorage.setItem('password', 'admin');")
        Selenide.executeJavaScript<Any>("localStorage.setItem('username', 'admin');")
        Selenide.refresh()
    }


}