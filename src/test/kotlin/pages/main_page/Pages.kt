package pages.main_page

import pages.AuthorizationPage
import pages.ChecksPage
import pages.OrderPage
import pages.ProductPage

val AUTHORIZATION_PAGE = AuthorizationPage("/")
val ORDER_PAGE = OrderPage("orders/main")
val PRODUCT_PAGE = ProductPage("dict/products")
val CHECKS_PAGE = ChecksPage("settings/checks")