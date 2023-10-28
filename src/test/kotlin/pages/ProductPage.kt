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

    fun deleteParameter_Button() = element("form button > svg > path").`as`("[Удалить параметр]")
    fun addParameter_Button() = element(xpath("//button[text()='Добавить параметр']")).`as`("[+ Добавить параметр]")
    fun parametersTemplate_Tab() = element(xpath("//div[text()='Параметры шаблона']")).`as`("Таб [Параметры шаблона]")
    fun textVariables_FieldList() = elements("input").`as`("Поля ввода для текстовых переменных")
    fun packTemplate_Field() = element("[name='printing.packInstructionTemplateName']").`as`("Поле 'Пачка -> Шаблон'")
    fun firstStickerBoxTemplateField() = element("[name='printing.caseInstructionTemplateName01']").`as`("Поле 'Первый стикер короба -> Шаблон'")
    fun firstStickerBoxImageField() = element("[name='printing.caseInstructionImageName01']").`as`("Поле 'Первый стикер короба -> Изображение'")
    fun secondStickerBoxTemplateField() = element("[name='printing.caseInstructionTemplateName02']").`as`("Поле 'Второй стикер короба -> Шаблон'")
    fun secondStickerBoxImageField() = element("[name='printing.caseInstructionImageName02']").`as`("Поле 'Второй стикер короба -> Изображение'")
    fun stickerBlockTemplateField() = element("[name='printing.blockInstructionTemplateName']").`as`("Поле 'Стикер блока -> Шаблон'")
    fun stickerBlockImageField() = element("[name='printing.blockInstructionImageName']").`as`("Поле 'Стикер блока -> Изображение'")
    fun templatePrintTab() = element(xpath("//div[text()='Шаблоны печати']")).`as`("Таб [Шаблоны печати]")
    fun textVariablesTab() = element(xpath("//div[text()='Текстовые переменные']")).`as`("Таб [Текстовые переменные]")
    fun aggregation(cigarettesInPack: String, packsInBlock: String, boxInPallet: String, validPacksInBlock: String, blocksInBox: String) {
        element("[name='main.numberOfCigarettesInPack']").`as`("Количество сигарет в пачке").setValue(cigarettesInPack)
        element("[name='main.numberOfPacksInBlock']").`as`("Количество пачек в блоке").setValue(packsInBlock)
        element("[name='main.numberOfCasesInPallet']").`as`("Количество коробов на паллете").setValue(boxInPallet)
        element("[name='main.allowedNumbersOfPacksInBlock']").`as`("Валидных пачек в блоке").setValue(validPacksInBlock)
        element("[name='main.numberOfBlocksInCase']").`as`("Блоков в коробе").setValue(blocksInBox)
    }
    fun packGTIN() = element("[name='main.gtinPack']").`as`("Поле 'GTIN пачки'")
    fun blockGTIN() = element("[name='main.gtinBlock']").`as`("Поле 'GTIN блока'")
    fun boxGTIN() = element("[name='main.gtinCase']").`as`("Поле 'GTIN короба'")
    fun productionMode_DropDownList(): ElementsCollection {
        element("div[id='mui-component-select-main.productionMode']").`as`("Выпадающее меню 'Режим производства'").click()
        return items_dropDownList()
    }
    fun valueParameter_DropDownList(index: Int): ElementsCollection {
        valueParameterField(index).click()
        return items_dropDownList()
    }
    fun valueParameterField(index: Int): SelenideElement = element("div[id='mui-component-select-parameters.$index.value']").`as`("Выпадающее меню 'Значение параметра'(значение параметра шаблона пачки)")
    fun optionTemplateNameField(index: Int) = element("input[name='parameters.$index.variable']").`as`("Поле 'Параметр'(имя переменной шаблона пачки)")
    fun countryCode_DropDownList(): ElementsCollection {
        element("div[id='mui-component-select-main.countryCode']").`as`("Выпадающее меню 'Код страны'").click()
        return items_dropDownList()
    }
    fun factoryCode_DropDownList(): ElementsCollection {
        element("div[id='mui-component-select-main.factoryCode']").`as`("Выпадающее меню 'Код фабрики'").click()
        return items_dropDownList()
    }
    fun rskuField() = element("input[name='main.rsku']").`as`("Поле 'RSKU'")
    fun commentField() = element("input[name='main.comment']").`as`("Поле 'Комментарии'")
    fun mrpField() = element("input[name='main.mrp']").`as`("Поле 'МРЦ пачки'")
    fun nameServiceField() = element("input[name='main.nameService']").`as`("Поле 'Наименование'")
    fun lskuField() = element("input[name='main.lsku']").`as`("Поле 'LSKU'")
    fun nameProductField() = element("input[name='nameMain']").`as`("Поле 'Наименование продукта'")

}