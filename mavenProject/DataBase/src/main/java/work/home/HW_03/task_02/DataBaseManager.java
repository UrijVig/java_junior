package work.home.HW_03.task_02;

import work.home.HW_03.annotation.Column;
import work.home.HW_03.annotation.ID;
import work.home.HW_03.annotation.Table;

import java.lang.reflect.Field;
import java.sql.*;

public class DataBaseManager<T> {
    private final Connection CONNECTION;

    public DataBaseManager(Connection connection, T t) throws SQLException {
        this.CONNECTION = connection;
        createTable(t);

    }

    public void save(T t) throws SQLException {
        insertInToTable(t);
        printData(t);

    }

    private void printData(T t) throws SQLException {
//        T j = (T) t.getClass().getConstructor().newInstance();
        try (Statement st = CONNECTION.createStatement()) {
            ResultSet data = st.executeQuery(selectGenerator(t));
            StringBuilder sql = new StringBuilder();
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getAnnotation(ID.class) != null) {
                    sql.append(field.getAnnotation(ID.class).name());
                    sql.append(" = ")
                            .append(data.getInt(field.getAnnotation(ID.class).name()));
                }
                if (field.getAnnotation(Column.class) != null) {
                    sql.append(field.getAnnotation(Column.class).name());
                    sql.append(" = ");
                    if (field.getType() == int.class) {
                        sql.append(data.getInt(field.getAnnotation(ID.class).name()));
                    } else if (field.getType() == String.class) {
                        sql.append(data.getString(field.getAnnotation(ID.class).name()));
                    }
                }
            }
            System.out.println(sql);
        }
    }

    private void insertInToTable(T t) throws SQLException {
        try (Statement st = CONNECTION.createStatement()) {
            st.execute(insertGenerator(t));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable(T t) throws SQLException {
        try (Statement st = CONNECTION.createStatement()) {
            st.execute(createGenerator(t));
        }
    }

    public ResultSet getData(String sql) throws SQLException {
        try (Statement st = CONNECTION.createStatement()) {
            return st.executeQuery(sql);
        }
    }

    private String insertGenerator(T t) throws IllegalAccessException {
        StringBuilder sql = new StringBuilder();
        String tableName = t.getClass().getAnnotation(Table.class).name();
        sql.append("insert into ");
        sql.append(tableName).append(" values(");

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(ID.class) != null) {
                sql.append(field.get(t));
                sql.append(", ");
            }
            if (field.getAnnotation(Column.class) != null) {
                if (field.getType() == int.class) {
                    sql.append(field.get(t));
                } else if (field.getType() == String.class) {
                    sql.append('\'').append(field.get(t)).append('\'');
                }
                sql.append(", ");
            }
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(')');
        return sql.toString();
    }

    private String createGenerator(T t) {
        StringBuilder sql = new StringBuilder();
        String tableName = t.getClass().getAnnotation(Table.class).name();
        sql.append("create table ");
        sql.append(tableName).append("(\n");
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(ID.class) != null) {
                sql.append(field.getAnnotation(ID.class).name());
                sql.append(" int,\n");
            }
            if (field.getAnnotation(Column.class) != null) {
                sql.append(field.getAnnotation(Column.class).name());
                if (field.getType() == int.class) {
                    sql.append(" int,\n");
                } else if (field.getType() == String.class) {
                    sql.append(" varchar(256),\n");
                }
            }
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(')');
        return sql.toString();
    }

    private String selectGenerator(T t) {
        StringBuilder sql = new StringBuilder();
        String tableName = t.getClass().getAnnotation(Table.class).name();
        sql.append("select ");
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(ID.class) != null) {
                sql.append(field.getAnnotation(ID.class).name());
                sql.append(", ");
            }
            if (field.getAnnotation(Column.class) != null) {
                sql.append(field.getAnnotation(Column.class).name());
                sql.append(", ");
            }
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(" from ").append(tableName);
        return sql.toString();
    }

}
