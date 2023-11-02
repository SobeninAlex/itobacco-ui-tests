package pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.xpath
import pages.main_page.MainPage

class ProductPage(subURL: String) : MainPage(subURL) {

    val orderPageTitle = "Продукция"
    val orderPageSidebarTitle = "Создать продукт"

    fun deleteParameter_button() = element("form button > svg > path").`as`("[Удалить параметр]")
    fun addParameter_button() = element(xpath("//button[text()='Добавить параметр']")).`as`("[+ Добавить параметр]")
    fun parametersTemplate_tab() = element(xpath("//div[text()='Параметры шаблона']")).`as`("Таб [Параметры шаблона]")
    fun textVariables_listFields() = elements("input").`as`("Поля ввода для текстовых переменных")
    fun packTemplate_field() = element("[name='printing.packInstructionTemplateName']").`as`("Поле 'Пачка -> Шаблон'")
    fun firstStickerBoxTemplate_field() = element("[name='printing.caseInstructionTemplateName01']").`as`("Поле 'Первый стикер короба -> Шаблон'")
    fun firstStickerBoxImage_field() = element("[name='printing.caseInstructionImageName01']").`as`("Поле 'Первый стикер короба -> Изображение'")
    fun secondStickerBoxTemplate_field() = element("[name='printing.caseInstructionTemplateName02']").`as`("Поле 'Второй стикер короба -> Шаблон'")
    fun secondStickerBoxImage_field() = element("[name='printing.caseInstructionImageName02']").`as`("Поле 'Второй стикер короба -> Изображение'")
    fun stickerBlockTemplate_field() = element("[name='printing.blockInstructionTemplateName']").`as`("Поле 'Стикер блока -> Шаблон'")
    fun stickerBlockImage_field() = element("[name='printing.blockInstructionImageName']").`as`("Поле 'Стикер блока -> Изображение'")
    fun templatePrint_tab() = element(xpath("//div[text()='Шаблоны печати']")).`as`("Таб [Шаблоны печати]")
    fun textVariables_tab() = element(xpath("//div[text()='Текстовые переменные']")).`as`("Таб [Текстовые переменные]")
    fun aggregation(cigarettesInPack: String, packsInBlock: String, boxInPallet: String, validPacksInBlock: String, blocksInBox: String) {
        element("[name='main.numberOfCigarettesInPack']").`as`("Количество сигарет в пачке").setValue(cigarettesInPack)
        element("[name='main.numberOfPacksInBlock']").`as`("Количество пачек в блоке").setValue(packsInBlock)
        element("[name='main.numberOfCasesInPallet']").`as`("Количество коробов на паллете").setValue(boxInPallet)
        element("[name='main.allowedNumbersOfPacksInBlock']").`as`("Валидных пачек в блоке").setValue(validPacksInBlock)
        element("[name='main.numberOfBlocksInCase']").`as`("Блоков в коробе").setValue(blocksInBox)
    }
    fun packGTIN_field() = element("[name='main.gtinPack']").`as`("Поле 'GTIN пачки'")
    fun blockGTIN_field() = element("[name='main.gtinBlock']").`as`("Поле 'GTIN блока'")
    fun boxGTIN_field() = element("[name='main.gtinCase']").`as`("Поле 'GTIN короба'")
    fun productionMode_dropdown(): ElementsCollection {
        element("div[id='mui-component-select-main.productionMode']").`as`("Выпадающее меню 'Режим производства'").click()
        return items_dropdown()
    }
    fun valueParameter_dropdown(index: Int): ElementsCollection {
        valueParameter_field(index).click()
        return items_dropdown()
    }
    fun valueParameter_field(index: Int): SelenideElement = element("div[id='mui-component-select-parameters.$index.value']").`as`("Поле 'Значение параметра'(значение параметра шаблона пачки)")
    fun nameParameter_field(index: Int) = element("input[name='parameters.$index.variable']").`as`("Поле 'Параметр'(имя переменной шаблона пачки)")
    fun countryCode_dropdown(): ElementsCollection {
        element("div[id='mui-component-select-main.countryCode']").`as`("Выпадающее меню 'Код страны'").click()
        return items_dropdown()
    }
    fun factoryCode_dropdown(): ElementsCollection {
        element("div[id='mui-component-select-main.factoryCode']").`as`("Выпадающее меню 'Код фабрики'").click()
        return items_dropdown()
    }
    fun RSKU_field() = element("input[name='main.rsku']").`as`("Поле 'RSKU'")
    fun comment_field() = element("input[name='main.comment']").`as`("Поле 'Комментарии'")
    fun MRP_field() = element("input[name='main.mrp']").`as`("Поле 'МРЦ пачки'")
    fun nameService_field() = element("input[name='main.nameService']").`as`("Поле 'Наименование'")
    fun LSKU_field() = element("input[name='main.lsku']").`as`("Поле 'LSKU'")
    fun nameProduct_field() = element("input[name='nameMain']").`as`("Поле 'Наименование продукта'")

}