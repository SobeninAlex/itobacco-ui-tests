package tests.product_page

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition.*
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.PRODUCT_PAGE
import services.PoManager
import support.*
import test_settings.TestBase

@Epic("Тесты на страницу 'Продукция'")
@DisplayName("Успешное создание новой продукции")
class CreateNewProduct : TestBase() {

    @Test
    @DisplayName("Успешное создание новой продукции")
    fun createNewProduct_Success() {
        with(PRODUCT_PAGE) {
            step("Открытие страницы 'Продукция'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(visible).shouldHave(text(expectedPageTitle))
            })

            step("Клик по [Создать]", ThrowableRunnableVoid {
                createButton().click()
                sidebarTitle().shouldBe(visible).shouldHave(text(expectedSidebarTitle))
                saveButton().shouldBe(disabled)
            })

            step("Заполнение вкладки 'Атрибуты'", ThrowableRunnableVoid {
                nameProductField().setValue(nameProduct)
                rskuField().setValue(RSKU)
                lskuField().setValue("0$RSKU")
                nameServiceField().setValue(nameService)
                factoryCodeSelector().find(text(factoryCode)).click()
                mrpField().setValue(mrp)
                countryCodeSelector().find(text(countryCode)).click()
                commentField().setValue(comment)
                productionModeSelector().find(text(productionMode)).click()
                GTINs(pack = packGTIN, block = blockGTIN, box = boxGTIN)
                aggregation("20", "10", "31", "10", "20")
            })

            step("Клик по вкладке 'Шаблоны печати' -> Заполнение", ThrowableRunnableVoid {
                printTemplateTab().click()
                packTemplateField().setValue(packTemplate)
                stickerBlockTemplateField().setValue(template)
                stickerBlockImageField().setValue(bitmap)
                firstStickerBoxTemplateField().setValue(template)
                firstStickerBoxImageField().setValue(bitmap)
                secondStickerBoxTemplateField().setValue(template)
                secondStickerBoxImageField().setValue(bitmap)
            })

            step("Клик по вкладке 'Текстовые переменные' -> скролл", ThrowableRunnableVoid {
                textVariablesTab().click()
                testVariablesList().last().`as`("Последнее поле ввода 'Текстовые переменные'").scrollIntoView(true)
            })

            step("Клик по вкладке 'Параметры шаблона' -> Добавление и удаление параметров", ThrowableRunnableVoid{
                templateOptionsTab().click()
                addOptionButton().click()
                optionValueSelector().find(text(optionValue)).click()
                repeat(5) {
                    addOptionButton().click()
                }
                repeat(6) {
                    deleteOptionButton().click()
                }
            })

            step("Клик по [Сохранить]", ThrowableRunnableVoid {
                saveButton().click()
            })

            step("Используем поле 'Поиск', что бы проверить только что созданный продукт -> Проверяем атрибуты продукта", ThrowableRunnableVoid {
                searchField().setValue(RSKU)
                productList()
                    .shouldBe(CollectionCondition.size(1))

                val listAtr = productList()[0].findAll("td")
                listAtr[0].`as`("RSKU").shouldHave(text(RSKU))
                listAtr[1].`as`("Наименование").shouldHave(text(nameProduct))
                listAtr[2].`as`("Режим пр-ва").shouldHave(text(productionMode))
                listAtr[3].`as`("Страна").shouldHave(text(countryCode))
                listAtr[4].`as`("МРЦ пачки, коп").shouldHave(text("${mrp.toInt() / 100} 00"))
                listAtr[5].`as`("GTIN пачки").shouldHave(text(packGTIN))
                listAtr[6].`as`("GTIN блока").shouldHave(text(blockGTIN))
                listAtr[7].`as`("GTIN короба").shouldHave(text(boxGTIN))
            })
        }
    }

    @AfterEach
    fun after() {
        PoManager().deleteProduct(RSKU)
    }

}

