package support

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.proxy.RequestMatcher
import com.codeborne.selenide.proxy.RequestMatchers
import com.github.romankh3.image.comparison.ImageComparison
import com.github.romankh3.image.comparison.ImageComparisonUtil
import com.github.romankh3.image.comparison.model.ImageComparisonResult
import com.github.romankh3.image.comparison.model.ImageComparisonState
import io.qameta.allure.Attachment
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInfo
import org.openqa.selenium.Keys
import org.openqa.selenium.OutputType
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.util.regex.Pattern

//возвращает рандомную, латинскую, буквенную строку заданной длины
fun randomAlphabetic(length: Int): String {
    val charset = ('A'..'Z') + ('a'..'z')
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

//возвращает рандомное число заданной дины
fun randomNumeric(length: Int): String {
    val charset = 0..9
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

//расширяем интерфейс SelenideElement -> добавляем новую функцию для редактирования элемента
fun SelenideElement.editField(newValue: String) {
    sendKeys(Keys.CONTROL, "A")
    sendKeys(Keys.DELETE)
    value = newValue
}

//метод для скрина и сравнения эталонного и фактического скриншота
fun assertScreen(testInfo: TestInfo) {
    Selenide.sleep(700)
    val expectedFileName = testInfo.testMethod.get().name + ".png"
    val expectedScreensDir = "src/test/resources/screens/"

    val actualScreenshot = Selenide.screenshot(OutputType.FILE) as File
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

private fun addImageToAllure(name: String, file: File) {
    try {
        val image = Files.readAllBytes(file.toPath())
        saveScreenshot(name, image)
    } catch (ex: IOException) {
        throw RuntimeException("Can't read bytes")
    }
}

@Attachment(value = "{name}", type = "image/png")
private fun saveScreenshot(name: String, image: ByteArray): ByteArray {
    return image
}

fun mock(mockName: String, method: RequestMatcher.HttpMethod, urlRegex: Pattern, response: String) {
    Selenide.open()
    WebDriverRunner.getSelenideProxy().responseMocker().mockText(
        mockName,
        RequestMatchers.urlMatches(
            method,
            urlRegex
        )
    ) { response }
}