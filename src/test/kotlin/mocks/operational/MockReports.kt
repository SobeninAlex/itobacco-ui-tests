package mocks.operational

import com.codeborne.selenide.proxy.RequestMatcher
import java.io.File
import java.util.regex.Pattern

object MockReports {
    private val response = File("src/test/resources/mockResponse/operational_api_v1_reports.json").readText()
    private val urlRegex = Pattern.compile(".*/operational/api/v1/reports.*")
    private const val MOCK_NAME = "mockReports"

    fun mock() {
        support.mock(MOCK_NAME, RequestMatcher.HttpMethod.GET, urlRegex, response)
    }
}
