import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import testingClasses.Client;
import testingClasses.FactorLight;
import testingClasses.Status;

import javax.el.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TestJUEL {
    public static void main(String[] args) {
        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();
//      Заполнение
        Client client = new Client();
        Client client2 = new Client();
        client.setId(3);
        client.setFactorLight(FactorLight.GREEN);
        client2.setFactorLight(FactorLight.RED);
        client.setScope("testScope");
//        client2.setScope("");
        client.setTime(new Timestamp(33));
        List<String> addresses = new ArrayList<String>();
        addresses.add("a");
//        addresses.add("b");
        addresses.add("b");
        addresses.add("c");
        client.setAddresses(addresses);

        Status status = new Status();
        Status status2 = new Status();
        status.setCode(123L);
        status2.setCode(0L);

        client.setStatus(status);
        client2.setStatus(status2);

        Object time = new Timestamp(33);
        Object time2 = new Timestamp(34);
        Object value2 = 5;

        context.setVariable("client", factory.createValueExpression(client, client.getClass()));
        context.setVariable("client2", factory.createValueExpression(client2, client.getClass()));
        context.setVariable("time", factory.createValueExpression(time, Object.class));
        context.setVariable("time2", factory.createValueExpression(time2, Object.class));
        context.setVariable("factorLight", factory.createValueExpression(FactorLight.GREEN, Object.class));
        context.setVariable("testValue1", factory.createValueExpression("b", Object.class));


        /* 1. Равно ли поле time какому-то конкретному значению. */
        System.out.println("1. Равно ли поле time какому-то конкретному значению.");

        ValueExpression timeEqualsValue = factory.createValueExpression(context, "#{client.time == time}", boolean.class);
        System.out.println(timeEqualsValue.getValue(context));

        timeEqualsValue = factory.createValueExpression(context, "#{client.time == time2}", boolean.class);
        System.out.println(timeEqualsValue.getValue(context));


        /* 2. Равно ли поле scope какому-то конкретному значению. */
        System.out.println("2. Равно ли поле scope какому-то конкретному значению.");

        ValueExpression scopeEqualsValue = factory.createValueExpression(context, "#{client.scope == 'testScope'}", boolean.class);
        System.out.println(scopeEqualsValue.getValue(context));

        scopeEqualsValue = factory.createValueExpression(context, "#{client.time == 'badTestScope'}", boolean.class);
        System.out.println(scopeEqualsValue.getValue(context));


        /* 3. Равно ли поле status/code какому то значению. */
        System.out.println("3. Равно ли поле status/code какому то значению.");

        ValueExpression statusCodeEqualsValue = factory.createValueExpression(context, "#{client.status.code == 123}", boolean.class);
        System.out.println(statusCodeEqualsValue.getValue(context));

        statusCodeEqualsValue = factory.createValueExpression(context, "#{client.status.code == 1234}", boolean.class);
        System.out.println(statusCodeEqualsValue.getValue(context));


        /* 4. Равно ли поле status/code 0. */
        System.out.println("4. Равно ли поле status/code 0.");

        ValueExpression statusCodeEquals0 = factory.createValueExpression(context, "#{client2.status.code == 0}", boolean.class);
        System.out.println(statusCodeEquals0.getValue(context));

        statusCodeEquals0 = factory.createValueExpression(context, "#{client.status.code == 0}", boolean.class);
        System.out.println(statusCodeEquals0.getValue(context));


        /* 5. Равно ли поле factorLight какому-то значению. */
        System.out.println("5. Равно ли поле factorLight какому-то значению.");

        ValueExpression factorLightEqualsValue = factory.createValueExpression(context, "#{client.factorLight == factorLight}", boolean.class);
        System.out.println(factorLightEqualsValue.getValue(context));

        factorLightEqualsValue = factory.createValueExpression(context, "#{client2.factorLight == factorLight}", boolean.class);
        System.out.println(factorLightEqualsValue.getValue(context));


        /* 6. Равно ли поле scope одному из значений a, b или с. or */
        System.out.println("6. Равно ли поле scope одному из значений a,b или с.");

        ValueExpression scopeEqualsABC = factory.createValueExpression(context, "#{client.scope == 'badTestScope' or client.scope == 'testScope' or client.scope == 'badTestScope'}", boolean.class);
        System.out.println(scopeEqualsABC.getValue(context));

        scopeEqualsABC = factory.createValueExpression(context, "#{client.scope == 'badTestScope' or client.scope == 'badTestScope' or client.scope == 'badTestScope'}", boolean.class);
        System.out.println(scopeEqualsABC.getValue(context));


        /* 7. Содержит ли scope в себе какое-то значение. */
        System.out.println("7. Содержит ли scope в себе какое-то значение.");

        ValueExpression scopeIsEmpty = factory.createValueExpression(context, "#{client2.scope == null}", boolean.class);
        System.out.println(scopeIsEmpty.getValue(context));

        scopeIsEmpty = factory.createValueExpression(context, "#{client.scope == null}", boolean.class);
        System.out.println(scopeIsEmpty.getValue(context));


        /* 8. Содержит ли коллекция addresses только значения a, b и c. */
        System.out.println("8. Содержит ли поле addresses только значения a,b и c.");

        ValueExpression addressesIncludesABC = factory.createValueExpression(context, "#{client.addresses.contains(\"a\") and client.addresses.contains(testValue1) and client.addresses.contains('c') and client.addresses.size() == 3}", boolean.class);
        System.out.println(addressesIncludesABC.getValue(context));

        addressesIncludesABC = factory.createValueExpression(context, "#{client2.addresses.contains('a') and client2.addresses.contains('b') and client2.addresses.contains('c') and client2.addresses.size() == 3}", boolean.class);
        System.out.println(addressesIncludesABC.getValue(context));


        /* 9. Не пуст ли список addresses.  */
        System.out.println("9. Не пуст ли список addresses.");

        ValueExpression addressesEqualsNull = factory.createValueExpression(context, "#{client.addresses.isEmpty()}", boolean.class);
//        ValueExpression addressesEqualsNull = factory.createValueExpression(context, "#{client.addresses.size() == 0}", boolean.class);
        System.out.println(addressesEqualsNull.getValue(context));

        addressesEqualsNull = factory.createValueExpression(context, "#{client2.addresses.isEmpty() == null}", boolean.class);
        System.out.println(addressesEqualsNull.getValue(context));


        /* 10. id в интервале [a, b]. and  */
        System.out.println("10. id в интервале [a,b]. and");

        ValueExpression idBetweenValues = factory.createValueExpression(context, "#{client.id <= 3 and client.id > 1}", boolean.class);
        System.out.println(idBetweenValues.getValue(context));

        idBetweenValues = factory.createValueExpression(context, "#{client.id > 5 and client.id < 0}", boolean.class);
        System.out.println(idBetweenValues.getValue(context));


        /* 11. хотя бы одно поле из списка (a,b,c) отсутствует в addresses. */
        System.out.println("11. хотя бы одно поле из списка (a,b,c) отсутствует в addresses.");

        ValueExpression addressesIncludesNotAllABC = factory.createValueExpression(context, "#!{client.addresses.contains(\"a\") and client.addresses.contains(testValue1) and client.addresses.contains('c') and client.addresses.size() == 3}", boolean.class);
        System.out.println(addressesIncludesNotAllABC.getValue(context));

        addressesIncludesNotAllABC = factory.createValueExpression(context, "#{!(client.addresses.contains('a')) or !(client.addresses.contains('d')) or !(client.addresses.contains('c'))}", boolean.class);
        System.out.println(addressesIncludesNotAllABC.getValue(context));

    }

}
