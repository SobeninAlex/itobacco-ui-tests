package pages.main_page

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import org.openqa.selenium.By

open class MainPage(val subURL: String) {

    fun pageTitle() = element("h1").`as`("Заголовок страницы")
    protected fun items() = Selenide.elements("li[class]").`as`("Список элементов выпадающего списка")
    fun listItems() = Selenide.elements("tbody tr").`as`("Список элементов")
    fun searchField() = element(By.xpath("//label/following-sibling::div/input[@type='text']")).`as`("Поле 'Поиск'")
    fun createButton() = element(By.xpath("//a[.='Создать']")).`as`("[Создать]")
    fun saveButton() = element(By.xpath("//button[text()='Сохранить']")).`as`("[Сохранить]")
    fun cancelButton() = element(By.xpath("//button[text()='Отменить']")).`as`("[Отменить]")
    fun sidebar() = element("form").`as`("Сайдбар")
    fun sidebarTitle() = element("div.MuiPaper-root h2").`as`("Заголовок сайдбара")
    open fun openPage() {
        Selenide.open(subURL)
        Selenide.executeJavaScript<Any>("localStorage.setItem('password', 'admin');")
        Selenide.executeJavaScript<Any>("localStorage.setItem('username', 'admin');")
        Selenide.refresh()
    }

}