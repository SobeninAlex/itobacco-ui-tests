package mocks.po_manager

import com.codeborne.selenide.proxy.RequestMatcher
import java.util.regex.Pattern

object MockOrders {
    private val response = "" //TODO
    private val urlRegex = Pattern.compile(".*/po-manager/api/v1/orders.*")
    private const val MOCK_NAME = "mockOrders"

    fun mock() {
        support.mock(MOCK_NAME, RequestMatcher.HttpMethod.GET, urlRegex, response)
    }
}