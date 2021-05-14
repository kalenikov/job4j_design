package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        connection = getConnection();
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    private void execute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws SQLException {
        execute(String.format(
                "create table if not exists %s();",
                tableName
        ));
    }

    public void dropTable(String tableName) throws SQLException {
        execute(String.format(
                "drop table if exists %s;",
                tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        execute(String.format(
                "alter table %s add column %s %s;",
                tableName,
                columnName,
                type
        ));
    }


    public void dropColumn(String tableName, String columnName) throws SQLException {
        execute(String.format(
                "alter table %s drop column if exists %s;",
                tableName,
                columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        execute(String.format(
                "alter table %s rename column %s to %s;",
                tableName,
                columnName,
                newColumnName
        ));
    }

    public String getScheme(String tableName) throws SQLException {
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.load(TableEditor.class.getClassLoader().getResourceAsStream("app.properties"));

        TableEditor te = new TableEditor(props);
        String table = "new_table";
        te.dropTable(table);
        te.createTable(table);
        te.addColumn(table, "column1", "int");
        te.addColumn(table, "column2", "int");
        te.dropColumn(table, "column2");
        te.renameColumn(table, "column1", "column1_new");
        System.out.println(te.getScheme(table));
        te.dropTable(table);
    }
}