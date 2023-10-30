package pages.main_page

import com.codeborne.selenide.AuthenticationType
import com.codeborne.selenide.BasicAuthCredentials
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.refresh
import org.openqa.selenium.By
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.xpath

open class MainPage(val subURL: String) {

    val dateInputsSelector: By = cssSelector("input[type='tel']")
    val calendarIcon: By = cssSelector("button[aria-label^='Choose date']")
    fun pageTitle() = element("h1").`as`("Заголовок страницы")
    protected fun items_dropDownList() = Selenide.elements("li[class]").`as`("Список элементов выпадающего списка")
    fun listItems_List() = Selenide.elements("tbody tr").`as`("Список элементов")
    fun search_Field() = element(xpath("//label/following-sibling::div/input[@type='text']")).`as`("Поле 'Поиск'")
    fun create_Button() = element("a.MuiButton-contained").`as`("Кнопка создания сущности [Создать]/[Добавить]/[Сформировать]")
    fun submit_Button() = element("button[type='submit']").`as`("Кнопка подтверждения [Сохранить]/[Скачать отчет]")
    fun cancel_Button() = element(xpath("//button[text()='Отменить']")).`as`("[Отменить]")
    fun sidebar() = element("form").`as`("Сайдбар")
    fun sidebarTitle() = element("div.MuiPaper-root h2").`as`("Заголовок сайдбара")

    //TODO -> Переделать метод. Добавить авторизацию
    open fun openPage() {
        Selenide.open(subURL)
        Selenide.executeJavaScript<Any>("localStorage.setItem('password', 'admin');")
        Selenide.executeJavaScript<Any>("localStorage.setItem('username', 'admin');")
        refresh()
    }

}