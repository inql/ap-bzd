package com.inql.psqljdbc.ui;

import com.inql.psqljdbc.logic.DatabaseLogging;
import com.inql.psqljdbc.logic.DatabaseOperation;
import com.inql.psqljdbc.logic.HtmlGenerator;
import com.inql.psqljdbc.logic.ImportCsvFile;
import com.inql.psqljdbc.model.Data;

import java.io.Console;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    private Connection connection;
    private final Console console = System.console();
    private Scanner scanner;
    private String csvFile;
    private String tableName;

    public UserInterface(String csvFile) {
        this.csvFile = csvFile;
        this.scanner = new Scanner(System.in);
    }

    public void run(){
        if(setConnectionInfo()){
            Data data = new ImportCsvFile(csvFile).getDataFromCsvFile();
            tableName = csvFile.replaceAll(".*[\\\\/]|\\.[^\\.]*$","");
            DatabaseOperation databaseOperation = new DatabaseOperation(data,tableName,connection);
            try {
                databaseOperation.createTable();
                databaseOperation.insertInto();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Operation completed. Would you like to generate html file?\n(Y/N)");
            if(scanner.next().toLowerCase().equals("y")){
                generateHtmlFile();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("The program will now exit...\n Thanks for using!\ninql");
        }

    }

    private void generateHtmlFile(){
        System.out.println("Insert name for result file: ");
        String resultName = scanner.next();
        Path resultPath = Paths.get(System.getProperty("user.dir")+"/"+resultName).toAbsolutePath();
        HtmlGenerator htmlGenerator = new HtmlGenerator(resultPath.toString(),connection,tableName);
        htmlGenerator.generateHtml();

    }

    private boolean setConnectionInfo(){
        String dbUrl = "localhost";
        String dbName = "dbinkus";
        String dbUsername = "dbinkus";
        String dbPassword;

        System.out.println("Do you want to change default settings?\n\t(Y/N)\nDefaults:\n\turl- "+dbUrl+"\n\tdatabase name- "+dbName+"\n\tusername- "+dbUsername);
        if(scanner.next().toLowerCase().equals("y")){
            System.out.print("Enter database address: ");
            dbUrl=scanner.next();
            System.out.print("\nEnter database name: ");
            dbName=scanner.next();
            System.out.print("\nEnter username: ");
            dbUsername=scanner.next();
        }
        dbPassword = new String(console.readPassword("\nEnter password: "));
        try {
            connection = new DatabaseLogging().connect(dbUrl,dbName,dbUsername,dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return true;
    }
}
