package com.inql.psqljdbc.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLogging {

    public Connection connect(String dbUrl, String dbName, String dbUsername, String dbPassword) throws ClassNotFoundException, SQLException {
        Connection connection;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager
                .getConnection("jdbc:postgresql://"+dbUrl+":5432/"+dbName,
                        dbUsername, dbPassword);
        System.out.println("Opened database successfully");
        return connection;

    }



}
