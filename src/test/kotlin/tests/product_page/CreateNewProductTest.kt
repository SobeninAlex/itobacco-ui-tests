package tests.product_page

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.sleep
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
                create_button().click()
                sidebarTitle().shouldBe(visible).shouldHave(text(orderPageSidebarTitle))
                submit_button().shouldBe(disabled)
            })

            step("Заполнение вкладки 'Атрибуты'", ThrowableRunnableVoid {
                nameProduct_field().setValue(nameProduct)
                RSKU_field().setValue(RSKU)
                LSKU_field().setValue("0$RSKU")
                nameService_field().setValue(nameService)
                factoryCode_dropdown().find(text(factoryCode)).click()
                MRP_field().setValue(mrp)
                countryCode_dropdown().find(text(countryCode)).click()
                comment_field().setValue(comment)
                productionMode_dropdown().find(text(productionMode)).click()
                packGTIN_field().value = packGTIN
                blockGTIN_field().value = blockGTIN
                boxGTIN_field().value = boxGTIN
                aggregation("20", "10", "31", "10", "20")
            })

            step("Клик по вкладке 'Шаблоны печати' -> Заполнение", ThrowableRunnableVoid {
                templatePrint_tab().click()
                packTemplate_field().setValue(packTemplate)
                stickerBlockTemplate_field().setValue(template)
                stickerBlockImage_field().setValue(bitmap)
                firstStickerBoxTemplate_field().setValue(template)
                firstStickerBoxImage_field().setValue(bitmap)
                secondStickerBoxTemplate_field().setValue(template)
                secondStickerBoxImage_field().setValue(bitmap)
            })

            step("Клик по вкладке 'Текстовые переменные' -> Скролл до последнего элемента", ThrowableRunnableVoid {
                textVariables_tab().click()
                textVariables_listFields().last().`as`("Последнее поле ввода 'Текстовые переменные'").scrollIntoView(true)
            })

            step("Клик по вкладке 'Параметры шаблона' -> Добавление и удаление параметров", ThrowableRunnableVoid{
                parametersTemplate_tab().click()
                addParameter_button().click()
                valueParameter_dropdown(0).find(text(valueOption_DateLineCode)).click()
                repeat(5) {
                    addParameter_button().click()
                }
                repeat(6) {
                    deleteParameter_button().click()
                }
            })

            step("Клик по [Сохранить]", ThrowableRunnableVoid {
                submit_button().click()
//                cancel_Button().click() //TODO
            })

            step("Используем поле 'Поиск' -> Поиск только что созданного продукта -> Проверка атрибутов продукта", ThrowableRunnableVoid {
                search_field().setValue(RSKU)
                listItems_list()
                    .shouldBe(CollectionCondition.size(1))

                val listAtr = listItems_list()[0].findAll("td")
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

