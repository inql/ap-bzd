package com.inql.psqljdbc.logic;

import com.inql.psqljdbc.model.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DatabaseOperation {

    private Data data;
    private String tableName;
    private Connection connection;

    public DatabaseOperation(Data data, String tableName, Connection connection) {
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
        Iterator iterator = data.getColumnNames().listIterator();
        while(iterator.hasNext()){
            sqlStatement.append(",");
            sqlStatement.append(iterator.next()).append(" VARCHAR(20)");
        }
        sqlStatement.append(")");
        statement.addBatch(sqlStatement.toString());
        statement.executeBatch();
        connection.commit();
        insertInto();
    }

    public void insertInto(){
        Statement statement = connection.createStatement();
        try {
            ResultSet tableResultSet = statement.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData tableResultSetMetaData = tableResultSet.getMetaData();
            StringBuilder sqlStatement = new StringBuilder();
            sqlStatement.append("INSERT INTO ").append(tableName).append(" (");
            for(int i =1; i<=tableResultSetMetaData.getColumnCount(); i++){
                sqlStatement.append(tableResultSetMetaData.getColumnName(i));
                if(i<tableResultSetMetaData.getColumnCount())
                    sqlStatement.append(", ");
            }
            sqlStatement.append(") VALUES");
            for(List<String> values : data.getValues()){
                sqlStatement.append("\n(");
                for(int i =0; i<tableResultSetMetaData.getColumnCount(); i++){
                    if(i<values.size()){
                        if(values.get(i).equals(""))
                            sqlStatement.append("null");
                        else
                            sqlStatement.append(values.get(i));
                    }
                    else sqlStatement.append("null");
                    if(i<tableResultSetMetaData.getColumnCount()-1)
                        sqlStatement.append(", ");
                }
                sqlStatement.append(")");
            }
            statement.execute(sqlStatement.toString());
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
