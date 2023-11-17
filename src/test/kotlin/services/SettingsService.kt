package services

import com.codeborne.selenide.BearerTokenCredentials
import com.codeborne.selenide.Configuration
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import java.io.File

private const val serviceName = "user-details"

open class SettingsService {

    val token: String

    init {
        token = getAuthToken(serviceName)
    }

    protected fun request(serviceName: String): RequestSpecification {
        return RestAssured.given()
            .headers("authorization", token)
            .baseUri("${Configuration.baseUrl}$serviceName/")
            .contentType(ContentType.JSON)
    }

    private fun getAuthToken(serviceName: String): String {
        val json = File("src/test/resources/body/user.json").readText()
        return RestAssured.given()
            .baseUri("${Configuration.baseUrl}$serviceName/")
            .contentType(ContentType.JSON)
            .body(json)
            .post("api/v1/auth")
            .then().log().all()
            .statusCode(200)
            .extract()
            .response()
            .header("authorization")
    }

}