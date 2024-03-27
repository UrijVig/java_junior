package work.home.HW_03.task_02;

import work.home.HW_03.Student;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test")){
            Student student = new Student(1, "name1", "for_name1", 34);
            DataBaseManager<Student> dbm = new DataBaseManager<>(connection, student);
            dbm.save(student);
            dbm.save(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
