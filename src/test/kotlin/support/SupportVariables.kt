package support

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val RSKU = randomNumeric(8)
val nameProduct = randomAlphabetic(30)
const val productionMode = "31"
const val countryCode = "RU"
val mrp = "${(120..180).random()}00"
val comment = randomAlphabetic(100)
const val packGTIN = "04629308877037"
const val blockGTIN = "04629308877044"
const val boxGTIN = "14629308870004"
const val factoryCode = "344"
const val packTemplate = "itobacco-renue-crpt-videojet.xml"
const val template = "iTobacco_Renue100x150.eti"
const val bitmap = "iTobacco_34_34.bmp"
const val valueOption_DateLineCode = "Дата, время пр-ва / Код линии"
const val valueOption_MRPDate = "МРЦ / Дата производства"
const val valueOption_Code = "Код маркировки"
const val packVariables_RUSSIA = "RUSSIA"
const val packVariables_LINE3 = "LINE3"
const val packVariables_LINE4 = "LINE4"
const val nameService = "RU, Fi27"
const val typeReport_UsageReport = "Отчет по использованию"
const val typeReport_ProductReport = "Отчет по продукции"
const val typeReport_OrderReport = "Отчет по заказам"
val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy")
val dateNow: LocalDate = LocalDate.now()
