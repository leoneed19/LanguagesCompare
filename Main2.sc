import java.sql.Timestamp

import j.srvgetfullproductlist.{AcctTypeType, AdditionalStatusGFPLType, GetFullProductListRs}
import testClasses.{Client, FactorLight, Status}

object main2 {
  def main(args: Array[String]): Unit = {
    val client = new Client()
    val statuss = new Status()
    val factorLight = FactorLight.GREEN
    statuss.setCode(234L)

    val timestamp = Timestamp.valueOf("1999-10-10 11:22:11.0")
    client.setTime(timestamp)
    client.setScope("testScope")
    client.setStatus(statuss)
    client.setFactorLight(factorLight)

    val getFullProductListRs = new GetFullProductListRs()
    var getFullProductListRs2 = new GetFullProductListRs()
    val status = new GetFullProductListRs.Status()
    status.setStatusCode(0)

    getFullProductListRs.setStatus(status)
    var additionalStatusGFPLTypeArrayList: Array[AdditionalStatusGFPLType] = Array()
    var additionalStatusGFPLType = new AdditionalStatusGFPLType(0, null, null, null)
    var additionalStatusGFPLType2 = new AdditionalStatusGFPLType(2, null, null, null)
    var additionalStatusGFPLType3 = new AdditionalStatusGFPLType(3, null, null, null)
    var additionalStatusGFPLType4 = new AdditionalStatusGFPLType(4, null, null, null)
    var additionalStatusGFPLType5 = new AdditionalStatusGFPLType(5, null, null, null)

    val acctTypeType = AcctTypeType.IMA
    getFullProductListRs.setOperUID("11111")
    getFullProductListRs.setRqUID("qwe123")
    getFullProductListRs.getAcctTypes.add(acctTypeType)

    // 1. поле status/statusCode == 0
    println("1. поле status/statusCode == 0")
    if (getFullProductListRs.getStatus.getStatusCode == 0)
      println(true)
    else println(false)

    getFullProductListRs.getStatus.setStatusCode(1)

    if (getFullProductListRs.getStatus.getStatusCode == 0)
      println(true)
    else
      println(false)

    val additionalStatusGFPLTypeArrayList2: Array[AdditionalStatusGFPLType] = Array()
    //    List < AdditionalStatusGFPLType > additionalStatusGFPLTypeArrayList2 = null
    val status2 = new GetFullProductListRs.Status()
    //    status2.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2)

    getFullProductListRs.setStatus(status2)

    // 2. список status/additionalStatuses - пуст или == null
    println("2. список status/additionalStatuses - пуст или == null")
    if (getFullProductListRs.getStatus.getAdditionalStatuses.size() == 0)
      println(true)
    else
      println(false)

    // 3. все поля statusCode в коллекции status/additionalStatus == 0
    println("3. все поля statusCode в коллекции status/additionalStatus == 0")
    // 4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts,
    //        и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts.
    println("Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts, и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts.")
    val currentValue = 10
    var result4 = false

    getFullProductListRs.getStatusDetailByProductList.getStatusDetailByProducts.forEach(it => it.getMetricList.getMetrics.forEach(it => {
      if (it.getTimeOutList.getTimeMin.getFirstDayOfWeek.toString == currentValue.toString)
        result4 = result4 & true
      else
        result4 = result4 & false
    }))
    println(result4)

    // 5. в коллекции acctTypes нет значений DEPOSIT и IMA.
    println("5. в коллекции acctTypes нет значений DEPOSIT и IMA.")
    var result5 = true
    getFullProductListRs.getAcctTypes.forEach(it => if (it.toString == "DEPOSIT" || it.toString == "IMA")
      result5 = result5 & false
    else
      result5 = result5 & true)
    println(result5)

    // 6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null.
    //        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList2 = null
    //        getFullProductListRs.status.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2)
    println("6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null.")
    if (getFullProductListRs.getRqUID == "qwe123" & getFullProductListRs.getStatus.getAdditionalStatuses.size() == 0)
      println(true)
    else
      println(false)

    //println getFullProductListRs.getStatus().getAdditionalStatuses().isEmpty()


    // 7. все поля status/statusDesc начинаются с "success".
    println("7. все поля status/statusDesc начинаются с success.")
    var result = false
    getFullProductListRs.getStatus.getStatusDesc.foreach(it => if (it.toString.startsWith("success"))
      result = result & true
    else
      result = result & false)
    println(result)

    // 8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению.
    println("8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению.")
    if (getFullProductListRs.getAcctTypes.size() == 1 && getFullProductListRs.getAcctTypes.get(0) == AcctTypeType.IMA)
      println(true)
    else
      println(false)

    // 9. проверить что operUID и syatemId не равны null.
    println("9. проверить что operUID и systemId не равны null.")

    if (getFullProductListRs.getOperUID != null && getFullProductListRs.getSystemId != null)
      println(true)
    else
      println(false)
  }
}




