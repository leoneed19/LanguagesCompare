import com.sun.javafx.font.Metrics;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import testingClasses2.*;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TestJUEL2 {
    public static void main(String[] args) {
        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();
        GetFullProductListRs getFullProductListRs = new GetFullProductListRs();
        getFullProductListRs.setRqUID("eee");

        AdditionalStatusGFPLType param = new AdditionalStatusGFPLType();
        param.setStatusCode(0L);
        AcctTypeType param2 = AcctTypeType.IMA;

        Predicate<AdditionalStatusGFPLType> p3 = e -> e.getStatusCode() == 1;
        Predicate<Calendar> p4 = e -> e.getFirstDayOfWeek() < 7;


        context.setVariable("g", factory.createValueExpression(getFullProductListRs, getFullProductListRs.getClass()));
        context.setVariable("param", factory.createValueExpression(param, AdditionalStatusGFPLType.class));
        context.setVariable("param2", factory.createValueExpression(param2, AcctTypeType.class));
        context.setVariable("p3", factory.createValueExpression(p3, Predicate.class));
        context.setVariable("p4", factory.createValueExpression(p4, Predicate.class));

        GetFullProductListRs.Status status = new GetFullProductListRs.Status();
        status.setStatusCode(0L);
        getFullProductListRs.setStatus(status);


        /* 1. поле status/statusCode == 0 */
        System.out.println("1. поле status/statusCode == 0");
        ValueExpression statusStatusCode = factory.createValueExpression(context, "#{g.status.statusCode == 0}", boolean.class);
        System.out.println(statusStatusCode.getValue(context));

        GetFullProductListRs.Status status2 = new GetFullProductListRs.Status();
        status2.setStatusCode(1L);
        getFullProductListRs.setStatus(status2);

        statusStatusCode = factory.createValueExpression(context, "#{g.status.statusCode == 0}", boolean.class);
        System.out.println(statusStatusCode.getValue(context));


        /* 2. список status/additionalStatuses - пуст или == null */
        System.out.println("2. список status/additionalStatuses - пуст или == null");
        // Заполнение

        GetFullProductListRs.Status status3 = new GetFullProductListRs.Status();
        AdditionalStatusGFPLType additionalStatusGFPLType1 = new AdditionalStatusGFPLType();
        AdditionalStatusGFPLType additionalStatusGFPLType2 = new AdditionalStatusGFPLType();

        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeList = new ArrayList<AdditionalStatusGFPLType>();
        additionalStatusGFPLTypeList.add(additionalStatusGFPLType1);
        additionalStatusGFPLTypeList.add(additionalStatusGFPLType2);
        additionalStatusGFPLType1.setStatusCode(1);
        additionalStatusGFPLType2.setStatusCode(1);

        ValueExpression statusAdditionalStatuses = factory.createValueExpression(context, "#{g.status.additionalStatuses.isEmpty()}", boolean.class);
        System.out.println(statusAdditionalStatuses.getValue(context));

        status3.withAdditionalStatuses(additionalStatusGFPLTypeList);
        getFullProductListRs.setStatus(status3);

        statusAdditionalStatuses = factory.createValueExpression(context, "#{g.status.additionalStatuses.isEmpty()}", boolean.class);
        System.out.println(statusAdditionalStatuses.getValue(context));

//        status3.withAdditionalStatuses(additionalStatusGFPLTypeList);


        /* 3. все поля statusCode в коллекции status/additionalStatus == 0 */
        System.out.println("3. все поля statusCode в коллекции status/additionalStatus == 0");
        //TODO не могу применить ко всем элементам в коллекции, с Java 8 могу с помощью stream().allMatch()
        /*Не получается применить условие ко всем объектам так как не работает stream().filter()*/
        Predicate<AdditionalStatusGFPLType> p2 = e -> e.getStatusCode() == 0;
        ValueExpression statusAdditionalStatusStatusCode = factory.createValueExpression(context, "#{g.status.additionalStatuses.stream().allMatch(p3)}", boolean.class);
//        ValueExpression statusAdditionalStatusStatusCode = factory.createValueExpression(context, "#{g.status.additionalStatuses.stream()}", Object.class);
        System.out.println(statusAdditionalStatusStatusCode.getValue(context));

//        getFullProductListRs.getStatus().getAdditionalStatuses().forEach((k,v) -> System.out.println("d")) ;
//        Arrays.stream(getFullProductListRs.getStatus().getAdditionalStatuses().toArray());

        boolean b1 = getFullProductListRs.getStatus().getAdditionalStatuses().stream().allMatch(p2);
        System.out.println(b1);
//        statusAdditionalStatusStatusCode = factory.createValueExpression(context, "#{g.status.additionalStatuses.isEmpty()}", boolean.class);
//        System.out.println(statusAdditionalStatusStatusCode.getValue(context));




        /* 4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts,
         и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts. */
        //Не получается сделать только одним juel
        System.out.println("4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции " +
                "metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts, и меньше чем все " +
                "элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции " +
                "statusDetailByProductList/statusDetailByProducts.");
        //TODO не могу применить ко всем элементам в коллекции
        GetFullProductListRs.StatusDetailByProductList statusDetailByProductList = new GetFullProductListRs.StatusDetailByProductList();
        StatusDetailByProductType statusDetailByProductType = new StatusDetailByProductType();
        TimeMetricListType timeMetricListType = new TimeMetricListType();
        TimeMetricType timeMetricType = new TimeMetricType();
        TimeRangeType timeOutList = new TimeRangeType();

//        ValueExpression query4 = factory.createValueExpression(context, "#{g.statusDetailByProductList.statusDetailByProducts.metricList.metrics.timeOutList.timeMin.firstDayOfWeek.stream().allMatch(p4)}", boolean.class);
//        ValueExpression statusAdditionalStatusStatusCode = factory.createValueExpression(context, "#{g.status.additionalStatuses.stream()}", Object.class);
//        System.out.println(query4.getValue(context));

        Calendar myClassImplCalendar = new Calendar() {


            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int i, int i1) {

            }

            @Override
            public void roll(int i, boolean b) {

            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }
        };
        timeOutList.setTimeMin(myClassImplCalendar);
        timeMetricType.withTimeOutList(timeOutList);
        timeMetricListType.withMetrics(timeMetricType);
        statusDetailByProductType.setMetricList(timeMetricListType);
//        statusDetailByProductList.withStatusDetailByProducts(statusDetailByProductType);
        statusDetailByProductList.getStatusDetailByProducts().add(statusDetailByProductType);
        getFullProductListRs.setStatusDetailByProductList(statusDetailByProductList);


        //TODO сделать не через два стрима а через flatMap может
        Predicate<TimeMetricType> p6 = it -> it.getTimeOutList().getTimeMin().getFirstDayOfWeek() < 7;
        Predicate<StatusDetailByProductType> p5 = it -> it.getMetricList().getMetrics().stream().allMatch(p6) ;
        context.setVariable("p5", factory.createValueExpression(p5, Predicate.class));
//        context.setVariable("p5", factory.createValueExpression(p5, Predicate.class));

        ValueExpression query4 = factory.createValueExpression(context, "#{g.getStatusDetailByProductList().getStatusDetailByProducts().stream().allMatch(p5)}", boolean.class);
//        query4 = factory.createValueExpression(context, "#{g.statusDetailByProductList.statusDetailByProducts.stream().skip(0).metricList.metrics.timeOutList.timeMin.stream().allMatch(p4)}", boolean.class);
//        query4 = factory.createValueExpression(context, "#{g.getStatusDetailByProductList().getStatusDetailByProducts().forEach(it -> {it.getMetricList().getMetrics().forEach(it2 -> {if (it2.getTimeOutList().getTimeMin().getFirstDayOfWeek() < 7)}); });.stream().allMatch(p4)}", boolean.class);
//        query4 = factory.createValueExpression(context, "#{g.getStatusDetailByProductList.getStatusDetailByProducts.get(0).getMetricList().getMetrics.get(0).getTimeOutList.timeMin.stream().allMatch(p4)}", boolean.class);
//        ValueExpression statusAdditionalStatusStatusCode = factory.createValueExpression(context, "#{g.status.additionalStatuses.stream()}", Object.class);
        System.out.println(query4.getValue(context));

//        System.out.println(getFullProductListRs.getStatusDetailByProductList().getStatusDetailByProducts().stream().allMatch(p5));
//        getFullProductListRs.getStatusDetailByProductList().getStatusDetailByProducts().forEach(it -> {
//            it.getMetricList().getMetrics().forEach(it2 -> {
//                if (it2.getTimeOutList().getTimeMin().getFirstDayOfWeek() < 7)
//                    System.out.println("true");
//            });
//        });
//        getFullProductListRs.getStatusDetailByProductList().getStatusDetailByProducts().get(0).getMetricList().getMetrics().get(0).getTimeOutList().getTimeMin().getFirstDayOfWeek();

//        System.out.println("firstDayOfWeek " + getFullProductListRs.statusDetailByProductList.statusDetailByProducts.get(0).metricList.metrics.get(0).timeOutList.timeMin.firstDayOfWeek)
//            getFullProductListRs.statusDetailByProductList.statusDetailByProducts


        /* 5. в коллекции acctTypes нет значений DEPOSIT и IMA. */
        System.out.println("5. в коллекции acctTypes нет значений DEPOSIT и IMA.");

        ValueExpression acctTypesIncludesValues = factory.createValueExpression(context, "#{g.acctTypes.contains(param2)}", boolean.class);
        System.out.println(acctTypesIncludesValues.getValue(context));

        AcctTypeType accType = AcctTypeType.IMA;
        getFullProductListRs.withAcctTypes(accType);

//        System.out.println(getFullProductListRs.getAcctTypes().contains(AcctTypeType.IMA));

        acctTypesIncludesValues = factory.createValueExpression(context, "#{g.acctTypes.contains(param2)}", boolean.class);
        System.out.println(acctTypesIncludesValues.getValue(context));


        /* 6. rqUID == 'qwe123' и список status/additionalStatus - пуст или == null. */
        //        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList2 = null
        //        getFullProductListRs.status.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2)
        System.out.println("6. rqUID == 'qwe123' и список status/additionalStatus - пуст или == null.");

        ValueExpression multiCondition = factory.createValueExpression(context, "#{g.rqUID == 'qwe123' and g.status.additionalStatus.isEmpty()}", boolean.class);
        System.out.println(multiCondition.getValue(context));
        getFullProductListRs.setRqUID("qwe123");
        multiCondition = factory.createValueExpression(context, "#{g.rqUID == 'qwe123' or g.status.additionalStatus.isEmpty()}", boolean.class);
        System.out.println(multiCondition.getValue(context));

        /* 7. все поля status/statusDesc начинаются с "success". */
        //TODO не могу применить ко всем элементам в коллекции
        System.out.println("7. все поля status/statusDesc начинаются с success.");
        GetFullProductListRs.Status status4 = new GetFullProductListRs.Status();
        status4.setStatusDesc("successfully");
        getFullProductListRs.setStatus(status4);


        /* 8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению. */
        System.out.println("8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению.");
        ValueExpression acctTypesIncludesOneEl = factory.createValueExpression(context, "#{g.acctTypes.size() == 1 and g.acctTypes.get(0) == param2}", boolean.class);
        System.out.println(acctTypesIncludesOneEl.getValue(context));

        getFullProductListRs.withAcctTypes(AcctTypeType.CARD);

        acctTypesIncludesOneEl = factory.createValueExpression(context, "#{g.acctTypes.size() == 1 and g.acctTypes.get(0) == param2}", boolean.class);
        System.out.println(acctTypesIncludesOneEl.getValue(context));


        /* 9. проверить что operUID и systemId не равны null. */
        System.out.println("9. проверить что operUID и systemId не равны null.");
        getFullProductListRs.setOperUID("operUID");
        ValueExpression multiCon = factory.createValueExpression(context, "#{g.operUID != null and g.systemId != null}", boolean.class);
        System.out.println(multiCon.getValue(context));

//        getFullProductListRs.setOperUID("operUID");
        getFullProductListRs.setSystemId("SystemId");

        multiCon = factory.createValueExpression(context, "#{g.operUID != null and g.systemId != null}", boolean.class);
        System.out.println(multiCon.getValue(context));


    }
}
