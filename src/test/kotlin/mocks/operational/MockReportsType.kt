package mocks.operational

import com.codeborne.selenide.proxy.RequestMatcher
import java.io.File
import java.util.regex.Pattern

object MockReportsType {
    private val response = File("src/test/resources/mockResponse/operational_api_v1_report-type.json").readText()
    private val urlRegex = Pattern.compile(".*/operational/api/v1/report-type")
    private const val MOCK_NAME = "mockReportsType"

    fun mock() {
        support.mock(MOCK_NAME, RequestMatcher.HttpMethod.GET, urlRegex, response)
    }
}