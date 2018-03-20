package com.inql.psqljdbc;

import com.inql.psqljdbc.ui.DatabaseLogging;

public class Main {
    public static void main(String args[]) {
        DatabaseLogging databaseLogging = new DatabaseLogging();
        databaseLogging.connectToDatabase();
    }
}