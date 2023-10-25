package tests.product_page

import com.codeborne.selenide.Condition.*
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.main_page.PRODUCT_PAGE
import services.PO_MANAGER
import support.*
import test_settings.TestBase

@Epic("Тесты на страницу 'Продукция'")
@DisplayName("Редактирование продукции")
class EditNewProduct : TestBase() {

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

    @Test
    @DisplayName("Редактирование новосозданной продукции")
    fun editNewProduct() {
        with(PRODUCT_PAGE) {
            step("Переход на страницу 'Продукция'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(visible).shouldHave(text(orderPageTitle))
            })

            step("Поиск продукцию по RSKU -> клик по найденной продукции", ThrowableRunnableVoid {
                searchField().setValue(RSKU)

                listItems()[0]
                    .shouldBe(visible)
                    .find("td")
                    .shouldHave(text(RSKU))
                    .click()

                sidebarTitle()
                    .shouldBe(visible)
                    .shouldHave(text(nameProduct))

                saveButton().shouldBe(disabled)
            })

            step("Редактирование поля 'Наименование (служебное)'", ThrowableRunnableVoid {
                nameServiceField().editField(newValue = serviceName)
            })

            step("Редактирование поля 'МРЦ пачки'", ThrowableRunnableVoid {
                mrpField().editField(mrp)
            })

            step("Редактирование поля 'Комментарии'", ThrowableRunnableVoid {
                commentField().editField(comment)
            })

            step("Редактирование GTIN-ов", ThrowableRunnableVoid {
                packGTIN().editField(packGTIN)
                blockGTIN().editField(blockGTIN)
                boxGTIN().editField(boxGTIN)
            })

            step("Переход к Шаблонам печати -> редактирование шаблонов", ThrowableRunnableVoid {
                templatePrintTab().click()

                packTemplateField().editField(packTemplate)
                stickerBlockTemplateField().editField(template)
                stickerBlockImageField().editField(image)
                firstStickerBoxTemplateField().editField(template)
                firstStickerBoxImageField().editField(image)
                secondStickerBoxTemplateField().editField(template)
                secondStickerBoxImageField().editField(image)
            })

            step("Переход к Параметрам шаблона -> редактирование параметров", ThrowableRunnableVoid {
                optionsTemplateTab().click()

                repeat(3) {
                    addOptionButton().click()
                }

                optionTemplateNameField(0).value = packVariables_RUSSIA
                valueOptionSelector(0).find(text(valueOption_Code)).click()

                optionTemplateNameField(1).value = packVariables_LINE3
                valueOptionSelector(1).find(text(valueOption_Date_LineCode)).click()

                optionTemplateNameField(2).value = packVariables_LINE4
                valueOptionSelector(2).find(text(valueOption_MRP_Date)).click()
            })

            step("Клик по [Сохранить]", ThrowableRunnableVoid {
                saveButton().click()
            })

            step("Проверка: изменения применились", ThrowableRunnableVoid{
                val listAtr = listItems()[0].findAll("td")
                listAtr[4].`as`("МРЦ пачки, коп").shouldHave(text("${mrp.toInt() / 100} 00"))
                listAtr[5].`as`("GTIN пачки").shouldHave(text(packGTIN))
                listAtr[6].`as`("GTIN блока").shouldHave(text(blockGTIN))
                listAtr[7].`as`("GTIN короба").shouldHave(text(boxGTIN))
            })

            step("Клик по продукции -> проверка: атрибуты изменились", ThrowableRunnableVoid {
                listItems()[0].click()
                sidebarTitle().shouldBe(visible)
                nameServiceField().shouldHave(value(serviceName))
                commentField().shouldHave(value(comment))
            })

            step("Переход к шаблонам печати -> проверка: шаблоны и изображения изменились", ThrowableRunnableVoid {
                templatePrintTab().click()
                packTemplateField().shouldHave(value(packTemplate))
                stickerBlockTemplateField().shouldHave(value(template))
                stickerBlockImageField().shouldHave(value(image))
                firstStickerBoxTemplateField().shouldHave(value(template))
                firstStickerBoxImageField().shouldHave(value(image))
                secondStickerBoxTemplateField().shouldHave(value(template))
                secondStickerBoxImageField().shouldHave(value(image))
            })

            step("Переход к параметрам шаблона -> проверка: переменные добавились", ThrowableRunnableVoid {
                optionsTemplateTab().click()

                optionTemplateNameField(0).shouldHave(value(packVariables_RUSSIA))
                valueOptionField(0).shouldHave(text(valueOption_Code))

                optionTemplateNameField(1).shouldHave(value(packVariables_LINE3))
                valueOptionField(1).shouldHave(text(valueOption_Date_LineCode))

                optionTemplateNameField(2).shouldHave(value(packVariables_LINE4))
                valueOptionField(2).shouldHave(text(valueOption_MRP_Date))
            })

            step("Проверка: [Сохранить] задизейблена -> клик по [Отмена] -> проверка: сайдбар закрылся", ThrowableRunnableVoid {
                saveButton().shouldBe(disabled)
                cancelButton().click()
                sidebar().shouldNotBe(visible)
            })
        }
    }

    @AfterEach
    fun after() {
        PO_MANAGER.deleteProduct(RSKU)
    }

}