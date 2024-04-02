package work.home.HW_04;

import work.home.HW_04.DAO.iml.StudentDAO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = new ArrayList<>();

//        Student jon = new Student(10, "Jon", "Resly", 26);

        for (int i = 0; i < 10; i++) {
            students.add(new Student(i, "first_name" +i, "second_name" + i, (int) (Math.random() * 10 + 15)));
        }
        for(Student st: students){
            studentDAO.persist(st);
        }
        List<Student> dbResult = studentDAO.getAll();
        System.out.println(dbResult);

        Student findStudent = studentDAO.find(2);
        System.out.println(findStudent);

        findStudent.setFirst_name("Fil");
        findStudent.setSecondName("Pet");
//        findStudent.setId(10);
        studentDAO.merge(findStudent);

        dbResult = studentDAO.getAll();
        System.out.println(dbResult);

        studentDAO.remove(findStudent);

        dbResult = studentDAO.getAll();
        System.out.println(dbResult);

        dbResult = studentDAO.findStudentsByFilter("age", 20, "=");
        System.out.println(dbResult);

        studentDAO.close();
    }
}
