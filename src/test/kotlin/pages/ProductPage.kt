package pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import org.openqa.selenium.By.xpath

class ProductPage {

    val expectedPageTitle = "Продукция"
    val expectedSidebarTitle = "Создать продукт"

    private fun items() = elements("li[class]").`as`("Список элементов выпадающего списка")
    fun productList() = elements("tbody tr").`as`("Список продукции")
    fun searchField() = element(xpath("//label/following-sibling::div/input")).`as`("Поле 'Поиск'")
    fun saveButton() = element("button[type='submit']").`as`("[Сохранить]")
    fun deleteOptionButton() = element("form button > svg > path").`as`("[Удалить параметр]")
    fun addOptionButton() = element(xpath("//button[text()='Добавить параметр']")).`as`("[+ Добавить параметр]")
    fun templateOptionsTab() = element(xpath("//div[text()='Параметры шаблона']")).`as`("Таб [Параметры шаблона]")
    fun testVariablesList() = elements("input").`as`("Поля ввода для тестовых переменных")
    fun packTemplateField() = element("[name='printing.packInstructionTemplateName']").`as`("Поле 'Пачка -> Шаблон'")
    fun firstStickerBoxTemplateField() = element("[name='printing.caseInstructionTemplateName01']").`as`("Поле 'Первый стикер короба -> Шаблон'")
    fun firstStickerBoxImageField() = element("[name='printing.caseInstructionImageName01']").`as`("Поле 'Первый стикер короба -> Изображение'")
    fun secondStickerBoxTemplateField() = element("[name='printing.caseInstructionTemplateName02']").`as`("Поле 'Второй стикер короба -> Шаблон'")
    fun secondStickerBoxImageField() = element("[name='printing.caseInstructionImageName02']").`as`("Поле 'Второй стикер короба -> Изображение'")
    fun stickerBlockTemplateField() = element("[name='printing.blockInstructionTemplateName']").`as`("Поле 'Стикер блока -> Шаблон'")
    fun stickerBlockImageField() = element("[name='printing.blockInstructionImageName']").`as`("Поле 'Стикер блока -> Изображение'")
    fun printTemplateTab() = element(xpath("//div[text()='Шаблоны печати']")).`as`("Таб [Шаблоны печати]")
    fun textVariablesTab() = element(xpath("//div[text()='Текстовые переменные']")).`as`("Таб [Текстовые переменные]")
    fun aggregation(cigarettesInPack: String, packsInBlock: String, boxInPallet: String, validPacksInBlock: String, blocksInBox: String) {
        element("[name='main.numberOfCigarettesInPack']").`as`("Количество сигарет в пачке").setValue(cigarettesInPack)
        element("[name='main.numberOfPacksInBlock']").`as`("Количество пачек в блоке").setValue(packsInBlock)
        element("[name='main.numberOfCasesInPallet']").`as`("Количество коробов на паллете").setValue(boxInPallet)
        element("[name='main.allowedNumbersOfPacksInBlock']").`as`("Валидных пачек в блоке").setValue(validPacksInBlock)
        element("[name='main.numberOfBlocksInCase']").`as`("Блоков в коробе").setValue(blocksInBox)
    }
    fun GTINs(pack: String, block: String, box: String) {
        element("[name='main.gtinPack']").`as`("Поле 'GTIN пачки'").setValue(pack)
        element("[name='main.gtinBlock']").`as`("Поле 'GTIN блока'").setValue(block)
        element("[name='main.gtinCase']").`as`("Поле 'GTIN короба'").setValue(box)
    }
    fun productionModeSelector(): ElementsCollection {
        element("div[id='mui-component-select-main.productionMode']").`as`("Выпадающее меню 'Режим производства'").click()
        return items()
    }
    fun optionValueSelector(): ElementsCollection {
        element("div[id='mui-component-select-parameters.0.value']").`as`("Выпадающее меню 'Значение параметра'").click()
        return items()
    }
    fun countryCodeSelector(): ElementsCollection {
        element("div[id='mui-component-select-main.countryCode']").`as`("Выпадающее меню 'Код страны'").click()
        return items()
    }
    fun factoryCodeSelector(): ElementsCollection {
        element("div[id='mui-component-select-main.factoryCode']").`as`("Выпадающее меню 'Код фабрики'").click()
        return items()
    }
    fun rskuField() = element("input[name='main.rsku']").`as`("Поле 'RSKU'")
    fun commentField() = element("input[name='main.comment']").`as`("Поле 'Комментарии'")
    fun mrpField() = element("input[name='main.mrp']").`as`("Поле 'МРЦ пачки'")
    fun nameServiceField() = element("input[name='main.nameService']").`as`("Поле 'Наименование'")
    fun lskuField() = element("input[name='main.lsku']").`as`("Поле 'LSKU'")
    fun nameProductField() = element("input[name='nameMain']").`as`("Поле 'Наименование продукта'")
    fun sidebarTitle() = element("form h2").`as`("Заголовок сайдбара")
    fun createButton() = element(xpath("//a[.='Создать']")).`as`("[Создать]")
    fun pageTitle() = element(xpath("//h1[.='Продукция']")).`as`("Заголовок страницы")

    fun openPage() {
        Selenide.open("dict/products")
        Selenide.executeJavaScript<Any>("localStorage.setItem('password', 'admin');")
        Selenide.executeJavaScript<Any>("localStorage.setItem('username', 'admin');")
        Selenide.refresh()
    }

}