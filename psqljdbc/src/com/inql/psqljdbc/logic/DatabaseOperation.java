package com.inql.psqljdbc.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DatabaseOperation {

    private ArrayList<List<String>> data;
    private String tableName;
    private Connection connection;

    public DatabaseOperation(ArrayList<List<String>> data, String tableName, Connection connection) {
        this.data = data;
        this.tableName = tableName;
        this.connection = connection;
    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        StringBuilder sqlStatement = new StringBuilder();
        connection.setAutoCommit(false);
        sqlStatement.append("DROP TABLE IF EXISTS ").append(tableName).append(" CASCADE");
        statement.addBatch(sqlStatement.toString());
        sqlStatement = new StringBuilder();
        sqlStatement.append("CREATE TABLE ").append(tableName)
                .append(" (ID SERIAL PRIMARY KEY");
        Iterator iterator = data.get(0).listIterator();
        while(iterator.hasNext()){
            sqlStatement.append(",");
            sqlStatement.append(iterator.next()).append(" VARCHAR(20)");
        }
        sqlStatement.append(")");
        statement.addBatch(sqlStatement.toString());
        statement.executeBatch();
        connection.commit();

    }
}
