package work.home.HW_04.DAO.iml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import work.home.HW_04.DAO.UserDAO;
import work.home.HW_04.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с базой данных в таблице Student
 */
public class StudentDAO implements UserDAO<Student> {
    private final SessionFactory sessionFactory;

    public StudentDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Student find(long id) {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    @Override
    public void persist(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void merge(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void remove(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(student);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        List<Student> students = new ArrayList<>(session.createQuery("from Student s", Student.class).getResultList());
        session.close();
        return students;
    }

    /**
     * Метод для оиска объектов согласно указанному фильтру
     *
     * @param fieldName название искомого поля передаётся в формате "name"
     * @param value пороговое значение фильтра тип int
     * @param condition условие фильтрации: ">" ">=" "<" "<=" "="
     * @return список объектов удовлетворяющих условию
     */
    public List<Student> findStudentsByFilter (String fieldName, int value, String condition){
        String select = String.format("FROM Student s WHERE s.%s %s :%s", fieldName, condition, fieldName);
        Session session = sessionFactory.openSession();
        List<Student> students = session.createQuery(select)
                .setParameter(fieldName, value)
                .list();
        session.close();
        return students;
    }

    /**
     * Метод для закрытия сессии с базой данных
     */
    public  void close(){
        sessionFactory.close();
    }

}
