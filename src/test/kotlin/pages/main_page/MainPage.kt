package pages.main_page

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element

open class MainPage {

    fun pageTitle() = element("h1").`as`("Заголовок страницы")
    protected fun items() = Selenide.elements("li[class]").`as`("Список элементов выпадающего списка")
    fun listItems() = Selenide.elements("tbody tr").`as`("Список элементов")

}