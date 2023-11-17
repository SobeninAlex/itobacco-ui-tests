package pages.main_page

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.*
import org.openqa.selenium.By
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.xpath
import services.SettingsService

open class MainPage(val subURL: String) {

    val dateInputsSelector: By = cssSelector("input[type='tel']")
    val calendarIconSelector: By = cssSelector("button[aria-label^='Choose date']")
    fun pageTitle() = element("h1").`as`("Заголовок страницы")
    protected fun items_dropdown() = elements("li[class]").`as`("Элементы выпадающего списка")
    fun listItems_list() = elements("tbody tr").`as`("Список элементов")
    fun search_field() = element(xpath("//label/following-sibling::div/input[@type='text']")).`as`("Поле 'Поиск'")
    fun create_button() = element("a.MuiButton-contained").`as`("Кнопка создания сущности [Создать]/[Добавить]/[Сформировать]")
    fun submit_button() = element("button[type='submit']").`as`("Кнопка подтверждения [Сохранить]/[Скачать отчет]")
    fun cancel_button() = element(xpath("//button[text()='Отменить']")).`as`("[Отменить]")
    fun sidebar() = element("form").`as`("Сайдбар")
    fun sidebarTitle() = element("div.MuiPaper-root h2").`as`("Заголовок сайдбара")

    open fun openPage() {
        val token = SettingsService().token
        open(subURL)
        executeJavaScript<Any>("localStorage.setItem('token', '$token');")
        refresh()
        open(subURL)
    }

}