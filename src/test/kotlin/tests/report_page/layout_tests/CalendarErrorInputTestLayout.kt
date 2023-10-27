package tests.report_page.layout_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.screenshot
import com.codeborne.selenide.Selenide.sleep
import com.github.romankh3.image.comparison.ImageComparison
import com.github.romankh3.image.comparison.ImageComparisonUtil
import com.github.romankh3.image.comparison.model.ImageComparisonResult
import com.github.romankh3.image.comparison.model.ImageComparisonState
import io.qameta.allure.Allure.ThrowableRunnableVoid
import io.qameta.allure.Allure.step
import io.qameta.allure.Attachment
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.openqa.selenium.OutputType
import pages.main_page.REPORT_PAGE
import support.dateNow
import support.editField
import support.formatter
import support.typeReport_OrderReport
import test_settings.TestBase
import java.io.File
import java.io.IOException
import java.nio.file.Files

@Epic("Тесты на страницу 'Отчеты'")
@Story("Тесты на верстку")
@DisplayName("Отображение ошибки инпутов календаря в сдйдбаре формирования отчета")
class CalendarErrorInputTestLayout : TestBase() {

    @Test
    @DisplayName("Отображение ошибки инпутов календаря в сдйдбаре формирования отчета")
    fun calendarErrorInputInSidebarReport_Layout(testInfo: TestInfo) {
        REPORT_PAGE.apply {
            step("Переход на страницу 'Отчеты'", ThrowableRunnableVoid {
                openPage()
                pageTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageTitle))
            })

            step("Клик по [Сформировать]", ThrowableRunnableVoid {
                createButton().click()
                sidebarTitle().shouldBe(Condition.visible).shouldHave(Condition.text(reportPageSidebarTitle))
                startDateInputInSidebar().shouldBe(Condition.visible)
            })

            step("Выбираем тип отчета", ThrowableRunnableVoid {
                typeReportSelector().find(Condition.text(typeReport_OrderReport)).click()
            })

            step("Устанавливаем дату 'ОТ' больше даты 'ДО'", ThrowableRunnableVoid {
                startDateInputInSidebar().editField(dateNow.format(formatter))
                endDateInputInSidebar().editField(dateNow.minusDays(1).format(formatter))
            })

            step("Клик по [Cкачать отчет]", ThrowableRunnableVoid {
                submitButton().click()
                sleep(300)
            })

            step("Сравниваем", ThrowableRunnableVoid {
                assertScreen(testInfo)
            })

        }
        sleep(100)
    }


    fun assertScreen(testInfo: TestInfo) {
        val expectedFileName = testInfo.testMethod.get().name + ".png"
        val expectedScreensDir = "src/test/resources/screens/"

        val actualScreenshot = screenshot(OutputType.FILE) as File
        val expectedScreenshot = File(expectedScreensDir + expectedFileName)

        if (!expectedScreenshot.exists()) {
            addImageToAllure("actual", actualScreenshot)
            throw IllegalArgumentException("Can't assert image. No reference screenshot. Actual screen can be downloaded from allure")
        }

        val expectedImage = ImageComparisonUtil.readImageFromResources(expectedScreensDir + expectedFileName)
        val actualImage = ImageComparisonUtil.readImageFromResources(actualScreenshot.toPath().toString())

        val resultDestination = File("build/diffs/diff_$expectedFileName")

        val imageComparison = ImageComparison(expectedImage, actualImage, resultDestination)
        val result: ImageComparisonResult = imageComparison.compareImages()

        if (!result.imageComparisonState.equals(ImageComparisonState.MATCH)) {
            addImageToAllure("actual", actualScreenshot)
            addImageToAllure("expected", expectedScreenshot)
            addImageToAllure("result", resultDestination)
        }

        Assertions.assertEquals(ImageComparisonState.MATCH, result.imageComparisonState)
    }

    fun addImageToAllure(name: String, file: File) {
        try {
            val image = Files.readAllBytes(file.toPath())
            saveScreenshot(name, image)
        } catch (ex: IOException) {
            throw RuntimeException("Can't read bytes")
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    fun saveScreenshot(name: String, image: ByteArray): ByteArray {
        return image
    }

}