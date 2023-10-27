package pages.main_page

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import org.openqa.selenium.By.xpath

open class MainPage(val subURL: String) {

    fun pageTitle() = element("h1").`as`("Заголовок страницы")
    protected fun items() = Selenide.elements("li[class]").`as`("Список элементов выпадающего списка")
    fun listItems() = Selenide.elements("tbody tr").`as`("Список элементов")
    fun searchField() = element(xpath("//label/following-sibling::div/input[@type='text']")).`as`("Поле 'Поиск'")
    fun createButton() = element("a.MuiButton-contained").`as`("Кнопка создания сущности [Создать]/[Добавить]/[Сформировать]")
    fun submitButton() = element("button[type='submit']").`as`("Кнопка подтверждения [Сохранить]/[Скачать отчет]")
    fun cancelButton() = element(xpath("//button[text()='Отменить']")).`as`("[Отменить]")
    fun sidebar() = element("form").`as`("Сайдбар")
    fun startDateInputInSidebar() = element(xpath("(//form//input[@type='tel'])[1]")).`as`("Инпут начальной даты")
    fun endDateInputInSidebar() = element(xpath("(//form//input[@type='tel'])[2]")).`as`("Инпут конечной даты")
    fun startDateInput() = element(xpath("(/input[@type='tel'])[1]")).`as`("Инпут начальной даты")
    fun endDateInput() = element(xpath("(//input[@type='tel'])[2]")).`as`("Инпут конечной даты")
    fun sidebarTitle() = element("div.MuiPaper-root h2").`as`("Заголовок сайдбара")
    open fun openPage() {
        Selenide.open(subURL)
        Selenide.executeJavaScript<Any>("localStorage.setItem('password', 'admin');")
        Selenide.executeJavaScript<Any>("localStorage.setItem('username', 'admin');")
        Selenide.refresh()
    }

}