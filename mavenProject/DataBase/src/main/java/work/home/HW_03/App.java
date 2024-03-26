package work.home.HW_03;

import java.sql.*;
public class App 
{
    public static void main( String[] args ) {

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test")) {
            acceptConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void acceptConnection(Connection connection) throws SQLException {
        createTable(connection);
        insertInTable(connection);
        deleteRowFromTableByIdx(connection, 2);
        updateAgeStudentByIdx(connection, 4, 43);

        printTable(connection);
    }

    private static void printTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery
                    ("select id, first_name, second_name, age, from student");
            while (resultSet.next()){
                Student student = new Student(resultSet.getInt("id")
                ,resultSet.getString("first_name")
                ,resultSet.getString("second_name")
                ,resultSet.getInt("age"));

                System.out.println(student);
            }
        }
    }

    private static void updateAgeStudentByIdx(Connection connection, int idx, int age) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement
                ("update student set age = ? where id = ?")){
            statement.setInt(1, age);
            statement.setInt(2, idx);

            statement.executeUpdate();
        }
    }

    private static void deleteRowFromTableByIdx(Connection connection, int idx) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement
                ("delete from student where id = ?")){
            statement.setInt(1, idx);

            statement.executeUpdate();

        }
    }

    private static void insertInTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                insert into student(id, first_name,second_name,age) values
                (1, 'name1', 'for_name1', 34),
                (2, 'name2', 'for_name2', 34),
                (3, 'name3', 'for_name3', 34),
                (4, 'name4', 'for_name4', 34),
                (5, 'name5', 'for_name5', 34),
                (6, 'name6', 'for_name6', 34)
                """);
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute( """
                create table student(
                id bigint,
                first_name varchar(256),
                second_name varchar(256),
                age int
                )
                """);
        }
    }
}
