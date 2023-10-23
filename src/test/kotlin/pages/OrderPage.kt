package pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import org.openqa.selenium.By.ByXPath

class OrderPage {

    val expectedOrderPageTitle = "Реестр заказов"
    val expectedOrderPageURL = "http://itobacco-dev-03.k8s.renue.yc/orders/main"

    fun titlePage() = element(ByXPath("//h1[.='Реестр заказов']")).`as`("Заголовок страницы")

    fun openPage() {
        open("orders/main")
        Selenide.executeJavaScript<Any>("localStorage.setItem('password', 'admin');")
        Selenide.executeJavaScript<Any>("localStorage.setItem('username', 'admin');")
        Selenide.refresh()
    }



}