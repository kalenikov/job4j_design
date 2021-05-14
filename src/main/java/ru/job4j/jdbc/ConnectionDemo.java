package ru.job4j.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        ClassLoader classLoader = ConnectionDemo.class.getClassLoader();
        Properties props = new Properties();
        props.load(classLoader.getResourceAsStream("app.properties"));
        try (Connection connection = DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("login"),
                props.getProperty("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
