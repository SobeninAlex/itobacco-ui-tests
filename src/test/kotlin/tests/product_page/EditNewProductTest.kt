package tests.product_page

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.sleep
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.main_page.PRODUCT_PAGE
import services.PO_MANAGER
import support.*
import test_settings.TestBase

@Epic("Тесты на страницу 'Продукция'")
@Story("Функциональные тесты")
@DisplayName("Редактирование продукции")
class EditNewProductTest : TestBase() {

    private val serviceName = randomAlphabetic(5)
    private val comment = randomAlphabetic(30)
    private val packGTIN = "04629338877021"
    private val blockGTIN = "04629308877921"
    private val boxGTIN = "14629308879021"
    private val packTemplate = "itobacco-renue-crpt-videojet3.xml"
    private val template = "iTobacco_Renue_CRPT_68_38.eti"
    private val image = "iTobacco_83x16mm.bmp"

    @BeforeEach
    fun before() {
        PO_MANAGER.createNewProduct(RSKU, nameProduct)
    }

    @AfterEach
    fun after() {
        PO_MANAGER.deleteProduct(RSKU)
    }

    @Test
    @DisplayName("Редактирование новосозданной продукции")
    fun editNewProduct() {
        with(PRODUCT_PAGE) {
            step("Переход на страницу 'Продукция'", ThrowableRunnableVoid {
                openPage()
                sleep(1000)
                pageTitle().shouldBe(visible).shouldHave(text(orderPageTitle))
            })

            step("Поиск продукцию по RSKU -> клик по найденной продукции", ThrowableRunnableVoid {
                search_field().setValue(RSKU)

                listItems_list()[0]
                    .shouldBe(visible)
                    .find("td")
                    .shouldHave(text(RSKU))
                    .click()

                sidebarTitle()
                    .shouldBe(visible)
                    .shouldHave(text(nameProduct))

                submit_button().shouldBe(disabled)
            })

            step("Редактирование поля 'Наименование (служебное)'", ThrowableRunnableVoid {
                nameService_field().editField(newValue = serviceName)
            })

            step("Редактирование поля 'МРЦ пачки'", ThrowableRunnableVoid {
                MRP_field().editField(mrp)
            })

            step("Редактирование поля 'Комментарии'", ThrowableRunnableVoid {
                comment_field().editField(comment)
            })

            step("Редактирование GTIN-ов", ThrowableRunnableVoid {
                packGTIN_field().editField(packGTIN)
                blockGTIN_field().editField(blockGTIN)
                boxGTIN_field().editField(boxGTIN)
            })

            step("Переход к Шаблонам печати -> редактирование шаблонов", ThrowableRunnableVoid {
                templatePrint_tab().click()

                packTemplate_field().editField(packTemplate)
                stickerBlockTemplate_field().editField(template)
                stickerBlockImage_field().editField(image)
                firstStickerBoxTemplate_field().editField(template)
                firstStickerBoxImage_field().editField(image)
                secondStickerBoxTemplate_field().editField(template)
                secondStickerBoxImage_field().editField(image)
            })

            step("Переход к Параметрам шаблона -> редактирование параметров", ThrowableRunnableVoid {
                parametersTemplate_tab().click()

                repeat(3) {
                    addParameter_button().click()
                }

                nameParameter_field(0).value = packVariables_RUSSIA
                valueParameter_dropdown(0).find(text(valueOption_Code)).click()

                nameParameter_field(1).value = packVariables_LINE3
                valueParameter_dropdown(1).find(text(valueOption_DateLineCode)).click()

                nameParameter_field(2).value = packVariables_LINE4
                valueParameter_dropdown(2).find(text(valueOption_MRPDate)).click()
            })

            step("Клик по [Сохранить]", ThrowableRunnableVoid {
                submit_button().click()
            })

            step("Проверка: изменения применились", ThrowableRunnableVoid{
                val listAtr = listItems_list()[0].findAll("td")
                listAtr[4].`as`("МРЦ пачки, коп").shouldHave(text("${mrp.toInt() / 100} 00"))
                listAtr[5].`as`("GTIN пачки").shouldHave(text(packGTIN))
                listAtr[6].`as`("GTIN блока").shouldHave(text(blockGTIN))
                listAtr[7].`as`("GTIN короба").shouldHave(text(boxGTIN))
            })

            step("Клик по продукции -> проверка: атрибуты изменились", ThrowableRunnableVoid {
                listItems_list().first().click()
                sidebarTitle().shouldBe(visible)
                nameService_field().shouldHave(value(serviceName))
                comment_field().shouldHave(value(comment))
            })

            step("Переход к шаблонам печати -> проверка: шаблоны и изображения изменились", ThrowableRunnableVoid {
                templatePrint_tab().click()
                packTemplate_field().shouldHave(value(packTemplate))
                stickerBlockTemplate_field().shouldHave(value(template))
                stickerBlockImage_field().shouldHave(value(image))
                firstStickerBoxTemplate_field().shouldHave(value(template))
                firstStickerBoxImage_field().shouldHave(value(image))
                secondStickerBoxTemplate_field().shouldHave(value(template))
                secondStickerBoxImage_field().shouldHave(value(image))
            })

            step("Переход к параметрам шаблона -> проверка: переменные добавились", ThrowableRunnableVoid {
                parametersTemplate_tab().click()

                nameParameter_field(0).shouldHave(value(packVariables_RUSSIA))
                valueParameter_field(0).shouldHave(text(valueOption_Code))

                nameParameter_field(1).shouldHave(value(packVariables_LINE3))
                valueParameter_field(1).shouldHave(text(valueOption_DateLineCode))

                nameParameter_field(2).shouldHave(value(packVariables_LINE4))
                valueParameter_field(2).shouldHave(text(valueOption_MRPDate))
            })

            step("Проверка: [Сохранить] задизейблена -> клик по [Отмена] -> проверка: сайдбар закрылся", ThrowableRunnableVoid {
                submit_button().shouldBe(disabled)
                cancel_button().click()
                sidebar().shouldNotBe(visible)
            })
        }
    }

}