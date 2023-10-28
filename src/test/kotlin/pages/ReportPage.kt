package pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import pages.main_page.MainPage

class ReportPage(subURL: String) : MainPage(subURL) {

    val reportPageTitle = "Отчеты"
    val reportPageSidebarTitle = "Отчет"

    fun typeReport_DropDownList(): ElementsCollection {
        element("form div.MuiSelect-select").`as`("Выпадающее меню 'Тип отчета'").click()
        return items_dropDownList()
    }

}