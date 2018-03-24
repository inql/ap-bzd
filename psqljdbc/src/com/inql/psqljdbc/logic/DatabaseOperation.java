package com.inql.psqljdbc.logic;

import com.inql.psqljdbc.model.Data;

import java.sql.*;
import java.util.Iterator;
import java.util.List;

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
    }

    public void insertInto() throws SQLException {
            Statement statement = connection.createStatement();
            ResultSet tableResultSet = statement.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData tableResultSetMetaData = tableResultSet.getMetaData();
            //generate statement for insertion
            PreparedStatement preparedStatement = generateInsertionQuery(statement);
            for(Iterator<List<String>> i = data.getValues().listIterator(); i.hasNext();){
                List<String> values = i.next();
                Iterator<String> valueIterator = values.listIterator();
                for(int j =1; j<=preparedStatement.getParameterMetaData().getParameterCount(); j++){
                    if(valueIterator.hasNext()){
                        String value = valueIterator.next();
                            if(isColumnDisplaySizeCorrect(j+1,tableResultSetMetaData,value)){
                                tableResultSetMetaData = alterColumn(j+1, value.length(), tableResultSetMetaData);
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

    }

    private PreparedStatement generateInsertionQuery(Statement statement) throws SQLException{
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

        return connection.prepareStatement(sqlStatement.append(sqlStatementTemplate).toString());
    }

    private boolean isColumnDisplaySizeCorrect(int columnIndex, ResultSetMetaData resultSetMetaData, String value) throws SQLException{
        return resultSetMetaData.getColumnDisplaySize(columnIndex)<value.length();
    }

    private ResultSetMetaData alterColumn(int columnIndex, int newColumnDisplaySize, ResultSetMetaData resultSetMetaData) throws SQLException{
        Statement alterColumn = connection.createStatement();
        StringBuilder alterColumnStatement = new StringBuilder().append("ALTER TABLE ").append(tableName).append(" ALTER COLUMN ").append(resultSetMetaData.getColumnName(columnIndex)).append(" TYPE VARCHAR(").append(newColumnDisplaySize).append(")");
        alterColumn.execute(alterColumnStatement.toString());
        return connection.createStatement().executeQuery("SELECT * FROM "+tableName).getMetaData();
    }
}
