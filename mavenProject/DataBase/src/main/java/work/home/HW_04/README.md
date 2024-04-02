## Урок 4. Базы данных и инструменты взаимодействия с ними

* Создать сущность 
[Student](https://github.com/UrijVig/java_junior/blob/master/mavenProject/DataBase/src/main/java/work/home/HW_04/Student.java) 
с полями:
    * id - int
    * firstName - string
    * secondName - string
    * age - int


 * [Подключить hibernate](https://github.com/UrijVig/java_junior/blob/master/mavenProject/DataBase/src/main/resources/hibernate.cfg.xml). Реализовать простые запросы: 
    * Find(by id)

        ~~~java
        public Student find(long id) {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
        }
        ~~~

    * Persist

        ~~~java
        public void persist(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
        }
        ~~~

    * Merge

        ~~~java
        public void merge(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(student);
        transaction.commit();
        session.close();
        }
        ~~~

    * Remove

        ~~~java
        public void remove(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(student);
        transaction.commit();
        session.close();
        }
        ~~~
    
        > *Описание данных методов можно найти в интерфейсе [UserDAO](https://github.com/UrijVig/java_junior/blob/master/mavenProject/DataBase/src/main/java/work/home/HW_04/DAO/UserDAO.java), а реализацию в классе [StudentDAO](https://github.com/UrijVig/java_junior/blob/master/mavenProject/DataBase/src/main/java/work/home/HW_04/DAO/iml/StudentDAO.java)*


 * Попробовать написать запрос поиска всех студентов старше 20 лет (session.createQuery)
    ~~~java
    public List<Student> findStudentsByFilter (String fieldName, int value, String condition){
            String select = String.format("FROM Student s WHERE s.%s %s :%s", fieldName, condition, fieldName);
            Session session = sessionFactory.openSession();
            List<Student> students = session.createQuery(select)
                    .setParameter(fieldName, value)
                    .list();
            session.close();
            return students;
        }
    ~~~
    > *Описание и реализацию данного метода можно найти в классе [StudentDAO](https://github.com/UrijVig/java_junior/blob/master/mavenProject/DataBase/src/main/java/work/home/HW_04/DAO/iml/StudentDAO.java)*
