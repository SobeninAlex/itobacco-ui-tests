package services

import com.codeborne.selenide.Configuration
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

open class SettingsService {

    protected fun request(serviceName: String): RequestSpecification {
        return RestAssured.given()
            .auth()
            .preemptive()
            .basic("admin", "admin")
            .baseUri("${Configuration.baseUrl}$serviceName/")
            .contentType(ContentType.JSON)
    }

}