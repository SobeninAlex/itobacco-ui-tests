package services

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

open class SettingsService {

    protected fun request(serviceName: String): RequestSpecification {
        return RestAssured.given()
            .auth()
            .preemptive()
            .basic("admin", "admin")
            .baseUri("http://itobacco-dev-03.k8s.renue.yc/$serviceName/")
            .contentType(ContentType.JSON)
    }

}