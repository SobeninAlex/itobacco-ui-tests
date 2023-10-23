package tests.product_page

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.sleep
import io.qameta.allure.Allure
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Epic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.openqa.selenium.Keys
import pages.PRODUCT_PAGE
import services.PoManager
import support.*
import test_settings.TestBase

@Epic("Тесты на страницу 'Продукция'")
@DisplayName("Редактирование продукции")
class EditNewProduct : TestBase() {

    private val serviceName = randomAlphabetic(5)

    @BeforeEach
    fun before() {
        PoManager().createNewProduct(RSKU, nameProduct)
    }

    @Test
    @DisplayName("Редактирование новосозданной продукции")
    fun editNewProduct() {
        with(PRODUCT_PAGE) {
            step("Открытие страницы 'Продукция'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(visible).shouldHave(text(expectedPageTitle))
            })

            step("Ищем продукцию по RSKU -> кликаем", ThrowableRunnableVoid {
                searchField().setValue(RSKU)

                productList()[0]
                    .shouldBe(visible)
                    .find("td")
                    .shouldHave(text(RSKU))
                    .click()

                sidebarTitle()
                    .shouldBe(visible)
                    .shouldHave(text(nameProduct))
            })

            step("Редактируем продукт", ThrowableRunnableVoid {
                with(nameServiceField()) {
                    sendKeys(Keys.CONTROL, "A")
                    sendKeys(Keys.DELETE)
                    value = serviceName
                }

                with(mrpField()) {
                    sendKeys(Keys.CONTROL, "A")
                    sendKeys(Keys.DELETE)
                    value = mrp
                }

                //TODO

            })

        }
        sleep(100)
    }

    @AfterEach
    fun after() {
        PoManager().deleteProduct(RSKU)
    }

}