package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;

public class ConnectionDemo {
    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        ClassLoader classLoader = ConnectionDemo.class.getClassLoader();
        Properties props = new Properties();
        props.load(classLoader.getResourceAsStream("app.properties"));
        return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("login"),
                props.getProperty("password"));
    }


    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name varchar(255)"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

}
