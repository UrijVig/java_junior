package main.java.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            departments.add(new Department("Department #" + i));
        }

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    RandomGenerator.getRandomWorld(3,6),
                    ThreadLocalRandom.current().nextInt(20, 61),
                    ThreadLocalRandom.current().nextInt(20_000, 100_000) * 1.0,
                    departments.get(ThreadLocalRandom.current().nextInt(departments.size()))
            ));
        }

        HomeWork hw = new HomeWork();

        System.out.println("Список сотрудников по алфавиту: ");
        hw.printNamesOrdered(persons);
        System.out.println();

        System.out.println("Ветераны труда в департаментах: ");
        Map<Department,Person> oldestPerson = hw.printDepartmentOldestPerson(persons);
        for (Map.Entry<Department, Person> entry : oldestPerson.entrySet()) {
            System.out.println(entry);
        }
        System.out.println();

        System.out.println("Молодые и успешные сотрудники (первые 10): ");
        List<Person> youngPerson = hw.findFirstPersons(persons);
        youngPerson.forEach(System.out::println);
        System.out.println();

        System.out.println("Самый высокобюджетный департамент: ");
        Optional<Department> topDepartment = hw.findTopDepartment(persons);
        System.out.println(topDepartment);
        System.out.println();


    }

}
