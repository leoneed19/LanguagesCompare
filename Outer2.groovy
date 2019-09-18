package ru.sbt.fedorov.groovy

import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.AcctTypeType
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.AdditionalStatusGFPLType
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.GetFullProductListRs
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.StatusDetailByProductType
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.TimeMetricListType
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.TimeMetricType
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.TimeRangeType

class Outer2 {
    static void main(String[] args) {
        """1) поле status/statusCode == 0.
        2) список status/additionalStatuses - пуст или == null.
        3) все поля statusCode в коллекции status/additionalStatus == 0.
        4) Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts,
        и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts.
        5) в коллекции acctTypes нет значений DEPOSIT и IMA.
        6) (посмотри что можно сделать когда нужно проверить >1 условия, например) rqUid == 'qwe123' и список status/additionalStatus - пуст или == null.
        7) все поля status/statusDesc начинаются с "success".
        8) в коллекции acctTypes - один элемент и он равен какому то конкретному значению.
        9) проверить что operUID и syatemId не равны null."""

        GetFullProductListRs getFullProductListRs = new GetFullProductListRs()
//        GetFullProductListRs getFullProductListRs2 = new GetFullProductListRs()
        GetFullProductListRs.Status status = new GetFullProductListRs.Status()
        status.setStatusCode(0)

        getFullProductListRs.setStatus(status)
//        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList = new ArrayList<>()
//        AdditionalStatusGFPLType additionalStatusGFPLType = new AdditionalStatusGFPLType(0, null, null, null)
//        AdditionalStatusGFPLType additionalStatusGFPLType2 = new AdditionalStatusGFPLType(2, null, null, null)
//        AdditionalStatusGFPLType additionalStatusGFPLType3 = new AdditionalStatusGFPLType(3, null, null, null)
//        AdditionalStatusGFPLType additionalStatusGFPLType4 = new AdditionalStatusGFPLType(4, null, null, null)
//        AdditionalStatusGFPLType additionalStatusGFPLType5 = new AdditionalStatusGFPLType(5, null, null, null)
        // additionalStatusGFPLTypeArrayList.add(additionalStatusGFPLType)
        // status.withAdditionalStatuses(additionalStatusGFPLTypeArrayList)
        // GetFullProductListRs.additionalStatuses additionalStatus = new GetFullProductListRs.Status()
        // status.additionalStatuses = additionalStatusGFPLTypeArrayList

        AcctTypeType acctTypeType = AcctTypeType.IMA
        getFullProductListRs.operUID = 11111
        getFullProductListRs.rqUID = "qwe123"
        getFullProductListRs.acctTypes.add(acctTypeType)


        /** 1. поле status/statusCode == 0 */
        println "1. поле status/statusCode == 0"
        if (getFullProductListRs.status.statusCode == 0)
            println(true)
        else println false

        getFullProductListRs.status.statusCode = 1

        if (getFullProductListRs.status.statusCode == 0)
            println(true)
        else println false


        /** 2. список status/additionalStatuses - пуст или == null */
        // Заполнение
        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList2 = null
        GetFullProductListRs.Status status2 = new GetFullProductListRs.Status()
        status2.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2)
        getFullProductListRs.setStatus(status2)

        println "2. список status/additionalStatuses - пуст или == null"
        if (getFullProductListRs.getStatus().getAdditionalStatuses().size() == 0)
            println true
        else
            println false


        /** 3. все поля statusCode в коллекции status/additionalStatus == 0 */
        println "3. все поля statusCode в коллекции status/additionalStatus == 0"
        Boolean result3 = true
        getFullProductListRs?.status?.additionalStatuses?.each {
            if (it.statusCode == 0)
                result3 = result3 & true
            else
                result3 = result3 & false
        }
        println(result3)


        /** 4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts,
         и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts. */
        GetFullProductListRs.StatusDetailByProductList statusDetailByProductList = new GetFullProductListRs.StatusDetailByProductList()
        StatusDetailByProductType statusDetailByProductType = new StatusDetailByProductType()
        TimeMetricListType timeMetricListType = new TimeMetricListType()
        TimeMetricType timeMetricType = new TimeMetricType()
        TimeRangeType timeOutList = new TimeRangeType()
        MyClassImplCalendar myClassImplCalendar = new MyClassImplCalendar()
        Date date = new Date()
        date.time = 4234L
        myClassImplCalendar.setTime(date)
        myClassImplCalendar.setFirstDayOfWeek(4)
        myClassImplCalendar.firstDayOfWeek = 15
        timeOutList.timeMin = myClassImplCalendar as Calendar
        timeMetricType.withTimeOutList(timeOutList)
        timeMetricListType.metrics.add(timeMetricType)
        statusDetailByProductType.setMetricList(timeMetricListType)
        statusDetailByProductList.statusDetailByProducts.add(statusDetailByProductType)
        getFullProductListRs.setStatusDetailByProductList(statusDetailByProductList)

        println "4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts, и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts."
        Integer currentValue = 16
        Boolean result4 = true
        try {
            println "firstDayOfWeek " + getFullProductListRs.getStatusDetailByProductList().statusDetailByProducts.get(0).metricList.metrics.get(0).timeOutList.timeMin.firstDayOfWeek
            getFullProductListRs.getStatusDetailByProductList().statusDetailByProducts.each {}.metricList.metrics.each {
            }.timeOutList.timeMin.firstDayOfWeek.each { fieldFirstDayOfWeek ->
                println "ArrayList of firstDayOfWeek" + fieldFirstDayOfWeek.toString()
                println "Class of firstDayOfWeek" + fieldFirstDayOfWeek.class
                fieldFirstDayOfWeek.each {
                    if (currentValue > (Integer) it)
                        result4 = result4 & true
                    else
                        result4 = result4 & false
                }
            }
        }
        catch (Exception e) {
            println(e)
        }
        println result4


        /** 5. в коллекции acctTypes нет значений DEPOSIT и IMA. */
        println "5. в коллекции acctTypes нет значений DEPOSIT и IMA."
        Boolean result5 = true
        getFullProductListRs.acctTypes.each {
            if (it.toString() == "DEPOSIT" || it.toString() == "IMA")
                result5 = result5 & false
            else
                result5 = result5 & true
        }
        println result5


        /** 6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null. */
        //        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList2 = null
        //        getFullProductListRs.status.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2)
        println "6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null."
        if (getFullProductListRs.rqUID == "qwe123" & getFullProductListRs.getStatus().getAdditionalStatuses().size() == 0)
            println true
        else
            println false
        //println getFullProductListRs.getStatus().getAdditionalStatuses().isEmpty()


        /** 7. все поля status/statusDesc начинаются с "success". */
        println "7. все поля status/statusDesc начинаются с success."
        Boolean result = false
        getFullProductListRs.status.getStatusDesc().each { statusDesc ->
            if (statusDesc.startsWith("success"))
                result = result & true
            else
                result = result & false
        }
        println result


        /** 8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению. */
        println "8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению."
        if (getFullProductListRs.acctTypes.size() == 1 && getFullProductListRs.acctTypes.get(0) == AcctTypeType.IMA)
            println true
        else
            println false


        /** 9. проверить что operUID и syatemId не равны null. */
        println "9. проверить что operUID и systemId не равны null."

        if (getFullProductListRs.operUID != null && getFullProductListRs.systemId != null)
            println true
        else
            println false
    }
}
