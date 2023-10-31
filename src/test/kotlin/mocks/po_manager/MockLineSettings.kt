package mocks.po_manager

import com.codeborne.selenide.proxy.RequestMatcher
import java.io.File
import java.util.regex.Pattern

object MockLineSettings {
    private val response = File("src/test/resources/mockResponse/po-manager_api_v1_line-settings.json").readText() //TODO
    private val urlRegex = Pattern.compile(".*/po-manager/api/v1/line-settings.*")
    private const val MOCK_NAME = "mockLineSettings"

    fun mock() {
        support.mock(MOCK_NAME, RequestMatcher.HttpMethod.GET, urlRegex, response)
    }
}