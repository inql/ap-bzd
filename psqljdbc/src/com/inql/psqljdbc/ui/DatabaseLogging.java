package com.inql.psqljdbc.ui;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class DatabaseLogging {

    private Connection connection;
    private final Console console = System.console();
    private String dbUrl = "192.168.56.102";
    private String dbName = "dbinkus";
    private String dbUsername = "dbinkus";
    private String dbPassword = "!QAZ2wsx";
    private Scanner scanner;

    public DatabaseLogging() {
        this.scanner = new Scanner(System.in);
    }

    public Connection connectToDatabase(){
        System.out.println("Do you like to change default settings?\n\t(Y/N)");
        if(scanner.next().toLowerCase().equals("y")){
            //todo change settings
            insertNewSettings();
        }
        else{
            //todo use saved settings
            useSavedSettings();
        }
        return connect();
    }

    private void insertNewSettings(){
        System.out.print("Enter database address: ");
        dbUrl=scanner.next();
        System.out.print("\nEnter database name: ");
        dbName=scanner.next();
        System.out.print("\nEnter username: ");
        dbUsername=scanner.next();
//        dbPassword = new String(console.readPassword("\nEnter password: "));
        System.out.println("\nEnter password");
        dbPassword=scanner.next();
    }

    private void useSavedSettings(){

    }

    private Connection connect(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://"+dbUrl+":5432/"+dbName,
                            dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return connection;
    }



}
