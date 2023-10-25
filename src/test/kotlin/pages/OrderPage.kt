package pages

import com.codeborne.selenide.Configuration
import pages.main_page.MainPage

class OrderPage(subURL: String) : MainPage(subURL) {

    val orderPageTitle = "Реестр заказов"
    val orderPageURL: String get() = Configuration.baseUrl + subURL

}