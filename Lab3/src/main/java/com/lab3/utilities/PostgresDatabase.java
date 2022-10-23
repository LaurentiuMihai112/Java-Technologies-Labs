package com.lab3.utilities;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresDatabase {
    private Connection connection;

    private static PostgresDatabase dbInstance = null;

    private PostgresDatabase() {
        try {
            Context ctx = new InitialContext(System.getProperties());
            DataSource dataSource = (DataSource) ctx.lookup("postgresjndi");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static PostgresDatabase get() {
        if (dbInstance == null) {
            dbInstance = new PostgresDatabase();
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
