import java.sql.Timestamp

import testClasses.{Client, FactorLight, Status}

object main {
  def main(args: Array[String]): Unit = {
    val client = new Client()
    val status = new Status()
    val factorLight = FactorLight.GREEN
    status.setCode(234L)

    val timestamp = Timestamp.valueOf("1999-10-10 11:22:11.0")
    client.setTime(timestamp)
    client.setScope("testScope")
    client.setStatus(status)
    client.setFactorLight(factorLight)

    println("1.Равно ли поле time какому-то конкретному значению.")
    if (client.getTime.toString.equals("2068-07-28 16:24:17.992")) {
      println(true)
    } else {
      println(false)
    }
    if (client.getTime.toString.equals("1999-10-10 11:22:11.0")) {
      println(true)
    } else {
      println(false)
    }

    //        2. Равно ли поле scope какому-то конкретному значению.
    println("2.Равно ли поле scope какому-то конкретному значению.")
    if (client.getScope == "notTestScope") {
      println(true)
    } else {
      println(false)
    }
    if (client.getScope == "testScope") {
      println(true)
    } else {
      println(false)
    }

    //        3. Равно ли поле status/code какому то значению.
    println("3.Равно ли поле status/code какому-то значению.")
    if (client.getStatus.getCode == 32545) {
      println(true)
    } else {
      println(false)
    }
    if (client.getStatus.getCode == 234L) {
      println(true)
    } else {
      println(false)
    }


    //        4. Равно ли поле status/code 0.
    println("3.Равно ли поле status/code 0.")
    if (client.getStatus.getCode == 0) {
      println(true)
    } else {
      println(false)
    }

    status.setCode(0L)
    client.setStatus(status)
    if (client.getStatus.getCode == 0L) {
      println(true)
    } else {
      println(false)
    }


    //        5. Равно ли поле factorLight какому-то значению.
    println("5.Равно ли поле factorLight какому-то значению.")
    if (client.getFactorLight.toString == FactorLight.RED.toString) {
      println(true)
    } else {
      println(false)
    }
    if (client.getFactorLight.toString == FactorLight.GREEN.toString) {
      println(true)
    } else {
      println(false)
    }


    //        6. Равно ли поле scope одному из значений a,b или с. or
    println("6.Равно ли поле scope одному из значений a,b или с.")
    if (client.getScope == "notTestScope" || client.getScope == "notTestScope1" || client.getScope == "notTestScope2") {
      println(true)
    } else {
      println(false)
    }
    if (client.getScope == "notTestScope" || client.getScope == "testScope" || client.getScope == "notTestScope2") {
      println(true)
    } else {
      println(false)
    }


    //        7. Содержит ли scope в себе какое-то значение.
    println("7.Содержит ли scope в себе какое-то значение.")
    if (client.getScope == null) {
      println(true)
    } else {
      println(false)
    }
    if (client.getScope == null) {
      println(true)
    } else {
      println(false)
    }


    //        8. Содержит ли поле addresses только значения a,b и c. 
    println("8.Содержит ли поле addresses только значения a,b и c.")
    val a = "Moscow"
    val b = "NY"
    val c = "Boston"
    val d = "LA"
    var result = false
    var summOfEachResult = true
    val parameters: Array[String] = Array(a, b, c)

    parameters.foreach {
      parameter =>
        result = false
        client.getAddresses.forEach { it =>
          if (it == parameter)
            result = result | true
          else
            result = result | false
        }
        summOfEachResult = summOfEachResult & result
    }
    if (client.getAddresses.size() != parameters.length)
      println(false)
    else
      println(summOfEachResult)
    summOfEachResult = true
    parameters :+ d
    parameters :+ "Boston"
    //        parameters.add("Piter")
    parameters.foreach {
      parameter =>
        result = false
        client.getAddresses.forEach { it =>
          if (it == parameter)
            result = result | true
          else
            result = result | false
        }
        summOfEachResult = summOfEachResult & result
    }
    if (client.getAddresses.size() != parameters.length)
      println(false)
    else
      println(summOfEachResult)


    //        9. Не пуст ли список addresses.
    println("9.Не пуст ли список addresses.")
    if (client.getAddresses == null)
      println(false)
    else
      println(true)

    //client.getAddresses = null
    if (client.getAddresses == null)
      println(false)
    else
      println(true)


    //       10. id в интервале [a,b]. and
    println("10.id в интервале [a,b].")
    val intA = 1
    val intB = 6
    if (client.getId >= intA & client.getId <= intB)
      println(true)
    else
      println(false)

    client.setId(7)
    if (client.getId >= intA & client.getId <= intB)
      println(true)
    else
      println(false)

    //       11. хотя бы одно поле из списка (a,b,c) отсутствует в addresses.
    println("11.хотя бы одно поле из списка (a,b,c) отсутствует в addresses.")
    summOfEachResult = true
    parameters.foreach { parameter =>
      result = false
      client.getAddresses.forEach { it =>
        if (it == parameter)
          result = result | true
        else
          result = result | false
      }
      summOfEachResult = summOfEachResult & result
    }
    println(summOfEachResult)

    parameters :+ "new"
    summOfEachResult = true
    parameters.foreach { parameter =>
      result = false
      client.getAddresses.forEach { it =>
        if (it == parameter)
          result = result | true
        else
          result = result | false
      }
      summOfEachResult = summOfEachResult & result
    }
    println(summOfEachResult)

  }

}


