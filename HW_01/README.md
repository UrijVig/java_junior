## Урок 1. Лямбды и Stream API

* Вывести на консоль отсортированные (по алфавиту) имена персонов

    ~~~java
    public void printNamesOrdered(List<Person> persons) {
        persons.stream().
                sorted(Comparator.comparing(Person::getName)).
                forEach(System.out::println);
    }
    ~~~

* В каждом департаменте найти самого взрослого сотрудника.
  Вывести на консоль мапипнг department -> personName
  Map<Department, Person>

~~~java
    public Map<Department, Person> printDepartmentOldestPerson(List<Person> persons) {
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
        return persons.stream()
                .collect(Collectors.toMap(Person::getDepartment, Function.identity(),
                        (first, second) -> {
                    if(ageComparator.compare(first,second) > 0) return first;
                    return second;
                        }));
    }
~~~

* Найти 10 первых сотрудников, младше 30 лет, у которых зарплата выше 50_000

~~~java
public List<Person> findFirstPersons(List<Person> persons) {
        return persons.stream()
                .filter(it -> it.getAge() < 30)
                .filter(it -> it.getSalary() > 50_000)
                .limit(10)
                .collect(Collectors.toList());
    }
~~~

* Найти депаратмент, чья суммарная зарплата всех сотрудников максимальна

~~~java
public Optional<Department> findTopDepartment(List<Person> persons) {
        Map<Department, Double> budget = persons.stream()
                .collect(Collectors.groupingBy(Person::getDepartment, Collectors.summingDouble(Person::getSalary)));
        return budget.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
~~~

[Описание методов](https://github.com/UrijVig/java_junior/blob/master/HW_01/src/main/java/app/HomeWork.java)
[Вызовы и демнстрация](https://github.com/UrijVig/java_junior/blob/master/HW_01/src/main/java/app/Main.java)