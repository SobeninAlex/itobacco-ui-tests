package mocks.po_manager

import com.codeborne.selenide.proxy.RequestMatcher
import java.io.File
import java.util.regex.Pattern

object MockProductionMod91 {
    private val response = File("src/test/resources/mockResponse/po-manager_api_v1_dict_production-mode_91.json").readText()
    private val urlRegex = Pattern.compile(".*/po-manager/api/v1/dict/production-mode/91")
    private const val MOCK_NAME = "mockProductionMod91"

    fun mock() {
        support.mock(MOCK_NAME, RequestMatcher.HttpMethod.GET, urlRegex, response)
    }
}