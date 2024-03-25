package main.java.app;

import main.java.app.annotation.*;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class TestRunner {
    public static <List> void run(Class<?> testClass) throws InvocationTargetException, IllegalAccessException {
        final Object testObj = initTestObj(testClass);
        Method[] methodArray = Arrays.stream(testClass.getDeclaredMethods())
                .filter(it -> !it.accessFlags().contains(AccessFlag.PRIVATE))
                .toList().toArray(new Method[0]);
        Method[] beforeEachList = Arrays.stream(methodArray)
                .filter(it -> it.getAnnotation(BeforeEach.class) != null)
                .toList().toArray(new Method[0]);
        Method[] beforeAllList = Arrays.stream(methodArray)
                .filter(it -> it.getAnnotation(BeforeAll.class) != null)
                .toList().toArray(new Method[0]);
        Method[] testList = Arrays.stream(methodArray)
                .filter(it -> it.getAnnotation(Test.class) != null)
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(Test.class).order()))
                .toList().toArray(new Method[0]);
        Method[] afterEachList = Arrays.stream(methodArray)
                .filter(it -> it.getAnnotation(AfterEach.class) != null)
                .toList().toArray(new Method[0]);
        Method[] afterAllList = Arrays.stream(methodArray)
                .filter(it -> it.getAnnotation(AfterAll.class) != null)
                .toList().toArray(new Method[0]);

        foreach(beforeAllList,testObj);
        for (Method testMethod : testList) {
            foreach(beforeEachList,testObj);
            testMethod.invoke((testObj));
            foreach(afterEachList,testObj);
        }
        foreach(afterAllList,testObj);


    }
    private static void foreach(Method[] methods, Object testObj) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            method.invoke(testObj);
        }
    }

    private static Object initTestObj(Class<?> testClass) {
        try {
            Constructor<?> noArgsConstructor = testClass.getConstructor();
            return noArgsConstructor.newInstance();
        } catch (InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException
                 | IllegalAccessException e) {
            throw new RuntimeException(e + " Не удалось создать объект Тест класса! ");
        }
    }
}
