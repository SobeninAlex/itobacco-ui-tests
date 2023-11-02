package pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import pages.main_page.MainPage

class ReportPage(subURL: String) : MainPage(subURL) {

    val reportPageTitle = "Отчеты"
    val reportPageSidebarTitle = "Отчет"

    fun typeReport_sidebarDropdown(): ElementsCollection {
        element("form div.MuiSelect-select").`as`("Выпадающее меню 'Тип отчета'").click()
        return items_dropdown()
    }

    fun typeReport_dropdown(): ElementsCollection {
        elements("div.MuiSelect-select").first().`as`("Выпадающее меню 'Тип отчета'").click()
        return items_dropdown()
    }

}