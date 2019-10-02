package ru.sbt.fedorov.jvm;

import ru.sbt.fedorov.testClasses.AnotherClass;
import ru.sbt.fedorov.testClasses.Client;
import ru.sbt.fedorov.testClasses.FactorLight;
import ru.sbt.fedorov.testClasses.Status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JavaExpressions {
    public static void main(String[] args) {
        Client client;
        Client client2;
        Client client3;
        Status status;
        AnotherClass anotherClass2;
        AnotherClass anotherClass22;
        AnotherClass anotherClass222;
        AnotherClass anotherClass;

        client = new Client();
        client2 = new Client();
        client3 = new Client();

        status = new Status();
        status.setCode(Long.parseLong("234"));

        anotherClass = new AnotherClass();
        anotherClass.setName("Max");
        status.setAnotherClass(anotherClass);

        /* Уставновка значений класса Client */
        client.setTime(Timestamp.valueOf("2019-04-15 21:43:51.042"));
        client.setStatus(status);
        client.setScope("testScope");

        /* Подготовка значений для иницилизации объектов */
        anotherClass2 = new AnotherClass();
        anotherClass22 = new AnotherClass();
        anotherClass222 = new AnotherClass();
        anotherClass2.setValue(5);
        anotherClass22.setValue(35);
        anotherClass222.setValue(115);
        Status status2 = new Status();
        Status status22 = new Status();
        Status status222 = new Status();
        status2.setAnotherClass(anotherClass2);
        status22.setAnotherClass(anotherClass22);
        status222.setAnotherClass(anotherClass222);
        status2.setDescription("testDescription");
        status22.setDescription("testDescription");
        status222.setDescription("testDescription");
        List<Status> statuses = new ArrayList<>();
        List<Status> statuses3 = null;
        statuses.add(status);
        statuses.add(status2);
        statuses.add(status22);
        statuses.add(status222);
        FactorLight factorLight = FactorLight.GREEN;
        client.setFactorLight(factorLight);
        client.setId(5);

        /* Уставновка значений класса Client 2 и 3 */
        client2.setStatuses(statuses);

        Status secondStatus = new Status();
        secondStatus.setCode(Long.parseLong("0"));
        client2.setStatus(secondStatus);
        client2.setScope(null);
        client3.setStatuses(statuses3);

        List<String> listAddresses = new ArrayList<>();
        listAddresses.add("Moscow");
        listAddresses.add("NY");
        listAddresses.add("Boston");
        listAddresses.add("LA");
        listAddresses.add("LA");

        client.setAddresses(listAddresses);

//        1. Равно ли поле time какому-то конкретному значению.
        System.out.println("1.Равно ли поле time какому-то конкретному значению.");
        if (client.getTime().toString().equals("2068-07-28 16:24:17.992")) {
            System.out.println(true);
        } else {
            System.out.println(false);

        }
        if (client.getTime().toString().equals("2019-04-15 21:43:51.042")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

//        2. Равно ли поле scope какому-то конкретному значению.
        System.out.println("2.Равно ли поле scope какому-то конкретному значению.");
        if (client.getScope().equals("notTestScope")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (client.getScope().equals("testScope")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

//        3. Равно ли поле status/code какому то значению.
        System.out.println("3.Равно ли поле status/code какому то значению.");
        if (client.getStatus().getCode() == 32545) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (client.getStatus().getCode() == 234) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

//        4. Равно ли поле status/code 0.
        System.out.println("4.Равно ли поле status/code 0.");
        if (client.getStatus().getCode() == 0) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (client2.getStatus().getCode() == 0) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

//        5. Равно ли поле factorLight какому-то значению.
        System.out.println("5.Равно ли поле factorLight какому-то значению.");
        if (client.getFactorLight().toString().equals(FactorLight.RED.toString())) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (client.getFactorLight().toString().equals(FactorLight.GREEN.toString())) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

//        6. Равно ли поле scope одному из значений a,b или с.
        System.out.println("6.Равно ли поле scope одному из значений a,b или с.");
        if (client.getScope().equals("notTestScope") || client.getScope().equals("notTestScope1") || client.getScope().equals("notTestScope2")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (client.getScope().equals("notTestScope") || client.getScope().equals("testScope") || client.getScope().equals("notTestScope2")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

//        7. Содержит ли scope в себе какое-то значение.
        System.out.println("7.Содержит ли scope в себе какое-то значение.");
        if (client.getScope() == null) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        if (client2.getScope() == null) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

//      8. Содержит ли поле addresses только значения a,b и c.  // a a b c = a b b c
        System.out.println("8.Содержит ли поле addresses только значения a,b и c.");
        String a = "Moscow";
        String b = "NY";
        String c = "Boston";
        String d = "LA";
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        AtomicReference<Boolean> summOfEachResult = new AtomicReference<>(true);
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add(a);
        parameters.add(b);
        parameters.add(c);
        parameters.forEach(parameter -> {
            result.set(false);
            client.getAddresses().forEach(it -> {
                if (it.equals(parameter))
                    result.set(result.get() | true);
                else
                    result.set(result.get() | false);
            });
            summOfEachResult.set(summOfEachResult.get() & result.get());
        });
        if (client.getAddresses().size() != parameters.size())
            System.out.println(false);
        else
            System.out.println(summOfEachResult);
        summOfEachResult.set(true);
        parameters.add(d);
        parameters.add("Boston");
//        parameters.add("Piter")
        parameters.forEach(parameter -> {

            result.set(false);
            client.getAddresses().forEach(it -> {
                if (it.equals(parameter))
                    result.set(result.get() | true);
                else
                    result.set(result.get() | false);
            });
            summOfEachResult.set(summOfEachResult.get() & result.get());
        });
        if (client.getAddresses().size() != parameters.size())
            System.out.println(false);
        else
            System.out.println(summOfEachResult);

//        9. Не пуст ли список addresses.
        System.out.println("9.Не пуст ли список addresses.");
        if (client.getAddresses() == null)
            System.out.println(false);
        else
            System.out.println(true);

        client2.setAddresses(null);
        if (client2.getAddresses() == null)
            System.out.println(false);
        else
            System.out.println(true);

//        10. id в интервале [a,b].
        System.out.println("10.id в интервале [a,b].");
        long intA = 1;
        long intB = 6;
        if (client.getId() >= intA & client.getId() <= intB)
            System.out.println(true);
        else
            System.out.println(false);

        client.setId(7);
        if (client.getId() >= intA & client.getId() <= intB)
            System.out.println(true);
        else
            System.out.println(false);

//        11. хотя бы одно поле из списка (a,b,c) отсутствует в addresses.
        System.out.println("11.хотя бы одно поле из списка (a,b,c) отсутствует в addresses.");
        summOfEachResult.set(true);
        parameters.forEach(parameter -> {
            result.set(false);
            client.getAddresses().forEach(it -> {
                if (it.equals(parameter))
                    result.set(result.get() | true);
                else
                    result.set(result.get() | false);
            });
            summOfEachResult.set(summOfEachResult.get() & result.get());
        });
        System.out.println(summOfEachResult);

        parameters.add("new");
        summOfEachResult.set(true);
        parameters.forEach(parameter -> {
            result.set(false);
            client.getAddresses().forEach(it -> {
                if (it.equals(parameter))
                    result.set(result.get() | true);
                else
                    result.set(result.get() | false);
            });
            summOfEachResult.set(summOfEachResult.get() & result.get());
        });
        System.out.println(summOfEachResult);
    }
}
