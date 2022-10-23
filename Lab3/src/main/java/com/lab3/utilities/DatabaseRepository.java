package com.lab3.utilities;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRepository {
    private Connection connection;

    private static DatabaseRepository dbInstance = null;

    private DatabaseRepository() {
        try {
            Context ctx = new InitialContext(System.getProperties());
            DataSource dataSource = (DataSource) ctx.lookup("postgresjndi");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseRepository get() {
        if (dbInstance == null) {
            dbInstance = new DatabaseRepository();
        }
        return dbInstance;
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