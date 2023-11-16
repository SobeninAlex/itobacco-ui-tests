package models

enum class User (private val usernameOrPassword: String) {
    ADMIN ("admin"),
    ENGINEER("engineer"),
    MASTER("master"),
    OPERATOR("operator"),
    PLANNING("planning");

    fun getUsernameOrPassword():String{
        return usernameOrPassword
    }
}