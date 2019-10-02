# LanguagesCompare

В данном отчете приводится сравнение пяти языков (Java, Groovy, Kotlin, Scala, JuEL) для написания запросов (условий).

## Описание задачи

Вся работа происходит с java - классами, поэтому выбор языков обусловлен этой особенностью. Все они являются языками на платформе JVM. 

Задание состояло в проверке 2 списков условий. Все условия можно условно разделить на простые и сложные. Простые условия зачастую заключаются в проверке значения какого-то поля, сравнение его с другим значением и т д. Сложные же условия - это как правило различные множественные вложенности коллекций и проверка нескольких условий на разных уровнях вложенности.
## Особенности, а так же выявленные плюсы и минусы каждого из языков

### Java
Java — строго типизированный объектно-ориентированный язык программирования.

#### Плюсы 
+ Си - подобный язык, прост в освоении
+ присутствуют подходы функционального программирования: лямбда выражения, методы forEach(), stream()
#### Минусы 
- громоздкие условия, если использовать циклы 
#### Пример простого условия
```
//        2. Равно ли поле scope какому-то конкретному значению.
        if (client.getScope().equals("TestScope")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
```
#### Пример сложного условия
```
// 8. в коллекции acctTypes - один элемент и он равен какому то конкретному значению.
        if (getFullProductListRs.getAcctTypes().size() == 1 && getFullProductListRs.getAcctTypes().get(0) == AcctTypeType.IMA)
            System.out.println(true);
        else
            System.out.println(false);
```
### Scala
Scala — мультипарадигмальный язык программирования, сочетающий возможности функционального и объектно-ориентированного программирования.

#### Плюсы 
+ поддержка функциональности "из коробки", учитывая, что все сложные запросы включают работу с коллекциями 
#### Минусы 
- сложность в освоении
#### Пример простого условия
```
// 2) Равно ли поле scope какому-то конкретному значению.
    if (client.getScope == "TestScope") {
      println(true)
    } else {
      println(false)
    }
```
#### Пример сложного условия
```
// 4) Какое то конкретное значение больше чем все элементы timeOutList/timeMin в коллекции metricList/metrics в коллекции statusDetailByProductList/statusDetailByProducts

    var result4 = false
    g.getStatusDetailByProductList.getStatusDetailByProducts.forEach(m => m.getMetricList.getMetrics.forEach(t => if (t.getTimeOutList.getTimeMin.getFirstDayOfWeek > 6) result4 = result4 & true  else result4 = result4 & false))
    print(result4)
```

### Kotlin
Kotlin — статически типизированный язык программирования, работающий поверх JVM и разрабатываемый компанией JetBrains. 

#### Плюсы 
+ прост в освоении 
+ присутствуют подходы функционального программирования
+ легко реализуемая null-безопасность
#### Минусы 
не выявлены
#### Пример простого условия
```
/** 4. Равно ли поле status/code 0. */
    if (client.status.code == 0L) {
        println(true)
    } else {
        println(false)
    }
```
#### Пример сложного условия
```
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
```
### Groovy
Groovy — объектно-ориентированный язык программирования, разработанный для платформы Java как дополнение к языку Java с возможностями Python, Ruby и Smalltalk. 

#### Плюсы 
+ прост в освоении 
+ присутствуют подходы функционального программирования
+ легко реализуемая null-безопасность
#### Минусы 
не выявлены
#### Пример простого условия
```
// 6) Равно ли поле scope одному из значений a,b или с.
        if (client.scope == "notTestScope" || client.scope == "notTestScope1" || client.scope == "notTestScope2") {
            println true
        } else {
            println false
        }
```
#### Пример сложного условия
```
// 5) в коллекции acctTypes нет значений DEPOSIT и IMA.
        Boolean result5 = true
        getFullProductListRs.acctTypes.each {
            if (it.toString() == "DEPOSIT" || it.toString() == "IMA")
                result5 = result5 & false
            else
                result5 = result5 & true
        }
        println result5
```

### JuEL
JUEL - это реализация унифицированного языка выражений (EL), определенного как часть стандарта JSP 2.1.

#### Плюсы 
+ весь функционал Java
+ все условия и их проверка в одну строчку
+ высокая скорость написания, так как простой и емкий синтаксис
+ EL, т.е. не обязательно уметь программировать
#### Минусы 
не выявлены
#### Пример простого условия
```
context.setVariable("client2", factory.createValueExpression(client2, client.getClass()));

 /* 7. Содержит ли scope в себе какое-то значение. */
        ValueExpression scopeIsEmpty = factory.createValueExpression(context, "#{client2.scope == null}", boolean.class);
        System.out.println(scopeIsEmpty.getValue(context));
```
#### Пример сложного условия
```
 context.setVariable("g", factory.createValueExpression(getFullProductListRs, getFullProductListRs.getClass()));

/* 9. проверить что operUID и systemId не равны null. */
        ValueExpression multiCon = factory.createValueExpression(context, "#{g.operUID != null and g.systemId != null}", boolean.class);
        System.out.println(multiCon.getValue(context));
```

## Выводы
1) Простые запросы почти в каждом из языков одинаковы. Нет разницы, что использовать. Разве что можно выделить Kotlin, Groovy так как в них null-безопасность решена чуть изящнее (с помощью "?"), чем в Scala и Java, в которых придется использовать контейнер Optional.
2) Сложные запросы тоже очень похожи во всех этих языках, так как везде для их реализации удобнее использовать подходы из функционального программирования: лямбда-выражения, стримы, и такие методы для работы с коллекциями, как forEach(), flatMap(), each() и т. д. имеющиеся в каждом из исследуемых языков.
3) Все языки показали себя примерно равными, но я бы выделил, отчасти субъективно, JuEL. 
Во-первых это единственный язык выражений (Expression Language), это означает, что не придется изучать синтаксис полноценного языка программирования.
Во-вторых, при прочих равных видны очевидные плюсы в виде простоты и наглядности и как следствие более высокой скорости освоения и написания на нем условий.
В-третьих, этот язык используется в ряде уже существующих систем, с подобным назначением. 
