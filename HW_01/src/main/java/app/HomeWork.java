package main.java.app;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Реалзиовать методы, описанные ниже:
 */
public class HomeWork {

    /**
     * Вывести на консоль отсортированные (по алфавиту) имена персонов
     */
    public void printNamesOrdered(List<Person> persons) {
        persons.stream().
                sorted(Comparator.comparing(Person::getName)).
                forEach(System.out::println);
    }

    /**
     * В каждом департаменте найти самого взрослого сотрудника.
     * Вывести на консоль мапипнг department -> personName
     * Map<Department, Person>
     */
    public Map<Department, Person> printDepartmentOldestPerson(List<Person> persons) {
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
        return persons.stream()
                .collect(Collectors.toMap(Person::getDepartment, Function.identity(),
                        (first, second) -> {
                    if(ageComparator.compare(first,second) > 0) return first;
                    return second;
                        }));
    }

    /**
     * Найти 10 первых сотрудников, младше 30 лет, у которых зарплата выше 50_000
     */
    public List<Person> findFirstPersons(List<Person> persons) {
        return persons.stream()
                .filter(it -> it.getAge() < 30)
                .filter(it -> it.getSalary() > 50_000)
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * Найти депаратмент, чья суммарная зарплата всех сотрудников максимальна
     */
    public Optional<Department> findTopDepartment(List<Person> persons) {
        Map<Department, Double> budget = persons.stream()
                .collect(Collectors.groupingBy(Person::getDepartment, Collectors.summingDouble(Person::getSalary)));
//        for (Map.Entry<Department, Double> entry : budget.entrySet()) {
//            System.out.println(entry);
//        }
        return budget.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
