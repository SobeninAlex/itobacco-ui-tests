package services

import com.codeborne.selenide.Configuration
import groovy.util.logging.Slf4j
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.json.JSONObject
import java.io.File

open class SettingsService {
    private val serviceName = "user-details"
    private val token: String

    constructor() {
        token = auth()
    }

    protected fun authRequest(serviceName: String): RequestSpecification {
        return RestAssured.given()
            .headers(
                "authorization",
                token
            )
            .baseUri("${Configuration.baseUrl}$serviceName/")
            .contentType(ContentType.JSON)
    }

    protected fun noAuthRequest(serviceName: String): RequestSpecification {
        return RestAssured.given()
            .baseUri("${Configuration.baseUrl}$serviceName/")
            .contentType(ContentType.JSON)
    }


    private fun auth(): String {
        val json = File("src/test/resources/body/user.json").readText()
        val response = noAuthRequest(serviceName)
            .body(json)
            .post("/api/v1/auth")
            .then().log().all()
            .statusCode(200)
            .extract()
            .response()
            .header("authorization")
        return response
    }

}