package services

import org.json.JSONObject
import support.nameProduct
import java.io.File

private const val serviceName = "po-manager"

class PoManager : SettingsService() {

    fun deleteProduct(productRSKU: String) {
        request(serviceName)
            .delete("api/v1/product-info/$productRSKU")
            .then().log().all()
            .statusCode(200)
    }

    fun createNewProduct(productRSKU: String, nameProduct: String) {
        val json = JSONObject(File("src/test/resources/body/create_new_product.json").readText())
            .put("id", productRSKU)
            .put("productFullName", nameProduct)
            .toString()

        request(serviceName)
            .body(json)
            .post("api/v1/product-info")
            .then().log().all()
            .statusCode(200)
    }

}
