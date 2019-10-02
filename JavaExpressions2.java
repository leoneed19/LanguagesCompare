package ru.sbt.fedorov.jvm;

import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.AcctTypeType;
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.AdditionalStatusGFPLType;
import ru.sbt.fedorov.com.sbt.pprb.dto.esb.srvgetfullproductlist.GetFullProductListRs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class JavaExpressions2 {
    public static void main(String[] args) {
        GetFullProductListRs getFullProductListRs = new GetFullProductListRs();
        GetFullProductListRs getFullProductListRs2 = new GetFullProductListRs();
        GetFullProductListRs.Status status = new GetFullProductListRs.Status();
        status.setStatusCode(0);

        getFullProductListRs.setStatus(status);
        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList = new ArrayList<>();
        AdditionalStatusGFPLType additionalStatusGFPLType = new AdditionalStatusGFPLType(0, null, null, null);
        AdditionalStatusGFPLType additionalStatusGFPLType2 = new AdditionalStatusGFPLType(2, null, null, null);
        AdditionalStatusGFPLType additionalStatusGFPLType3 = new AdditionalStatusGFPLType(3, null, null, null);
        AdditionalStatusGFPLType additionalStatusGFPLType4 = new AdditionalStatusGFPLType(4, null, null, null);
        AdditionalStatusGFPLType additionalStatusGFPLType5 = new AdditionalStatusGFPLType(5, null, null, null);

        AcctTypeType acctTypeType = AcctTypeType.IMA;
        getFullProductListRs.setOperUID("11111");
        getFullProductListRs.setRqUID("qwe123");
        getFullProductListRs.getAcctTypes().add(acctTypeType);

        // 1. поле status/statusCode == 0
        System.out.println("1. поле status/statusCode == 0");
        if (getFullProductListRs.getStatus().getStatusCode() == 0)
            System.out.println(true);
        else System.out.println(false);

        getFullProductListRs.getStatus().setStatusCode(1);

        if (getFullProductListRs.getStatus().getStatusCode() == 0)
            System.out.println(true);
        else System.out.println(false);


        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList2 = null;
        GetFullProductListRs.Status status2 = new GetFullProductListRs.Status();
        status2.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2);

        getFullProductListRs.setStatus(status2);

        // 2. список status/additionalStatuses - пуст или == null
        System.out.println("2. список status/additionalStatuses - пуст или == null");
        if (getFullProductListRs.getStatus().getAdditionalStatuses().size() == 0)
            System.out.println(true);
        else
            System.out.println(false);

        // 3. все поля statusCode в коллекции status/additionalStatus == 0
        System.out.println("3. все поля statusCode в коллекции status/additionalStatus == 0");
        // 4. Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts,
        //        и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts.
        System.out.println("Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts, и меньше чем все элементы timeOutList/timeMax в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts.");
        int currentValue = 10;
        AtomicReference<Boolean> result4 = new AtomicReference<>(false);
        try {
            getFullProductListRs.getStatusDetailByProductList().getStatusDetailByProducts().forEach(it -> it.getMetricList().getMetrics().forEach(it2 -> {
                if (it2.getTimeOutList().getTimeMin().getFirstDayOfWeek() == currentValue)
                    result4.set(result4.get() & true);
                else
                    result4.set(result4.get() & false);
            }));

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(result4);

        // 5. в коллекции acctTypes нет значений DEPOSIT и IMA.
        System.out.println("5. в коллекции acctTypes нет значений DEPOSIT и IMA.");
        AtomicReference<Boolean> result5 = new AtomicReference<>(true);
        getFullProductListRs.getAcctTypes().forEach(it -> {
            if (it.toString().equals("DEPOSIT") || it.toString().equals("IMA"))
                result5.set(result5.get() & false);
            else
                result5.set(result5.get() & true);
        });
        System.out.println(result5);

        // 6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null.
//        List<AdditionalStatusGFPLType> additionalStatusGFPLTypeArrayList2 = null
//        getFullProductListRs.status.withAdditionalStatuses(additionalStatusGFPLTypeArrayList2)
        System.out.println("6. rqUid == 'qwe123' и список status/additionalStatus - пуст или == null.");
        if (getFullProductListRs.getRqUID().equals("qwe123") & getFullProductListRs.getStatus().getAdditionalStatuses().size() == 0)
            System.out.println(true);
        else
            System.out.println(false);

        // 7. все поля status/statusDesc начинаются с "success".
        System.out.println("7. все поля status/statusDesc начинаются с success.");
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        String it = getFullProductListRs.getStatus().getStatusDesc();
        if (it.startsWith("success"))
            result.set(result.get() & true);
        else
            result.set(result.get() & false);

        System.out.println(result);

        // 8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению.
        System.out.println("8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению.");
        if (getFullProductListRs.getAcctTypes().size() == 1 && getFullProductListRs.getAcctTypes().get(0) == AcctTypeType.IMA)
            System.out.println(true);
        else
            System.out.println(false);

        // 9. проверить что operUID и syatemId не равны null.
        System.out.println("9. проверить что operUID и systemId не равны null.");

        if (getFullProductListRs.getOperUID() != null && getFullProductListRs.getSystemId() != null)
            System.out.println(true);
        else
            System.out.println(false);

    }

}
                
