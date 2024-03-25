## Урок 2. Reflection API

Доделать запускатель тестов:

* Создать аннотации 
[BeforeEach](https://github.com/UrijVig/java_junior/blob/master/HW_02/src/main/java/app/annotation/BeforeEach.java), 
[BeforeAll](https://github.com/UrijVig/java_junior/blob/master/HW_02/src/main/java/app/annotation/BeforeAll.java), 
[AfterEach](https://github.com/UrijVig/java_junior/blob/master/HW_02/src/main/java/app/annotation/AfterEach.java), 
[AfterAll](https://github.com/UrijVig/java_junior/blob/master/HW_02/src/main/java/app/annotation/AfterAll.java)
*  Доработать класс 
[TestRunner](https://github.com/UrijVig/java_junior/blob/master/HW_02/src/main/java/app/TestRunner.java) 
так, что

    * Перед всеми тестами запускаеются методы, над которыми стоит BeforeAll

    * Перед каждым тестом запускаются методы, над которыми стоит BeforeEach

    * Запускаются все тест-методы (это уже реализовано)

    * После каждого теста запускаются методы, над которыми стоит AfterEach

    * После всех тестов запускаются методы, над которыми стоит AfterAll

        ***Другими словами: BeforeAll -> BeforeEach -> Test1 -> AfterEach -> BeforeEach -> Test2 -> AfterEach -> AfterAll***

* *Доработать аннотацию 
[Test](https://github.com/UrijVig/java_junior/blob/master/HW_02/src/main/java/app/annotation/Test.java)
: добавить параметр int order,
по котрому нужно отсортировать тест-методы (от меньшего к большему) и запустить в нужном порядке.
Значение order по умолчанию - 0
