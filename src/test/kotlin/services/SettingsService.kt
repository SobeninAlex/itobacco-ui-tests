package services

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import services.Namespace.DEV_03
import java.io.File

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