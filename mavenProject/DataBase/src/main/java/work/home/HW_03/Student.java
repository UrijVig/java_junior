package work.home.HW_03;

import work.home.HW_03.annotation.Column;
import work.home.HW_03.annotation.ID;
import work.home.HW_03.annotation.Table;

@Table(name = "student")
public class Student {
    @ID
    private int ID;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "second_name")
    private String second_name;
    @Column(name = "age")
    private int age;

    public Student() {
    }

    public Student(int id, String firstName, String secondName, int age) {
        this.ID = id;
        this.first_name = firstName;
        this.second_name = secondName;
        this.age = age;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + ID +
                ", firstName='" + first_name + '\'' +
                ", secondName='" + second_name + '\'' +
                ", age=" + age +
                '}';
    }
}
