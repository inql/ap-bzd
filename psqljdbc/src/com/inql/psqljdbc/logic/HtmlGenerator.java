package com.inql.psqljdbc.logic;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HtmlGenerator {

    private String outputPath;
    private List<String> lines = new ArrayList<>();
    private String line="";
    private Connection connection;
    private String tableName;

    public HtmlGenerator(String outputPath, Connection connection, String tableName) {
        this.outputPath = outputPath;
        this.connection = connection;
        this.tableName = tableName;
    }

    public void generateHtml(){
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/template.html")));
            while((line=bufferedReader.readLine())!=null){
                if(line.contains("<!--table-->"))
                    lines.add(generateTable());
                else
                    lines.add(line);
            }
            Path file = Paths.get(outputPath);
            Files.write(file,lines, Charset.forName("UTF-8"));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try{
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public String generateTable(){
        String result;
        try {
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("<table class=\"tg\">\n");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            resultBuilder.append("<tr>\n<th class=\"tg-q8ie\" colspan=\"").append(resultSetMetaData.getColumnCount()).append("\">").append(tableName).append("</th>\n</tr>\n");
            resultBuilder.append("<tr>\n");
            for(int i =1; i<=resultSetMetaData.getColumnCount(); i++){
                resultBuilder.append("<td class=\"tg-50tj\">").append(resultSetMetaData.getColumnName(i)).append("</td>\n");
            }
            resultBuilder.append("</tr>\n");
            while(resultSet.next()){
                resultBuilder.append("<tr>\n");
                resultBuilder.append("<td>").append(resultSet.getInt(1)).append("</td>\n");
                for(int i=2; i<=resultSetMetaData.getColumnCount(); i++){
                    resultBuilder.append("<td class=\"tg-94ly\">").append(resultSet.getString(i)).append("</td>\n");
                }
                resultBuilder.append("</tr>\n");
            }
            resultBuilder.append("</table>\n");
            result=resultBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Error while generating table";
        }
        return result;
    }


}
