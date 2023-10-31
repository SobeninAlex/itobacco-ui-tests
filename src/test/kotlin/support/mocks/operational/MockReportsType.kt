package support.mocks.operational

import com.codeborne.selenide.proxy.RequestMatcher
import java.io.File

object MockReportsType {
    private val mockResponse = File("src/test/resources/mockResponse/operational_api_v1_report-type.json").readText()
    private const val METHOD_URL = "http://itobacco-dev-03.k8s.renue.yc/operational/api/v1/report-type"
    private const val MOCK_NAME = "mockReportsType"

    fun mock() {
        support.mock(MOCK_NAME, RequestMatcher.HttpMethod.GET, METHOD_URL, mockResponse)
    }
}