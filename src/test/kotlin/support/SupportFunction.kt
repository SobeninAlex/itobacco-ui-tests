package support

//возвращает рандомную, латинскую, буквенную строку заданной длины
fun randomAlphabetic(length: Int): String {
    val charset = ('A'..'Z') + ('a'..'z')
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}

//возвращает рандомное число заданной дины
fun randomNumeric(length: Int): String {
    val charset = 0..9
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}