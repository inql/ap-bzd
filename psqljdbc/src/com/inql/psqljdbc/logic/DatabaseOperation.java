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
        try {
            Statement statement = connection.createStatement();
            ResultSet tableResultSet = statement.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData tableResultSetMetaData = tableResultSet.getMetaData();
            StringBuilder sqlStatement = new StringBuilder();
            StringBuilder sqlStatementTemplate = new StringBuilder();
            sqlStatement.append("INSERT INTO ").append(tableName).append(" (");
            sqlStatementTemplate.append("(");
            for(int i =2; i<=tableResultSetMetaData.getColumnCount(); i++){
                sqlStatement.append(tableResultSetMetaData.getColumnName(i));
                sqlStatementTemplate.append("?");
                if(i<tableResultSetMetaData.getColumnCount()){
                    sqlStatement.append(", ");
                    sqlStatementTemplate.append(", ");
                }
            }
            sqlStatement.append(") VALUES ");
            sqlStatementTemplate.append(")");
            StringBuilder preAlterTableStringBuilder = new StringBuilder().append("ALTER TABLE ").append(tableName).append(" ALTER COLUMN ");
            Statement alterTableStatement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.append(sqlStatementTemplate).toString());
            for(Iterator<List<String>> i = data.getValues().listIterator(); i.hasNext();){
                List<String> values = i.next();
                Iterator<String> valueIterator = values.listIterator();
                for(int j =1; j<=preparedStatement.getParameterMetaData().getParameterCount(); j++){
                    if(valueIterator.hasNext()){
                        String value = valueIterator.next();
                            if(tableResultSetMetaData.getColumnDisplaySize(j+1)<value.length()){
                                StringBuilder alterTable = new StringBuilder().append(preAlterTableStringBuilder).append(tableResultSetMetaData.getColumnName(j+1)).append(" TYPE VARCHAR(").append(value.length()).append(")");
                                alterTableStatement.execute(alterTable.toString());
                                tableResultSetMetaData = statement.executeQuery("SELECT * FROM "+tableName).getMetaData();
                            }
                            if(value.equals(""))
                                preparedStatement.setString(j,"null");
                            else
                                preparedStatement.setString(j,value);
                        }
                    else preparedStatement.setString(j,"null");
                }
                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
