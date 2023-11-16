package services

import org.json.JSONObject
import java.io.File


class UserDetails : SettingsService()  {
    private val serviceName = "user-details-service"

    fun auth (): String {
        val json = JSONObject(File("src/test/resources/body/user.json").readText())
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