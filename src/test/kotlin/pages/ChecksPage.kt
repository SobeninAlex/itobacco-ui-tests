package pages

import com.codeborne.selenide.Selenide
import pages.main_page.MainPage

class ChecksPage(private val subURL: String) : MainPage() {

    val checksPageTitle = "Проверки произведенной продукции"


//    $$("input:not([checked])")
//    $$("input")



    fun openPage() {
        Selenide.open(subURL)
        Selenide.executeJavaScript<Any>("localStorage.setItem('password', 'admin');")
        Selenide.executeJavaScript<Any>("localStorage.setItem('username', 'admin');")
        Selenide.refresh()
    }
}