package tests.product_page

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.sleep
import com.codeborne.selenide.WebDriverConditions
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.main_page.PRODUCT_PAGE
import services.PO_MANAGER
import support.*
import test_settings.TestBase

@Epic("Тесты на страницу 'Продукция'")
@Story("Функциональные тесты")
@DisplayName("Успешное создание новой продукции")
class CreateNewProductTest : TestBase() {

    @Test
    @DisplayName("Успешное создание новой продукции")
    fun createNewProduct_Success() {
        with(PRODUCT_PAGE) {
            step("Переход на страницу 'Продукция'", ThrowableRunnableVoid {
                openPage()
                sleep(1000)
                pageTitle().shouldBe(visible).shouldHave(text(orderPageTitle))
            })

            step("Клик по [Создать]", ThrowableRunnableVoid {
                create_Button().click()
                sidebarTitle().shouldBe(visible).shouldHave(text(orderPageSidebarTitle))
                submit_Button().shouldBe(disabled)
            })

            step("Заполнение вкладки 'Атрибуты'", ThrowableRunnableVoid {
                nameProductField().setValue(nameProduct)
                rskuField().setValue(RSKU)
                lskuField().setValue("0$RSKU")
                nameServiceField().setValue(nameService)
                factoryCode_DropDownList().find(text(factoryCode)).click()
                mrpField().setValue(mrp)
                countryCode_DropDownList().find(text(countryCode)).click()
                commentField().setValue(comment)
                productionMode_DropDownList().find(text(productionMode)).click()
                packGTIN().value = packGTIN
                blockGTIN().value = blockGTIN
                boxGTIN().value = boxGTIN
                aggregation("20", "10", "31", "10", "20")
            })

            step("Клик по вкладке 'Шаблоны печати' -> Заполнение", ThrowableRunnableVoid {
                templatePrintTab().click()
                packTemplate_Field().setValue(packTemplate)
                stickerBlockTemplateField().setValue(template)
                stickerBlockImageField().setValue(bitmap)
                firstStickerBoxTemplateField().setValue(template)
                firstStickerBoxImageField().setValue(bitmap)
                secondStickerBoxTemplateField().setValue(template)
                secondStickerBoxImageField().setValue(bitmap)
            })

            step("Клик по вкладке 'Текстовые переменные' -> Скролл до последнего элемента", ThrowableRunnableVoid {
                textVariablesTab().click()
                textVariables_FieldList().last().`as`("Последнее поле ввода 'Текстовые переменные'").scrollIntoView(true)
            })

            step("Клик по вкладке 'Параметры шаблона' -> Добавление и удаление параметров", ThrowableRunnableVoid{
                parametersTemplate_Tab().click()
                addParameter_Button().click()
                valueParameter_DropDownList(0).find(text(valueOption_Date_LineCode)).click()
                repeat(5) {
                    addParameter_Button().click()
                }
                repeat(6) {
                    deleteParameter_Button().click()
                }
            })

            step("Клик по [Сохранить]", ThrowableRunnableVoid {
                submit_Button().click()
            })

            step("Используем поле 'Поиск' -> Поиск только что созданного продукта -> Проверка атрибутов продукта", ThrowableRunnableVoid {
                search_Field().setValue(RSKU)
                listItems_List()
                    .shouldBe(CollectionCondition.size(1))

                val listAtr = listItems_List()[0].findAll("td")
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
        PO_MANAGER.deleteProduct(RSKU)
    }

}

