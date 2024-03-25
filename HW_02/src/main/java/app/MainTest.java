package main.java.app;

import main.java.app.annotation.AfterAll;
import main.java.app.annotation.BeforeEach;
import main.java.app.annotation.BeforeAll;
import main.java.app.annotation.AfterEach;
import main.java.app.annotation.Test;

import java.lang.reflect.InvocationTargetException;

public class MainTest {
    public static void main(String[] args) {
        try {
            TestRunner.run(MainTest.class);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e + "Ошибка при попытке запуска тестового метода!");
        }
    }



    @BeforeAll
    void BeforeAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void BeforeEach1() {
        System.out.println("BeforeEach1");
    }

    @BeforeEach
    void BeforeEach2() {
        System.out.println("BeforeEach2");
    }
    @Test(order = 2)
    void test1() {
        System.out.println("test1");
    }

    @Test(order = 1)
    void test2() {
        System.out.println("test2");
    }

    @Test(order = 3)
    void test3() {
        System.out.println("test3");
    }

    @AfterEach
    void AfterEach1() {
        System.out.println("AfterEach1");
    }

    @AfterEach
    void AfterEach2() {
        System.out.println("AfterEach2");
    }

    @AfterAll
    void AfterAll() {
        System.out.println("AfterAll");
    }
}
