package pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import org.openqa.selenium.By
import org.openqa.selenium.By.cssSelector
import pages.main_page.MainPage

class ChecksPage(subURL: String) : MainPage(subURL) {

    val nameChecked_locator: By = cssSelector("td:nth-of-type(1)")
    val descriptionChecks_locator: By = cssSelector("td:nth-of-type(2)")

    val checksPageTitle = "Проверки произведенной продукции"

    val nameChecksList = listOf(
        "Проверка на дубль пачки",
        "Проверка на дубль блока",
        "Проверка на дубль агрегации",
        "Ошибка восстановления короба",
        "Проверка на микс продукции",
        "Проверка времени между нанесением и агрегацией КМ",
        "Проверка структуры GTIN",
        "Проверка контрольной цифры GTIN",
        "Проверка структуры КМ",
        "Проверка эмиссии кода системой",
        "Проверка отправки кода на печать",
        "Проверка времени между нанесением и эмиссией КМ",
    )

    val descriptionChecksList = listOf(
        "Проверяется, что все пачки в агрегации от Sewtec имеют уникальный КМ в рамках агрегации (для всех режимов производства)",
        "Проверяется, что все блоки в агрегации от Sewtec имеют уникальный КМ в рамках агрегации (для 31, 91, 96 режимов производства)",
        "При получении агрегации от Sewtec в случае переагрегации проверяется, что для короба было произведено восстановление агрегации и оно было не более 10 минут назад (для всех режимов производства)",
        "Проверяется, что короб(а)-донор(ы), в который входили коды текущего короба, был восстановлен на Sewtec и восстановление не старше 30 дней (для 31, 91, 93 режимов производства)",
        "Проверяется, что все коды в агрегации от Sewtec относятся к одному GTIN и имеют одинаковую МРЦ (для всех режимов производства)",
        "Проверяется, что для всех кодов в агрегации от Sewtec между нанесением и агрегацией прошло не более 72 часов (для 31 режима производства)",
        "Проверяется, что GTIN у всех кодов в агрегации от Sewtec последовательно содержит цифры, указывающие на уровень упаковки, префикс компании, номер ссылки на номенклатуру товара и контрольную цифру (для всех режимов производства)",
        "Проверяется, что GTIN у всех кодов в агрегации Sewtec имеет верную контрольную цифру (для всех режимов производства)",
        "Проверяется, что у всех кодов в агрегации от Sewtec структура соответствует структуре КМ пачек и блоков для товарной группы табака, а структура КМ коробов справедлива для ITG (для всех режимов производства)",
        "Проверяется, что все коды в агрегации от Sewtec были эмитированы iTobacco (для 31 режима производства)",
        "Проверяется, что все коды в агрегации от Sewtec были отправлены на печать (для 31 режима производства)",
        "Проверяется, что для всех кодов в агрегации от Sewtec между нанесением и эмиссией прошло не более 30-n дней, по умолчанию n=4 дня (для 31 режима производства)",
    )

    fun checkToggles() {
        val toggles = elements("tbody tr input")

        var index = 0
        while (index < toggles.size()) { //TODO
            if (toggles[index].attr("checked") == "true") {
                toggles[index].click()
                sleep(500)
                toggles[index].shouldNotHave(Condition.attribute("checked"))
            } else {
                toggles[index].click()
                sleep(500)
                toggles[index].shouldHave(Condition.attribute("checked"))
            }
            index++
        }
    }

}