package com.lab3.utilities;

import java.sql.*;

public class DatabaseRepository {
    private Connection connection;
    private static DatabaseRepository databaseInstance = null;

    private DatabaseRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Lab3db", "postgres", "admin");
            System.out.println("Connected to PostgreSQL database!");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public static DatabaseRepository get() {
        if (databaseInstance == null) {
            databaseInstance = new DatabaseRepository();
        }
        return databaseInstance;
    }

    public ResultSet run(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}