package models

enum class User(val usernameOrPassword: String) {
    ADMIN("admin"),
    ENGINEER("engineer"),
    MASTER("master"),
    OPERATOR("operator"),
    PLANNING("planning"),
}