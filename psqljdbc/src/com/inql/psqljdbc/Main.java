package com.inql.psqljdbc;

import com.inql.psqljdbc.logic.DatabaseOperation;
import com.inql.psqljdbc.logic.ImportCsvFile;
import com.inql.psqljdbc.ui.DatabaseLogging;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String args[]) {
        DatabaseLogging databaseLogging = new DatabaseLogging();
        Connection connection = databaseLogging.connectToDatabase();

        Path path = Paths.get(args[0]).toAbsolutePath();
        System.out.println(path.toString());
        ImportCsvFile importCsvFile = new ImportCsvFile(path.toString());
        DatabaseOperation databaseOperation = new DatabaseOperation(importCsvFile.getDataFromCsvFile(),"data",connection);
        try {
            databaseOperation.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}