package ru.sbt.fedorov.kotlin

import java.sql.Timestamp
import ru.sbt.fedorov.testClasses.Client
import ru.sbt.fedorov.testClasses.FactorLight
import ru.sbt.fedorov.testClasses.Status

fun main(args: Array<String>) {
    try {
        testing()
    } catch (e: Exception) {
        println(e)
    }
}


fun testing() {
    val client = object : Client() {}
    val client2 = object : Client() {}
    val timeStamp = Timestamp(12345)
    val timeStamp2 = Timestamp(54321)
    val status = Status(234, null, null)
    val status2 = Status(0, null, null)
    val factorLight = FactorLight.GREEN
    val parameterA = "Moscow"
    val parameterB = "NY"
    val parameterC = "Boston"
    val parameterD = "LA"
    val parameterE = "LA"
    val addresses = arrayListOf(parameterA, parameterB, parameterC)

    client.time = timeStamp
    client.scope = "testScope"
    client.status = status
    client2.status = status2
    client.factorLight = factorLight
    client.addresses = addresses
    client.id = 4


    /** 1. Равно ли поле time какому-то конкретному значению. */
    println("1. Равно ли поле time какому-то конкретному значению.")
    if (client.time.toString() == timeStamp.toString()) {
        println(true)
    } else {
        println(false)
    }
    if (client.time.toString() == timeStamp2.toString()) {
        println(true)
    } else {
        println(false)
    }


    /** 2. Равно ли поле scope какому-то конкретному значению. */
    println("2. Равно ли поле scope какому-то конкретному значению.")
    if (client.scope == "notTestScope") {
        println(true)
    } else {
        println(false)
    }
    if (client.scope == "testScope") {
        println(true)
    } else {
        println(false)
    }


    /** 3. Равно ли поле status/code какому то значению. */
    println("3. Равно ли поле status/code какому то значению.")
    if (client.status.code == 32545L) {
        println(true)
    } else {
        println(false)
    }
    if (client.status.code == 234L) {
        println(true)
    } else {
        println(false)
    }


    /** 4. Равно ли поле status/code 0. */
    println("4. Равно ли поле status/code 0.")
    if (client.status.code == 0L) {
        println(true)
    } else {
        println(false)
    }
    if (client2.status.code == 0L) {
        println(true)
    } else {
        println(false)
    }


    /** 5. Равно ли поле factorLight какому-то значению. */
    println("5. Равно ли поле factorLight какому-то значению.")
    if (client.factorLight == FactorLight.RED) {
        println(true)
    } else {
        println(false)
    }
    if (client.factorLight == FactorLight.GREEN) {
        println(true)
    } else {
        println(false)
    }


    /** 6. Равно ли поле scope одному из значений a,b или с. or */
    println("6. Равно ли поле scope одному из значений a,b или с.")
    if (client.scope == "notTestScope" || client.scope == "notTestScope1" || client.scope == "notTestScope2") {
        println(true)
    } else {
        println(false)
    }
    if (client.scope == "notTestScope" || client.scope == "testScope" || client.scope == "notTestScope2") {
        println(true)
    } else {
        println(false)
    }


    /** 7. Содержит ли scope в себе какое-то значение. */
    println("7. Содержит ли scope в себе какое-то значение.")
    // Norm
    val l = client2?.scope ?: println(false)

    if (client?.scope == null) {
        println(true)
    } else {
        println(false)
    }
    if (client2?.scope == null) {
        println(true)
    } else {
        println(false)
    }


    /** 8. Содержит ли поле addresses только значения a,b и c. */
    println("8. Содержит ли поле addresses только значения a,b и c.")
    val a = "Moscow"
    val b = "NY"
    val c = "Boston"
    val d = "LA"
    var result8 = false
    var summOfEachResult = true
    var parameters = arrayListOf(a, b, c)
    parameters.forEach { parameter ->
        result8 = false
        client.addresses.forEach {
            if (it == parameter)
                result8 = result8 || true
            else
                result8 = result8 || false
        }
        summOfEachResult = summOfEachResult && result8
    }
    if (client.addresses.size != parameters.size)
        println(false)
    else
        println(summOfEachResult)

    client.addresses.add("NewAddress")

//  parameters.add(d)
    summOfEachResult = true
    parameters.forEach { parameter ->
        result8 = false
        client.addresses.forEach {
            if (it == parameter)
                result8 = result8 || true
            else
                result8 = result8 || false
        }
        summOfEachResult = summOfEachResult && result8
    }
    if (client.addresses.size != parameters.size)
        println(false)
    else
        println(summOfEachResult)


    /** 9. Не пуст ли список addresses.  */
    println("9. Не пуст ли список addresses.")
//    if (client.addresses == null)
//        println(false)
//    else
//        println(true)

//    if (client2.addresses == null)
//        println(false)
//    else
//        println(true)
    client?.addresses?.isEmpty() ?: println(true)
    client2?.addresses?.isEmpty() ?: println(true)


    /** 10. id в интервале [a,b]. and  */
    println("10. id в интервале [a,b]. and")
    val intA = 1
    val intB = 6
    if (client.id in intA..intB)
        println(true)
    else
        println(false)

    client.id = 7

    if (client.id in intA..intB)
        println(true)
    else
        println(false)


    /** 11. хотя бы одно поле из списка (a,b,c) отсутствует в addresses. */
    println("11. хотя бы одно поле из списка (a,b,c) отсутствует в addresses.")
    var result11 = false
    summOfEachResult = true
    parameters.forEach { parameter ->
        result11 = false
        client.addresses.forEach {
            if (it == parameter)
                result11 = result11 || true
            else
                result11 = result11 || false
        }
        summOfEachResult = summOfEachResult && result11
    }
    println(!summOfEachResult)
    //parameters.forEach { (println(it)) }
    //client.addresses.forEach { (println(it)) }

    parameters.add("newParameter")
    summOfEachResult = true
    parameters.forEach { parameter ->
        result11 = false
        client.addresses.forEach {
            if (it == parameter)
                result11 = result11 || true
            else
                result11 = result11 || false
        }
        summOfEachResult = summOfEachResult && result11
    }
    println(!summOfEachResult)
}
