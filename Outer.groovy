package ru.sbt.fedorov.groovy

import ru.sbt.fedorov.testClasses.AnotherClass
import ru.sbt.fedorov.testClasses.Client
import ru.sbt.fedorov.testClasses.FactorLight
import ru.sbt.fedorov.testClasses.Status

import java.sql.Timestamp

class Outer {

    static void main(String[] args) {
        Client client
        Client client2
        Client client3

        Status status
        AnotherClass anotherClass2
        AnotherClass anotherClass22
        AnotherClass anotherClass222
        AnotherClass anotherClass


        client = new Client()
        client2 = new Client()
        client3 = new Client()

        status = new Status()
        status.setCode(Long.parseLong("234"))
        // Timestamp testTimestamp = new Timestamp(date.getTime())
        // System.out.println(testTimestamp)
        anotherClass = new AnotherClass()
        anotherClass.setName("Max")
        status.setAnotherClass(anotherClass)

        /* Уставновка значений класса Client */
        client.setTime(Timestamp.valueOf("2019-04-15 21:43:51.042"))
        client.setStatus(status)
        client.setScope("testScope")

        /* Подготовка значений для иницилизации объектов */
        anotherClass2 = new AnotherClass()
        anotherClass22 = new AnotherClass()
        anotherClass222 = new AnotherClass()
        anotherClass2.setValue(5)
        anotherClass22.setValue(35)
        anotherClass222.setValue(115)
        Status status2 = new Status(0, "",null)
        Status status22 = new Status(0, "",null)
        Status status222 = new Status(0, "",null)
        status2.setAnotherClass(anotherClass2)
        status22.setAnotherClass(anotherClass22)
        status222.setAnotherClass(anotherClass222)
        status2.setDescription("testDescription")
        status22.setDescription("testDescription")
        status222.setDescription("testDescription")
        List<Status> statuses = new ArrayList<>()
        List<Status> statuses3 = null
        statuses.add(status)
        statuses.add(status2)
        statuses.add(status22)
        statuses.add(status222)
        FactorLight factorLight = FactorLight.GREEN
        client.setFactorLight(factorLight)
        client.id = 5

        /* Уставновка значений класса Client 2 и 3 */
        client2.setStatuses(statuses)
        Status secondStatus = new Status(0, "",null)
        secondStatus.setCode(Long.parseLong("0"))
        client2.setStatus(secondStatus)
        client2.setScope(null)
        client3.setStatuses(statuses3)

        List<String> listAddresses = new ArrayList<>()
        listAddresses.add("Moscow")
        // listAddresses.add("London")
        // listAddresses.add("Seul")
        listAddresses.add("NY")
        listAddresses.add("Boston")
        listAddresses.add("LA")
        listAddresses.add("LA")
        client.setAddresses(listAddresses)


        /** 1. Равно ли поле time какому-то конкретному значению. */
        println "1.Равно ли поле time какому-то конкретному значению."
        if (client.time.toString() == '2068-07-28 16:24:17.992') {
            println true
        } else {
            println false
        }
        if (client.time.toString() == "2019-04-15 21:43:51.042") {
            println true
        } else {
            println false
        }


        /** 2. Равно ли поле scope какому-то конкретному значению. */
        println "2.Равно ли поле scope какому-то конкретному значению."
        if (client.scope == "notTestScope") {
            println true
        } else {
            println false
        }
        if (client.scope == "testScope") {
            println true
        } else {
            println false
        }


        /**  3. Равно ли поле status/code какому то значению. */
        println "3.Равно ли поле status/code какому то значению."
        if (client.status.code == 32545) {
            println true
        } else {
            println false
        }
        if (client.status.code == 234) {
            println true
        } else {
            println false
        }


        /**  4. Равно ли поле status/code 0. */
        println "4.Равно ли поле status/code 0."
        if (client.status.code == 0) {
            println true
        } else {
            println false
        }
        if (client2.status.code == 0) {
            println true
        } else {
            println false
        }


        /** 5. Равно ли поле factorLight какому-то значению. */
        println "5.Равно ли поле factorLight какому-то значению."
        if (client.factorLight.toString() == factorLight.RED.toString()) {
            println true
        } else {
            println false
        }
        if (client.factorLight.toString() == factorLight.GREEN.toString()) {
            println true
        } else {
            println false
        }


        /** 6. Равно ли поле scope одному из значений a,b или с. */
        println "6.Равно ли поле scope одному из значений a,b или с."
        if (client.scope == "notTestScope" || client.scope == "notTestScope1" || client.scope == "notTestScope2") {
            println true
        } else {
            println false
        }
        if (client.scope == "notTestScope" || client.scope == "testScope" || client.scope == "notTestScope2") {
            println true
        } else {
            println false
        }


        /** 7. Содержит ли scope в себе какое-то значение. */
        println "7.Содержит ли scope в себе какое-то значение."
        if (client.scope == null) {
            println true
        } else {
            println false
        }
        if (client2.scope == null) {
            println true
        } else {
            println false
        }


        /** 8. Содержит ли поле addresses только значения a,b и c.  // a a b c = a b b c */
        println "8.Содержит ли поле addresses только значения a,b и c."
        String a = "Moscow"
        String b = "NY"
        String c = "Boston"
        String d = "LA"
        Boolean result = false
        Boolean summOfEachResult = true
        ArrayList<String> parameters = new ArrayList<>()
        parameters.add(a)
        parameters.add(b)
        parameters.add(c)
        parameters.each { parameter ->
            result = false
            client.addresses.each {
                if (it == parameter)
                    result = result | true
                else
                    result = result | false
            }
            summOfEachResult = summOfEachResult & result
        }
        if (client.addresses.size() != parameters.size())
            println false
        else
            println summOfEachResult
        summOfEachResult = true
        parameters.add(d)
        parameters.add("Boston")
        // parameters.add("Piter")
        parameters.each { parameter ->
            result = false
            client.addresses.each {
                if (it == parameter)
                    result = result | true
                else
                    result = result | false
            }
            summOfEachResult = summOfEachResult & result
        }
        if (client.addresses.size() != parameters.size())
            println false
        else
            println summOfEachResult


        /** 9. Не пуст ли список addresses. */
        println "9.Не пуст ли список addresses."
        if (client.addresses == null)
            println false
        else
            println true

        client2.addresses = null
        if (client2.addresses == null)
            println false
        else
            println true


        /** 10. id в интервале [a,b]. */
        println "10.id в интервале [a,b]."
        long intA = 1
        long intB = 6
        if (client.id >= intA & client.id <= intB)
            println true
        else
            println false

        client.id = 7
        if (client.id >= intA & client.id <= intB)
            println true
        else
            println false


        /** 11. хотя бы одно поле из списка (a,b,c) отсутствует в addresses. */
        println "11.хотя бы одно поле из списка (a,b,c) отсутствует в addresses."
        summOfEachResult = true
        parameters.each { parameter ->
            result = false
            client.addresses.each {
                if (it == parameter)
                    result = result | true
                else
                    result = result | false
            }
            summOfEachResult = summOfEachResult & result
        }
        println summOfEachResult

        parameters.add("new")
        summOfEachResult = true
        parameters.each { parameter ->
            result = false
            client.addresses.each {
                if (it == parameter)
                    result = result | true
                else
                    result = result | false
            }
            summOfEachResult = summOfEachResult & result
        }
        println summOfEachResult
    }
}

