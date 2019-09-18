import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.*
import ru.sbt.fedorov.kotlin.calendar

fun main(args: Array<String>) {
    try {
        testing()
    } catch (e: Exception) {
        println(e)
    }
}

fun testing() {
    // Заполнение объекта

    val testGetFullProductListRs = object : GetFullProductListRs() {}
    val testGetFullProductListRs2 = object : GetFullProductListRs() {}
    val status = object : GetFullProductListRs.Status() {}
    val status2 = object : GetFullProductListRs.Status() {}
    status.statusCode = 0L
    status2.statusCode = 2L
    testGetFullProductListRs.status = status
    testGetFullProductListRs.rqUID = "qwe123"


    /** 1. поле status/statusCode == 0 */
    println("1. поле status/statusCode == 0")
    val getFullProductListRs = object : GetFullProductListRs() {}
    if (testGetFullProductListRs.status.statusCode == 0L)
        println(true)
    else println(false)

    testGetFullProductListRs.status.statusCode = 1

    if (testGetFullProductListRs.status.statusCode == 0L)
        println(true)
    else println(false)


    /** 2. список status/additionalStatuses - пуст или == null */
    println("2. список status/additionalStatuses - пуст или == null")
    // Заполнение
    val status3 = object : GetFullProductListRs.Status() {}
    val additionalStatusGFPLType1 = object : AdditionalStatusGFPLType() {}
    val additionalStatusGFPLType2 = object : AdditionalStatusGFPLType() {}
    val additionalStatuses = arrayListOf(additionalStatusGFPLType1, additionalStatusGFPLType2)
    getFullProductListRs.status = status3

    if (getFullProductListRs.status.additionalStatuses.isEmpty())
        println(true)
    else
        println(false)
    status3.additionalStatuses.add(additionalStatusGFPLType1)

    if (getFullProductListRs.status.additionalStatuses.isEmpty())
        println(true)
    else
        println(false)


    /** 3. все поля statusCode в коллекции status/additionalStatus == 0 */
    println("3. все поля statusCode в коллекции status/additionalStatus == 0")
    var result3 = true
    getFullProductListRs?.status?.additionalStatuses?.forEach {
        if (it.statusCode == 0L)
            result3 = result3 && true
        else
            result3 = result3 && false
    }
    println(result3)


    /** 4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts,
    и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts. */
    val statusDetailByProductList = object : GetFullProductListRs.StatusDetailByProductList() {}
    val statusDetailByProductType = object : StatusDetailByProductType() {}
    val timeMetricListType = object : TimeMetricListType() {}
    val timeMetricType = object : TimeMetricType() {}
    val timeOutList = object : TimeRangeType() {}

    timeOutList.timeMin = calendar
    calendar.firstDayOfWeek = 15
    timeMetricType.withTimeOutList(timeOutList)
    timeMetricListType.metrics.add(timeMetricType)
    statusDetailByProductType.setMetricList(timeMetricListType)
    statusDetailByProductList.statusDetailByProducts.add(statusDetailByProductType)
    getFullProductListRs.statusDetailByProductList = statusDetailByProductList

    println("4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts, и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts.")
    val currentValue = 14
    var result4 = true
    try {
        println("firstDayOfWeek " + getFullProductListRs.statusDetailByProductList.statusDetailByProducts.get(0).metricList.metrics.get(0).timeOutList.timeMin.firstDayOfWeek)
        getFullProductListRs.statusDetailByProductList.statusDetailByProducts.forEach {
            it.metricList.metrics.forEach { it ->
                if (currentValue > it.timeOutList.timeMin.firstDayOfWeek)
                    result4 = result4 && true
                else
                    result4 = result4 && false
            }
        }
    } catch (e: Exception) {
        println(e)
    }
    println(result4)


    /** 5. в коллекции acctTypes нет значений DEPOSIT и IMA. */
    println("5. в коллекции acctTypes нет значений DEPOSIT и IMA.")
    var result5 = true
    getFullProductListRs.acctTypes.forEach {
        if (it.toString() == "DEPOSIT" || it.toString() == "IMA")
            result5 = result5 && false
        else
            result5 = result5 && true
    }
    println(result5)

    val accType = AcctTypeType.IMA
    getFullProductListRs.acctTypes.add(accType)
    result5 = true
    getFullProductListRs.acctTypes.forEach {
        if (it.toString() == "DEPOSIT" || it.toString() == "IMA")
            result5 = result5 && false
        else
            result5 = result5 && true
    }
    println(result5)


    /** 6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null. */
    //        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList2 = null
    //        getFullProductListRs.status.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2)
    println("6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null.")
    if (testGetFullProductListRs.rqUID == "qwe123" && testGetFullProductListRs.status.additionalStatuses.isEmpty())
        println(true)
    else
        println(false)


    /** 7. все поля status/statusDesc начинаются с "success". */
    println("7. все поля status/statusDesc начинаются с success.")
    getFullProductListRs.status.statusDesc = "successfully"

    if (getFullProductListRs.status.statusDesc.startsWith("success"))
        println(true)
    else
        println(false)


    /** 8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению. */
    println("8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению.")
    if (getFullProductListRs.acctTypes.size == 1 && getFullProductListRs.acctTypes[0] == AcctTypeType.IMA)
        println(true)
    else
        println(false)

    getFullProductListRs.acctTypes.add(AcctTypeType.CARD)

    if (getFullProductListRs.acctTypes.size == 1 && getFullProductListRs.acctTypes[0] == AcctTypeType.IMA)
        println(true)
    else
        println(false)


    /** 9. проверить что operUID и syatemId не равны null. */
    println("9. проверить что operUID и systemId не равны null.")

    if (getFullProductListRs.operUID != null && getFullProductListRs.systemId != null)
        println(true)
    else
        println(false)

    getFullProductListRs.operUID = "operUID"
    getFullProductListRs.systemId = "SystemId"

    if (getFullProductListRs.operUID != null && getFullProductListRs.systemId != null)
        println(true)
    else
        println(false)
}
