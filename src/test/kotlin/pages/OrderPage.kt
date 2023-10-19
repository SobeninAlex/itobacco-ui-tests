package pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.open
import org.openqa.selenium.By.ByXPath

class OrderPage {

    fun titlePage() = element(ByXPath("//h1[.='Реестр заказов']")).`as`("Заголовок страницы")


    fun openPage() {
        open("orders/main")
    }



}